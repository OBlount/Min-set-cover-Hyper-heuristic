package problemInstance;

import heuristics.GenericHeuristic;
import heuristics.initialisation.RandomInitialisationHeuristic;
import heuristics.meta.FitnessProportionateSelectionHeuristic;
import heuristics.meta.ReinforcementLearningILSHeuristic;
import heuristics.meta.SimulatedAnnealing;

import java.util.*;

public class Instance implements IInstance
{
    public final Random rnd;
    private final String instanceName;
    private final int m;
    private final int n;
    private final Vector<Vector<Integer>> listOfSubsets;
    private final boolean[] currentSolution;
    private final boolean[] backupSolution;
    private final GenericHeuristic initialisationHeuristic;
    private final FitnessProportionateSelectionHeuristic rouletteWheelSelection;
    private GenericHeuristic currentlySelectedHeuristic;
    private final ReinforcementLearningILSHeuristic iteratedLocalSearchHeuristic;
    private final SimulatedAnnealing moveAcceptance;

    public Instance(String name, long seed,
                    double iom, double dos,
                    int lowerScore, int upperScore,
                    double cost, double alphaDecay)
    {
        this.rnd = new Random(seed);
        this.instanceName = name;
        InstanceReader reader = new InstanceReader(this.instanceName);
        this.m = reader.GetHeadersM();
        this.n = reader.GetHeadersN();
        listOfSubsets = new Vector<>(this.m);
        reader.PopulateSubsetVector(this.listOfSubsets);
        this.currentSolution = new boolean[this.m];
        this.backupSolution = new boolean[this.m];
        this.initialisationHeuristic = new RandomInitialisationHeuristic(this.rnd);
        CreateSolution();
        this.rouletteWheelSelection =
                new FitnessProportionateSelectionHeuristic(this.rnd, lowerScore, upperScore, iom, dos);
        this.rouletteWheelSelection.ApplyHeuristic(this);
        this.iteratedLocalSearchHeuristic = new ReinforcementLearningILSHeuristic(this.rnd, iom, dos);
        this.moveAcceptance =
                new SimulatedAnnealing(this.rnd, GetObjectiveValue(this.currentSolution),
                        cost, alphaDecay);
    }

    public String GetName()
    {
        return this.instanceName;
    }

    public int GetUniverse()
    {
        return this.n;
    }

    public Vector<Vector<Integer>> GetListOfSubsets()
    {
        return this.listOfSubsets;
    }

    public String GetCurrentSolutionAsString()
    {
        return Arrays.toString(this.currentSolution)
                .replace("true", "1")
                .replace("false", "0");
    }

    public int GetNumberOfVariables()
    {
        return this.m;
    }

    /**
     * A direct method to bit flip the current solution given index.
     * @param index Chosen index of the solution to bit flip.
     */
    public void BitFlip(int index)
    {
        this.currentSolution[index] = !this.currentSolution[index];
    }

    public void CreateSolution()
    {
        this.initialisationHeuristic.ApplyHeuristic(this);
    }

    /**
     * Given a boolean encoded string where 1 = "the subset is used" and 0 = "the subset is not used", the function will
     * return true if the solution is "feasible" - meaning it passes off as a correct solution containing all values
     * present in the universe <i>n</i>.
     * @param solution The boolean array encoded solution.
     * @return True if the solution is feasible.
     */
    private boolean isSolutionFeasible(boolean[] solution)
    {
        Set<Integer> union = new HashSet<>(this.n);
        for(int i = 0; i < solution.length; ++i)
            if(solution[i])
                union.addAll(this.listOfSubsets.get(i));
        return union.size() == this.n;
    }

    /**
     * This is a minimisation optimisation objective function which evaluates an encoded solution. If the solution is
     * infeasible, it will return ((total number of subsets + 1) + (number of subsets that aren't covered)). An
     * infeasible solution that covers little to no subsets gets an inferior score (E.g. 00000... returns the worst
     * score if infeasible. Returning a value close to 0 suggests that the solution is at its local optima.
     * @param solution The boolean array encoded solution.
     * @return The number of subsets it "uses" as its solution.
     */
    public int GetObjectiveValue(boolean[] solution)
    {
        int counter = 0;
        for(boolean set : solution) if(set) counter++;
        if(!isSolutionFeasible(solution))
            return (this.m + 1) + (this.n - counter);
        else
            return counter;
    }

    public void ApplyMovementOperator()
    {
        this.currentlySelectedHeuristic.ApplyHeuristic(this);
    }

    public boolean[] GetCurrentSolution()
    {
        return this.currentSolution;
    }

    public void BackupSolution(boolean[] solution)
    {
        System.arraycopy(solution, 0, this.backupSolution, 0, solution.length);
    }

    public void RevertCurrentSolution()
    {
        System.arraycopy(this.backupSolution, 0,
                this.currentSolution, 0, this.backupSolution.length);
    }

    public boolean[] GetBackupSolution()
    {
        return this.backupSolution;
    }

    public void SetCurrentlySelectedHeuristic(GenericHeuristic heuristic)
    {
        this.currentlySelectedHeuristic = heuristic;
    }

    public FitnessProportionateSelectionHeuristic GetReinforcementLearningHeuristic()
    {
        return this.rouletteWheelSelection;
    }

    public GenericHeuristic GetCurrentSelectedHeuristic()
    {
        return this.currentlySelectedHeuristic;
    }

    /**
     * What the instance should perform every iteration.
     */
    public void Solve()
    {
        this.iteratedLocalSearchHeuristic.ApplyHeuristic(this);
    }

    public void SetCurrentSolution(boolean[] solution)
    {
        System.arraycopy(solution, 0, this.currentSolution, 0, solution.length);
    }

    public SimulatedAnnealing GetMoveAcceptance()
    {
        return this.moveAcceptance;
    }
}

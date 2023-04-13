package problemInstance;

import heuristics.*;

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
    private final GenericHeuristic currentMovementOperator;

    public Instance(String name, long seed, double iom, double dos)
    {
        this.rnd = new Random(seed);
        this.instanceName = name;
        InstanceReader reader = new InstanceReader(this.instanceName);
        this.m = reader.GetHeadersM();
        this.n = reader.GetHeadersN();
        listOfSubsets = new Vector<>(this.m);
        populateListOfSubsets(reader);
        this.currentSolution = new boolean[this.m];
        this.backupSolution = new boolean[this.m];
        this.initialisationHeuristic = new RandomInitialisationHeuristic(this.rnd);
        this.initialisationHeuristic.ApplyHeuristic(this);
        // TODO: Random initial movement operator
        this.currentMovementOperator = new IteratedLocalSearchHeuristic(this.rnd, iom, dos);
    }

    private void populateListOfSubsets(InstanceReader reader)
    {
        for(int i = 0; i <= this.m; ++i)
            this.listOfSubsets.add(i, reader.GetSubsetBlock(i));
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
            return (this.m + 1) + (this.m - counter);
        else
            return counter;
    }

    public void ApplyMovementOperator()
    {
        this.currentMovementOperator.ApplyHeuristic(this);
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
}

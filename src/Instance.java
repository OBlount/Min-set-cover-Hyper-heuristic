import java.util.*;

public class Instance implements IInstance
{
    public final Random rnd;
    private final String instanceName;
    private final int m;
    private final int n;
    private final Vector<Vector<Integer>> listOfSubsets;
    private final boolean[] currentSolution;
    private final GenericHeuristic initialisationHeuristic;

    @SuppressWarnings("SameParameterValue")
    Instance(String name, long seed)
    {
        this.rnd = new Random(seed);
        this.instanceName = name;
        InstanceReader reader = new InstanceReader(this.instanceName);
        this.m = reader.GetHeadersM();
        this.n = reader.GetHeadersN();
        listOfSubsets = new Vector<>(this.m);
        populateListOfSubsets(reader);
        this.currentSolution = new boolean[this.m];
        this.initialisationHeuristic = new RandomInitialisationHeuristic(this.rnd);
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
     * @param solution The boolean array encoded current solution.
     * @return True if the solution is feasible.
     */
    private boolean isSolutionFeasible(boolean[] solution)
    {
        Set<Integer> union = new HashSet<>(this.n);
        for(int i = 0; i < solution.length; ++i)
        {
            if(solution[i])
                union.addAll(this.listOfSubsets.get(i));
        }
        return union.size() == this.n;
    }

    /**
     * This is a minimisation optimisation objective function which evaluates the current encoded solution. If the
     * solution is infeasible, it will return the max amount of subsets + 1 + how many subsets it covers. Returning a
     * value close to 0 suggests that the solution is at its local optima.
     * @return The number of subsets it "uses" as its solution.
     */
    public int GetObjectiveValue()
    {
        int counter = 0;
        for(boolean set : this.currentSolution) if(set) counter++;
        if(!isSolutionFeasible(this.currentSolution))
            return this.m + 1 + counter;
        else
            return counter;
    }
}

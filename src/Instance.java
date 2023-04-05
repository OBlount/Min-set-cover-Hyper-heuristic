import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class Instance implements IInstance
{
    public final Random rnd;
    private final String instanceName;
    private final int m;
    private final int n;
    private final Vector<Vector<Integer>> listOfSubsets;
    private boolean[] bestSolution;
    private boolean[] currentSolution;
    private final GenericHeuristic initialisationHeuristic;

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

    public void CreateRandomSolution()
    {
        this.initialisationHeuristic.ApplyHeuristic(this);
    }
}

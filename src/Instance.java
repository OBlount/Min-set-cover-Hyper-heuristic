import java.util.Vector;

public class Instance implements IInstance
{
    private final String instanceName;
    private final int m;
    private final int n;
    private final Vector<Vector<Integer>> listOfSubsets;

    Instance(String name)
    {
        this.instanceName = name;
        InstanceReader reader = new InstanceReader(this.instanceName);
        this.m = reader.GetHeadersM();
        this.n = reader.GetHeadersN();
        listOfSubsets = new Vector<>(this.m);
        populateListOfSubsets(reader);
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
}

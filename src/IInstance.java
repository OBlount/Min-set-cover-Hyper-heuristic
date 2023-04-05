import java.util.BitSet;
import java.util.Vector;

public interface IInstance
{
    String GetName();
    int GetUniverse();
    Vector<Vector<Integer>> GetListOfSubsets();
    String GetCurrentSolutionAsString();
    int GetNumberOfVariables();
    void BitFlip(int index);
    void CreateRandomSolution();
}

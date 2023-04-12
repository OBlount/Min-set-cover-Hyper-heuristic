import java.util.Vector;

@SuppressWarnings("unused")
public interface IInstance
{
    String GetName();
    int GetUniverse();
    Vector<Vector<Integer>> GetListOfSubsets();
    String GetCurrentSolutionAsString();
    int GetNumberOfVariables();
    void BitFlip(int index);
    void CreateSolution();
    int GetObjectiveValue();
    void ApplyMovementOperator();
}

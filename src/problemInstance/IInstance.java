package problemInstance;

import java.util.Vector;

public interface IInstance
{
    String GetName();
    int GetUniverse();
    Vector<Vector<Integer>> GetListOfSubsets();
    String GetCurrentSolutionAsString();
    int GetNumberOfVariables();
    void BitFlip(int index);
    void CreateSolution();
    int GetObjectiveValue(boolean[] solution);
    void ApplyMovementOperator();
    boolean[] GetCurrentSolution();
    void RevertCurrentSolution();
    void BackupSolution(boolean[] solution);
    boolean[] GetBackupSolution();
}

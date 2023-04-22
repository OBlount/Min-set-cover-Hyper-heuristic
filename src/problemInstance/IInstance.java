package problemInstance;

import heuristics.GenericHeuristic;
import heuristics.meta.FitnessProportionateSelectionHeuristic;
import heuristics.meta.SimulatedAnnealing;

import java.util.Vector;

public interface IInstance
{
    String GetName();
    int GetUniverse();
    Vector<Vector<Integer>> GetListOfSubsets();
    String GetSolutionAsString(boolean[] solution);
    int GetNumberOfVariables();
    void BitFlip(int index);
    void CreateSolution();
    int GetObjectiveValue(boolean[] solution);
    void ApplyMovementOperator();
    boolean[] GetCurrentSolution();
    boolean[] GetBestSolution();
    void RevertCurrentSolution();
    void BackupSolution(boolean[] solution);
    boolean[] GetBackupSolution();
    void SetCurrentlySelectedHeuristic(GenericHeuristic heuristic);
    FitnessProportionateSelectionHeuristic GetReinforcementLearningHeuristic();
    GenericHeuristic GetCurrentSelectedHeuristic();
    void Solve(long iterationNumber);
    void SetCurrentSolution(boolean[] solution);
    void SetBestSolution(boolean[] solution);
    SimulatedAnnealing GetMoveAcceptance();
    void CleanUp();
}

package heuristics.meta;

import heuristics.GenericHeuristic;
import problemInstance.Instance;

import java.util.Random;

public class ReinforcementLearningILSHeuristic extends GenericHeuristic
{
    public ReinforcementLearningILSHeuristic(Random random, double iom, double dos)
    {
        super(random, iom, dos);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        problem.BackupSolution(problem.GetCurrentSolution());
        // Select a local search heuristic and apply to the problem:
        problem.GetReinforcementLearningHeuristic().ApplyHeuristic(problem);
        problem.ApplyMovementOperator();
        // Evaluate current and new solution:
        int backupSolutionObjectiveValue = problem.GetObjectiveValue(problem.GetBackupSolution());
        int newSolutionObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
        // Perform elaborate move acceptance (Revert solution from backup if value is worse):
        boolean isAccepted = problem.GetMoveAcceptance().PerformElaborateMoveAcceptance(
                backupSolutionObjectiveValue,
                newSolutionObjectiveValue);
        if(isAccepted)
            problem.GetReinforcementLearningHeuristic().IncrementHeuristicScore(problem.GetCurrentSelectedHeuristic());
        else
        {
            problem.GetReinforcementLearningHeuristic().DecrementHeuristicScore(problem.GetCurrentSelectedHeuristic());
            problem.RevertCurrentSolution();
        }
    }

    @Override
    public String GetHeuristicName()
    {
        return "Reinforcement Learning Iterated Local Search";
    }
}

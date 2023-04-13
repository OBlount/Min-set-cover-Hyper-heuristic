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
        problem.GetReinforcementLearningHeuristic().ApplyHeuristic(problem);
        problem.ApplyMovementOperator();
        int newSolutionObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
        int backupSolutionObjectiveValue = problem.GetObjectiveValue(problem.GetBackupSolution());
        // Strict improvement (Revert solution from backup if value is worse):
        if(newSolutionObjectiveValue < backupSolutionObjectiveValue)
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
        return "Reinforcement Learning Iterated Search";
    }
}

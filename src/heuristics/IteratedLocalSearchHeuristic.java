package heuristics;

import problemInstance.Instance;

import java.util.Random;

public class IteratedLocalSearchHeuristic extends GenericHeuristic
{
    private final GenericHeuristic mutatorHeuristic;
    private final GenericHeuristic localSearchHeuristic;

    public IteratedLocalSearchHeuristic(Random randomNumberGenerator, double iom, double dos)
    {
        super(randomNumberGenerator, iom, dos);
        this.mutatorHeuristic = new RandomBitFlipHeuristic(randomNumberGenerator, 0.0);
        this.localSearchHeuristic = new DavisBitHillClimbHeuristic(randomNumberGenerator);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        problem.BackupSolution(problem.GetCurrentSolution());
        for(int i = 0; i < this.IOM; ++i)
            this.mutatorHeuristic.ApplyHeuristic(problem);
        for(int i = 0; i < this.DOS; ++i)
            this.localSearchHeuristic.ApplyHeuristic(problem);
        // Strict improvement (Revert solution from backup if value is worse):
        int solutionObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
        if(solutionObjectiveValue > problem.GetObjectiveValue(problem.GetBackupSolution()))
            problem.RevertCurrentSolution();
    }

    @Override
    public String GetHeuristicName()
    {
        return "Iterated Local Search";
    }
}

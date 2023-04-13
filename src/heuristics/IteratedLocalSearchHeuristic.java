package heuristics;

import problemInstance.Instance;

import java.util.Random;

public class IteratedLocalSearchHeuristic extends GenericHeuristic
{
    public IteratedLocalSearchHeuristic(Random randomNumberGenerator, double iom, double dos)
    {
        super(randomNumberGenerator, iom, dos);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        DavisBitHillClimbHeuristic davisBitHillClimbHeuristic =
                new DavisBitHillClimbHeuristic(this.rnd);
        for(int i = 0; i < this.IOM; ++i)
            problem.BitFlip(this.rnd.nextInt(problem.GetNumberOfVariables()));
        // for(int i = 0; i < this.DOS; ++i)
            // davisBitHillClimbHeuristic.ApplyHeuristic(problem);
    }

    @Override
    public String GetHeuristicName()
    {
        return "Iterated Local Search";
    }
}

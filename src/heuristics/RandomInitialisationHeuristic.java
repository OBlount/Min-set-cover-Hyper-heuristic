package heuristics;

import problemInstance.Instance;

import java.util.Random;

public class RandomInitialisationHeuristic extends GenericHeuristic
{
    public RandomInitialisationHeuristic(Random randomNumberGenerator)
    {
        super(randomNumberGenerator);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        RandomBitFlipHeuristic randomBitFlipHeuristic =
                new RandomBitFlipHeuristic(this.rnd, this.IOM);
        for(int i = 0; i < problem.GetNumberOfVariables(); ++i)
            if(rnd.nextBoolean())
                problem.BitFlip(i);
        // Stop until we find a feasible solution:
        while(problem.GetObjectiveValue(problem.GetCurrentSolution()) > problem.GetNumberOfVariables() + 1)
            randomBitFlipHeuristic.ApplyHeuristic(problem);
    }

    @Override
    public String GetHeuristicName()
    {
        return "Random Initialisation";
    }
}

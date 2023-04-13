package heuristics.initialisation;

import heuristics.GenericHeuristic;
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
        for(int i = 0; i < problem.GetNumberOfVariables(); ++i)
            if(rnd.nextBoolean())
                problem.BitFlip(i);
    }

    @Override
    public String GetHeuristicName()
    {
        return "Random Initialisation";
    }
}

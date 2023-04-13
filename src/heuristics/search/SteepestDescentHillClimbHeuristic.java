package heuristics.search;

import heuristics.GenericHeuristic;
import problemInstance.Instance;

import java.util.Random;

public class SteepestDescentHillClimbHeuristic extends GenericHeuristic
{
    public SteepestDescentHillClimbHeuristic(Random random)
    {
        super(random);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        int bestObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
        boolean isImprovement = false;
        int mostImprovingBitFlipIndex = 0;
        for(int i = 0; i < problem.GetNumberOfVariables(); ++i)
        {
            problem.BitFlip(i);
            int temporaryObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
            // Strict improvement:
            if(temporaryObjectiveValue < bestObjectiveValue)
            {
                bestObjectiveValue = temporaryObjectiveValue;
                mostImprovingBitFlipIndex = i;
                isImprovement = true;
            }
            problem.BitFlip(i);
        }
        if(isImprovement) problem.BitFlip(mostImprovingBitFlipIndex);
    }

    @Override
    public String GetHeuristicName()
    {
        return "Steepest Descent Hill Climb";
    }
}

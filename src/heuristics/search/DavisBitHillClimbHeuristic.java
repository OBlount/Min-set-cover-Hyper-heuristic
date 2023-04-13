package heuristics.search;

import heuristics.GenericHeuristic;
import problemInstance.Instance;

import java.util.Random;
import java.util.stream.IntStream;

public class DavisBitHillClimbHeuristic extends GenericHeuristic
{
    public DavisBitHillClimbHeuristic(Random random)
    {
        super(random);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        int bestObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
        int[] permutation = createRandomPermutation(problem.GetNumberOfVariables());
        for(int i = 0; i < problem.GetNumberOfVariables(); ++i)
        {
            problem.BitFlip(permutation[i]);
            int temporaryObjectiveValue = problem.GetObjectiveValue(problem.GetCurrentSolution());
            // Strict improvement:
            if(temporaryObjectiveValue < bestObjectiveValue)
                bestObjectiveValue = temporaryObjectiveValue;
            else
                problem.BitFlip(permutation[i]);
        }
    }

    private int[] createRandomPermutation(int length)
    {
        int[] array = IntStream.range(0, length).toArray();
        for (int i = 0; i < length; ++i)
        {
            int change = i + this.rnd.nextInt(length - i);
            swap(array, i, change);
        }
        return array;
    }

    private void swap(int[] array, int index, int change)
    {
        int tempValue = array[index];
        array[index] = array[change];
        array[change] = tempValue;
    }

    @Override
    public String GetHeuristicName()
    {
        return "Davis's Bit Hill Climb";
    }
}

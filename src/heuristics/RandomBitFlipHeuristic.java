package heuristics;

import problemInstance.Instance;

import java.util.Random;

public class RandomBitFlipHeuristic extends GenericHeuristic
{
    public RandomBitFlipHeuristic(Random random, double iom)
    {
        super(random, iom, 0.0);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        for(int i = 0; i < this.IOM; ++i)
        {
            int index = rnd.nextInt(problem.GetNumberOfVariables());
            problem.BitFlip(index);
        }
    }

    @Override
    public String GetHeuristicName()
    {
        return "Random Bit Flip";
    }
}

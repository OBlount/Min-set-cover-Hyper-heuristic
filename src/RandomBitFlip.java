import java.util.Random;

public class RandomBitFlip extends GenericHeuristic
{
    RandomBitFlip(Random random)
    {
        super(random);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        int index = rnd.nextInt(problem.GetNumberOfVariables());
        problem.BitFlip(index);
    }

    @Override
    public String GetHeuristicName()
    {
        return "Random Bit Flip Heuristic";
    }
}

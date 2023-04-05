import java.util.Random;

public class RandomInitialisationHeuristic extends GenericHeuristic
{
    RandomInitialisationHeuristic(Random randomNumberGenerator)
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
        return "Random Initialisation Heuristic";
    }
}

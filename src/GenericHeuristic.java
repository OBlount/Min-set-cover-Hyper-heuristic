import java.util.Random;

public abstract class GenericHeuristic
{
    Random rnd;

    GenericHeuristic(Random randomNumberGenerator)
    {
        this.rnd = randomNumberGenerator;
    }

    public abstract void ApplyHeuristic(Instance problem);
    public abstract String GetHeuristicName();
}

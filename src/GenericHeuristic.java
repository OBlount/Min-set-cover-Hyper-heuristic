import java.util.Random;

public abstract class GenericHeuristic
{
    final Random rnd;
    int IOM;
    int DOS;

    GenericHeuristic(Random randomNumberGenerator, double iom, double dos) throws IllegalArgumentException
    {
        this.rnd = randomNumberGenerator;
        if (iom >= 0.0 && iom < 0.2)
            this.IOM = 1;
        else if (iom >= 0.2 && iom < 0.4)
            this.IOM = 2;
        else if (iom >= 0.4 && iom < 0.6)
            this.IOM = 3;
        else if (iom >= 0.6 && iom < 0.8)
            this.IOM = 4;
        else if (iom >= 0.8 && iom < 1.0)
            this.IOM = 5;
        else if (iom == 1.0)
            this.IOM = 6;
        else
            throw new IllegalArgumentException("IOM should be between 0.0 and 1.0");
        if (dos >= 0.0 && dos < 0.2)
            this.DOS = 1;
        else if (dos >= 0.2 && dos < 0.4)
            this.DOS = 2;
        else if (dos >= 0.4 && dos < 0.6)
            this.DOS = 3;
        else if (dos >= 0.6 && dos < 0.8)
            this.DOS = 4;
        else if (dos >= 0.8 && dos < 1.0)
            this.DOS = 5;
        else if (dos == 1.0)
            this.DOS = 6;
        else
            throw new IllegalArgumentException("DOS should be between 0.0 and 1.0");
    }

    GenericHeuristic(Random randomNumberGenerator)
    {
        this.rnd = randomNumberGenerator;
    }

    public abstract void ApplyHeuristic(Instance problem);
    public abstract String GetHeuristicName();
}

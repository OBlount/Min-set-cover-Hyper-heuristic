import problemInstance.Instance;

public class Main
{
    public static void main(String[] args)
    {
        long runningTime = 2L;
        Instance d1 = new Instance("d1_50_500", 24042023, 0.5, 0.5);
        runInstance(d1, runningTime);
    }

    private static void runInstance(Instance instance, long timeToRun)
    {
        final long timeBudget = timeToRun * (1000 * 1000 * 1000);
        final long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < timeBudget)
            instance.ApplyMovementOperator();

        System.out.println(instance.GetCurrentSolutionAsString());
        System.out.println(instance.GetObjectiveValue(instance.GetCurrentSolution()));
    }
}

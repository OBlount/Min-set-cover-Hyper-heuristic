import problemInstance.Instance;
import problemInstance.InstanceConfig;

public class Main
{
    public static void main(String[] args)
    {
        Instance instance = new Instance(
                InstanceConfig.INSTANCE_FILE_NAME,
                InstanceConfig.SEED,
                InstanceConfig.IOM,
                InstanceConfig.DOS,
                InstanceConfig.LOWER_BOUND,
                InstanceConfig.UPPER_BOUND);
        runInstance(instance, InstanceConfig.RUN_TIME_IN_SECONDS);
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

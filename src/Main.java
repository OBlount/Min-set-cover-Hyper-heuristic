import problemInstance.Instance;
import problemInstance.InstanceConfig;

public class Main
{
    private static final int maxNumberOf = 10;

    public static void main(String[] args)
    {
        for(int i = 1; i <= maxNumberOf; ++i)
        {
            Instance instance = new Instance(
                    InstanceConfig.INSTANCE_FILE_NAME,
                    InstanceConfig.SEED_POOL[i % InstanceConfig.SEED_POOL.length],
                    InstanceConfig.IOM,
                    InstanceConfig.DOS,
                    InstanceConfig.LOWER_BOUND,
                    InstanceConfig.UPPER_BOUND,
                    InstanceConfig.MOVE_ACCEPTANCE_COST,
                    InstanceConfig.MOVE_ACCEPTANCE_ALPHA_DECAY);
            runInstance(instance, InstanceConfig.RUN_TIME_IN_SECONDS);
            System.out.println("Trial #" + i + ":");
            System.out.println("Best Solution: " + instance.GetCurrentSolutionAsString());
            System.out.println("Objective Value: " + instance.GetObjectiveValue(instance.GetCurrentSolution()));
        }
    }

    private static void runInstance(Instance instance, long timeToRun)
    {
        final long timeBudget = timeToRun * (1000 * 1000 * 1000);
        final long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < timeBudget)
            instance.Solve();
    }
}

import problemInstance.Instance;
import problemInstance.InstanceConfig;
import problemInstance.InstanceFileWriter;

import java.io.IOException;

public class Main
{
    private static final int maxNumberOf = 10;

    public static void main(String[] args) throws IOException {
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
                    InstanceConfig.MOVE_ACCEPTANCE_ALPHA_DECAY,
                    i);
            runInstance(instance, InstanceConfig.RUN_TIME_IN_SECONDS);
            System.out.println("Trial #" + i + ":");
            System.out.println("Best Solution: " + instance.GetSolutionAsString(instance.GetBestSolution()));
            System.out.println("Objective Value: " + instance.GetObjectiveValue(instance.GetBestSolution()));
        }
    }

    private static void runInstance(Instance instance, long timeToRun)
    {
        final long timeBudget = timeToRun * (1000 * 1000 * 1000);
        final long startTime = System.nanoTime();
        long iterationNumber = 0;
        while ((System.nanoTime() - startTime) < timeBudget)
            instance.Solve(iterationNumber++);
        instance.CleanUp();
    }
}

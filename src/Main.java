public class Main
{
    public static void main(String[] args)
    {
        Instance d1 = new Instance("d1_50_500", 24042023, 0.0, 0.0);
        d1.CreateSolution();

        final long timeBudget = 2L * (1000 * 1000 * 1000);
        final long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < timeBudget)
            d1.ApplyMovementOperator();
        System.out.println(d1.GetCurrentSolutionAsString());
        System.out.println(d1.GetObjectiveValue());
    }
}

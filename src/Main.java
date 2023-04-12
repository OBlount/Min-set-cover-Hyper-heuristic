public class Main
{
    public static void main(String[] args)
    {
        Instance d1 = new Instance("d1_50_500", 24042023);
        d1.CreateSolution();

        final long timeBudget = 10L * (1000 * 1000 * 1000);
        final long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < timeBudget)
        {
            RandomBitFlipHeuristic randomBitFlip = new RandomBitFlipHeuristic(d1.rnd);
            randomBitFlip.ApplyHeuristic(d1);
        }
        System.out.println(d1.GetCurrentSolutionAsString());
        System.out.println(d1.GetObjectiveValue());
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Instance d1 = new Instance("d1_50_500", 24042023);
        d1.CreateSolution();
        boolean terminationCriteria = true;
        while(terminationCriteria)
        {
            RandomBitFlipHeuristic randomBitFlip = new RandomBitFlipHeuristic(d1.rnd);
            randomBitFlip.ApplyHeuristic(d1);
            if(d1.GetObjectiveValue() <= d1.GetNumberOfVariables())
                terminationCriteria = false;
        }
        System.out.println(d1.GetCurrentSolutionAsString());
        System.out.println(d1.GetObjectiveValue());
    }
}

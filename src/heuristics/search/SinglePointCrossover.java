package heuristics.search;

import heuristics.GenericHeuristic;
import problemInstance.Instance;

import java.util.Arrays;
import java.util.Random;

public class SinglePointCrossover extends GenericHeuristic
{
    public SinglePointCrossover(Random random)
    {
        super(random);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        problem.BackupSolution(problem.GetCurrentSolution());
        boolean[] parent1 = problem.GetCurrentSolution();
        boolean[] parent2 = this.createRandomFeasibleSolution(problem);
        // Perform single cut:
        int randomIndex = this.rnd.nextInt(problem.GetNumberOfVariables());
        parent1 = Arrays.copyOfRange(parent1, 0, randomIndex);
        parent2 = Arrays.copyOfRange(parent2, randomIndex, problem.GetNumberOfVariables());
        // Perform crossing:
        boolean[] resultingSolution = new boolean[problem.GetNumberOfVariables()];
        System.arraycopy(parent1, 0, resultingSolution, 0, parent1.length);
        System.arraycopy(parent2, 0, resultingSolution, parent1.length, parent2.length);
        // Strict improvement:
        if(problem.GetObjectiveValue(resultingSolution) < problem.GetObjectiveValue(problem.GetCurrentSolution()))
            problem.SetCurrentSolution(resultingSolution);
    }

    private boolean[] createRandomFeasibleSolution(Instance problem)
    {
        boolean[] solution = new boolean[problem.GetNumberOfVariables()];
        while(problem.GetObjectiveValue(solution) > problem.GetNumberOfVariables())
            for(int i = 0; i < problem.GetNumberOfVariables(); ++i)
                solution[i] = this.rnd.nextBoolean();
        return solution;
    }

    @Override
    public String GetHeuristicName()
    {
        return "1-Point Crossover";
    }
}

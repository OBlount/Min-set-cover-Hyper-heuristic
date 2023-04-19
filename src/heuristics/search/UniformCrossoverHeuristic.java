package heuristics.search;

import heuristics.GenericHeuristic;
import problemInstance.Instance;

import java.util.Random;

public class UniformCrossoverHeuristic extends GenericHeuristic
{
    public UniformCrossoverHeuristic(Random random)
    {
        super(random);
    }

    @Override
    public void ApplyHeuristic(Instance problem)
    {
        boolean[] parent1 = problem.GetCurrentSolution();
        boolean[] parent2 = this.createRandomFeasibleSolution(problem);
        // Perform crossing:
        boolean[] resultingSolution = new boolean[problem.GetNumberOfVariables()];
        for(int i = 0; i < resultingSolution.length; ++i)
        {
            if(this.rnd.nextBoolean())
                resultingSolution[i] = parent1[i];
            else
                resultingSolution[i] = parent2[i];
        }
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
        return "Uniform Crossover";
    }
}

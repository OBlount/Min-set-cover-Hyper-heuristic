package heuristics.meta;

import java.util.Random;

public class SimulatedAnnealing
{
    private final Random rnd;
    private final double alpha;
    private double currentTemperature;

    public SimulatedAnnealing(Random random, double initialSolutionFitness, double cost, double alphaDecay)
    {
        this.rnd = random;
        this.currentTemperature = initialSolutionFitness * cost;
        this.alpha = alphaDecay;
    }

    public boolean PerformElaborateMoveAcceptance(int currentSolution, int candidateSolutionScore)
    {
        boolean isCandidateAccepted;
        double diceRoll = this.rnd.nextDouble();
        int delta = currentSolution - candidateSolutionScore;
        // Strict improvement or accept on temperature (Revert solution from backup if value is worse):
        isCandidateAccepted = delta >= 0 && !(diceRoll < p(delta));
        advanceTemperature();
        return isCandidateAccepted;
    }

    /**
     * Geometric cooling schedule implementation.
     */
    private void advanceTemperature()
    {
        this.currentTemperature = this.alpha * this.currentTemperature;
    }

    /**
     * Simulated Annealing probability utility function.
     * @param delta The fitness score difference between old and new solutions.
     * @return A number which overtime should get closer to 1.0.
     */
    private double p(int delta)
    {
        return Math.exp((-delta) / this.currentTemperature);
    }
}

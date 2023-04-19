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

    /**
     * A simulated annealing implementation which takes in two solutions, and returns an indicator to which one is
     * overall accepted. It requires a p() probability function and indirectly advances the temperature variable.
     * @param currentSolution The current solution objective value (most likely the backup solution score).
     * @param candidateSolutionScore The new solution to be compared to (most likely the current solution).
     * @return Returning true means that the candidate (new) solution is accepted. Returning false means the candidate
     * solution should be rejected.
     */
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

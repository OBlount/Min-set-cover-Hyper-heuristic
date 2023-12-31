package heuristics.meta;

import heuristics.GenericHeuristic;
import heuristics.search.*;
import problemInstance.EHeuristicIDs;
import problemInstance.Instance;
import problemInstance.InstanceConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class FitnessProportionateSelectionHeuristic extends GenericHeuristic implements IRouletteWheel
{
    private final int defaultScore, lowerBound, upperBound;
    private final LinkedHashMap<GenericHeuristic, Integer> rouletteWheel;

    public FitnessProportionateSelectionHeuristic(Random random, int lowerScore, int upperScore, double iom, double dos)
    {
        super(random, iom, dos);
        this.lowerBound = lowerScore;
        this.upperBound = upperScore;
        this.defaultScore = (lowerScore + upperScore) / 2;
        this.rouletteWheel = new LinkedHashMap<>(10);
        // Initialise all chosen heuristics here (dependant on config):
        for(EHeuristicIDs heuristicID : InstanceConfig.SET_OF_SEARCH_HEURISTICS)
        {
            switch (heuristicID)
            {
                case RANDOM_BIT_FLIP ->
                        this.rouletteWheel.put(new RandomBitFlipHeuristic(this.rnd, iom),
                                this.defaultScore);
                case SDHC ->
                        this.rouletteWheel.put(new SteepestDescentHillClimbHeuristic(this.rnd),
                                this.defaultScore);
                case DBHC ->
                        this.rouletteWheel.put(new DavisBitHillClimbHeuristic(this.rnd),
                                this.defaultScore);
                case ONE_POINT_CROSSOVER ->
                        this.rouletteWheel.put(new SinglePointCrossoverHeuristic(this.rnd),
                                this.defaultScore);
                case UNIFORM_CROSSOVER ->
                        this.rouletteWheel.put(new UniformCrossoverHeuristic(this.rnd),
                                this.defaultScore);
            }
        }
    }

    /**
     * Sets the instance's currently selected search operator to a heuristic found in the roulette wheel. The heuristic
     * chosen is at random, but with a bias according to the heuristic's associated score. Please note that the hash map
     * containing the roulette wheel data holds the literal heuristic instances (it houses the heuristics themselves and
     * provides the hyper-heuristic operators directly from here).
     * @param problem The problem instance.
     */
    @Override
    public void ApplyHeuristic(Instance problem)
    {
        int randomNumber = this.rnd.nextInt(GetTotalScore());
        int pointer = 0;
        for(Map.Entry<GenericHeuristic, Integer> heuristic : this.rouletteWheel.entrySet())
        {
            pointer += heuristic.getValue();
            if(pointer > randomNumber)
            {
                problem.SetCurrentlySelectedHeuristic(heuristic.getKey());
                break;
            }
        }
    }

    @Override
    public String GetHeuristicName()
    {
        return "Fitness Proportionate Selection";
    }

    @Override
    public void ResetScores()
    {
        for(Map.Entry<GenericHeuristic, Integer> heuristic : this.rouletteWheel.entrySet())
            heuristic.setValue(this.defaultScore);
    }

    @Override
    public int GetScoreOfHeuristic(GenericHeuristic heuristic)
    {
        return this.rouletteWheel.get(heuristic);
    }

    @Override
    public void IncrementHeuristicScore(GenericHeuristic heuristic)
    {
        if(this.rouletteWheel.get(heuristic) < this.upperBound)
            this.rouletteWheel.put(heuristic, this.rouletteWheel.get(heuristic) + 1);
    }

    @Override
    public void DecrementHeuristicScore(GenericHeuristic heuristic)
    {
        if(this.rouletteWheel.get(heuristic) > this.lowerBound)
            this.rouletteWheel.put(heuristic, this.rouletteWheel.get(heuristic) - 1);
    }

    @Override
    public int GetTotalScore()
    {
        int count = 0;
        for(Map.Entry<GenericHeuristic, Integer> heuristic : this.rouletteWheel.entrySet())
            count += heuristic.getValue();
        return count;
    }

    @Override
    public String WheelToString()
    {
        StringBuilder wheel = new StringBuilder();
        for(Map.Entry<GenericHeuristic, Integer> heuristic : this.rouletteWheel.entrySet())
            wheel.append(heuristic.getKey())
                    .append(" : ")
                    .append(heuristic.getValue().toString())
                    .append("\r\n");
        return wheel.toString();
    }
}

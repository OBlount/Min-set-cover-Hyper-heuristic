package heuristics.meta;

import heuristics.GenericHeuristic;

public interface IRouletteWheel
{
    void ResetScores();
    int GetScoreOfHeuristic(GenericHeuristic heuristic);
    void IncrementHeuristicScore(GenericHeuristic heuristic);
    void DecrementHeuristicScore(GenericHeuristic heuristic);
    int GetTotalScore();
    String WheelToString();
}

package heuristics;

public interface IRouletteWheel
{
    void ResetScores();
    int GetScoreOfHeuristic(GenericHeuristic heuristic);
    void IncrementHeuristicScore(GenericHeuristic heuristic);
    void DecrementHeuristicScore(GenericHeuristic heuristic);
    int GetTotalScore();
    String WheelToString();
}

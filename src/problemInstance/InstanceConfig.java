package problemInstance;

public class InstanceConfig
{
    // Instance data file names (omit file extension):
    public static final String INSTANCE_FILE_NAME = "d1_50_500";
    public static final String INSTANCE_OUTPUT_FILE_NAME = "output";
    public static final long[] SEED_POOL = {
            24042023,
            19042023,
            2022003,
            19283746,
    };
    public static long RUN_TIME_IN_SECONDS = 2L;
    // Global "Intensity of Mutation" and "Depth of Search" parameters:
    public static double IOM = 0.5;
    public static double DOS = 0.5;
    // Simulated Annealing parameters:
    public static double MOVE_ACCEPTANCE_COST = 1.0;
    public static double MOVE_ACCEPTANCE_ALPHA_DECAY = 0.1;
    // Fitness Proportionate Selection parameters:
    public static int LOWER_BOUND = 1;
    public static int UPPER_BOUND = 10;
    // List of heuristics present in the hyper-heuristic (IDs):
    public static EHeuristicIDs[] SET_OF_SEARCH_HEURISTICS = {
            EHeuristicIDs.RANDOM_BIT_FLIP,
            EHeuristicIDs.SDHC,
            EHeuristicIDs.DBHC,
            EHeuristicIDs.ONE_POINT_CROSSOVER,
            EHeuristicIDs.UNIFORM_CROSSOVER,
    };
}

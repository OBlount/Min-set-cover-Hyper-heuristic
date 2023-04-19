package problemInstance;

public class InstanceConfig
{
    public static final String INSTANCE_FILE_NAME = "d1_50_500";
    public static final long SEED = 24042023;
    public static long RUN_TIME_IN_SECONDS = 2L;
    public static double IOM = 0.5;
    public static double DOS = 0.5;
    public static double MOVE_ACCEPTANCE_COST = 1.0;
    public static double MOVE_ACCEPTANCE_ALPHA_DECAY = 0.1;
    public static int LOWER_BOUND = 1;
    public static int UPPER_BOUND = 10;
    public static EHeuristicIDs[] SET_OF_SEARCH_HEURISTICS = {
            EHeuristicIDs.RANDOM_BIT_FLIP,
            EHeuristicIDs.SDHC,
            EHeuristicIDs.DBHC,
            EHeuristicIDs.ONE_POINT_CROSSOVER,
            EHeuristicIDs.UNIFORM_CROSSOVER,
    };
}

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyRepository {
    private static StrategyRepository instance;

    // Map to store results for combinations of strategies
    private final Map<String, int[][][]> results;

    // Map to store round histories for each game played
    private final Map<String, List<boolean[]>> gameHistories;

    private StrategyRepository() {
        results = new HashMap<>();
        gameHistories = new HashMap<>();
    }

    public static StrategyRepository getInstance() {
        if (instance == null) {
            instance = new StrategyRepository();
        }
        return instance;
    }

    public void addResult(String key, int[][][] data) {
        results.put(key, data);
    }

    public int[][][] getResult(String key) {
        return results.get(key);
    }

    public void addGameHistory(String key, List<boolean[]> history) {
        gameHistories.put(key, history);
    }

    public List<boolean[]> getGameHistory(String key) {
        return gameHistories.get(key);
    }
}

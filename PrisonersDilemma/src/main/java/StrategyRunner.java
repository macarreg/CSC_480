import java.util.ArrayList;
import java.util.List;

public class StrategyRunner {
    public static void runStrategies(List<Strategy> strategies, int[][] results, String keyPrefix) {
        int size = strategies.size();

        // Generate all unique pairs of strategies
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) { // Ensure no duplicates (e.g., (A, B) and (B, A))
                pairs.add(new int[]{i, j});
            }
        }

        // Iterate through pivot strategies and pairs
        for (int pivot = 0; pivot < size; pivot++) {
            Strategy pivotStrategy = strategies.get(pivot);

            for (int pairIndex = 0; pairIndex < pairs.size(); pairIndex++) {
                int[] pair = pairs.get(pairIndex);
                Strategy s1 = strategies.get(pair[0]);
                Strategy s2 = strategies.get(pair[1]);

                // Initialize scores
                int pivotScore = 0;

                // Run 200 rounds
                for (int round = 0; round < 200; round++) {
                    // Get decisions
                    boolean pivotMove = pivotStrategy.decide();
                    boolean s1Move = s1.decide();
                    boolean s2Move = s2.decide();

                    // Evaluate scores
                    boolean[] decisions = {pivotMove, s1Move, s2Move};
                    int[] scores = Scoring.getScores(decisions);

                    // Accumulate the pivot strategy's score
                    pivotScore += scores[0];
                }

                // Store the pivot's score against this pair in the results table
                results[pivot][pairIndex] = pivotScore;

                // Generate a key and store round history if needed
                String gameKey = KeyGenerator.generateKey(keyPrefix, pivot, pair[0], pair[1]);
                StrategyRepository.getInstance().addGameHistory(gameKey, null); // Replace null with actual history if needed
            }
        }
    }
}

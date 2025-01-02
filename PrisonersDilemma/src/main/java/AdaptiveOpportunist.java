import java.util.List;

public class AdaptiveOpportunist implements Strategy {
    private final String gameKey;

    public AdaptiveOpportunist(String gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public String getName() {
        return gameKey;
    }

    @Override
    public boolean decide() {
        List<boolean[]> history = StrategyRepository.getInstance().getGameHistory(gameKey);
        if (history == null || history.isEmpty()) {
            // First round: cooperate
            return true;
        }

        int historySize = history.size();
        int recentRounds = Math.min(5, historySize); // Analyze last 5 rounds
        double weightedDefectionScore = 0;
        double weightedCooperationScore = 0;

        // Weight recent rounds more heavily
        for (int i = 0; i < recentRounds; i++) {
            boolean[] round = history.get(historySize - 1 - i);
            double weight = (recentRounds - i) / (double) recentRounds;

            for (boolean move : round) {
                if (move) {
                    weightedCooperationScore += weight;
                } else {
                    weightedDefectionScore += weight;
                }
            }
        }

        boolean majorityDefecting = weightedDefectionScore > weightedCooperationScore;

        // Decide based on trends
        if (majorityDefecting) {
            // Defect if defection dominates
            return false;
        } else {
            // Periodic cooperation testing (dynamic frequency)
            double testFrequency = 0.05 + 0.05 * (historySize / 50.0); // Scales up with game length
            boolean testCooperation = Math.random() < testFrequency;
            if (testCooperation) {
                return true;
            }

            // Default to majority rule in the last round
            boolean[] lastRound = history.get(historySize - 1);
            int lastRoundCooperates = 0;
            for (boolean move : lastRound) {
                if (move) lastRoundCooperates++;
            }
            return lastRoundCooperates >= 2;
        }
    }
}


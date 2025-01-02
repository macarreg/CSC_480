import java.util.List;

public class CalculatedContender implements Strategy {
    private final String gameKey;

    public CalculatedContender(String gameKey) {
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
        int recentRounds = Math.min(5, historySize); // Look back up to 5 rounds
        int cooperateCount = 0;
        int defectCount = 0;

        for (int i = historySize - recentRounds; i < historySize; i++) {
            boolean[] round = history.get(i);
            for (boolean move : round) {
                if (move) cooperateCount++;
                else defectCount++;
            }
        }

        // Determine if revenge is needed
        boolean revengeMode = defectCount > cooperateCount * 1.5;

        // Randomized cooperation testing
        boolean testCooperation = Math.random() < 0.1; // 10% chance of cooperation

        if (revengeMode) {
            // Retaliate for a period before reconsidering
            return false;
        } else if (testCooperation) {
            // Random test for cooperative potential
            return true;
        } else {
            // Default to majority rule
            boolean[] lastRound = history.get(historySize - 1);
            int lastRoundCooperates = 0;
            for (boolean move : lastRound) {
                if (move) lastRoundCooperates++;
            }
            return lastRoundCooperates >= 2;
        }
    }
}


import java.util.List;

public class AdaptiveAltruist implements Strategy {
    private final String gameKey;

    public AdaptiveAltruist(String gameKey) {
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

        // Analyze recent history (last three rounds)
        int recentRounds = 3;
        int cooperateCount = 0;
        int defectCount = 0;

        for (int i = Math.max(0, history.size() - recentRounds); i < history.size(); i++) {
            boolean[] round = history.get(i);
            for (boolean move : round) {
                if (move) cooperateCount++;
                else defectCount++;
            }
        }

        // Check majority cooperation in the last round
        boolean[] lastRound = history.get(history.size() - 1);
        int lastRoundCooperates = 0;
        for (boolean move : lastRound) {
            if (move) lastRoundCooperates++;
        }

        // Decision rules
        if (defectCount > cooperateCount * 1.5) {
            // Protect against majority defection over recent rounds
            return false;
        } else if (lastRoundCooperates >= 2) {
            // Cooperate if the majority cooperated in the last round
            return true;
        } else {
            // Test cooperation periodically
            return history.size() % 10 == 0 || cooperateCount > defectCount;
        }
    }
}


import java.util.List;

public class TitForTat implements Strategy {
    private final String gameKey;

    public TitForTat(String gameKey) {
        this.gameKey = gameKey;
    }
    @Override
    public String getName() {
        return gameKey;
    }

    @Override
    public boolean decide() {
        List<boolean[]> history = StrategyRepository.getInstance().getGameHistory(gameKey);
        if (history == null) return true;

        boolean[] lastRound = history.get(history.size() - 1);
        int complies = 0;

        for (boolean move : lastRound) {
            if (move) complies++;
        }

        return complies >= 2; // Mimic majority decision from the last round
    }
}

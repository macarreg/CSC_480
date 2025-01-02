import java.util.List;

public class StingyTitForTat implements Strategy{
    private final String gameKey;

    public StingyTitForTat(String gameKey) {
        this.gameKey = gameKey;
    }
    @Override
    public String getName() {
        return gameKey;
    }
    @Override
    public boolean decide() {
        List<boolean[]> history = StrategyRepository.getInstance().getGameHistory(gameKey);
        if (history == null) return false;

        boolean[] lastRound = history.get(history.size() - 1);
        if (lastRound.length == 0) return false;

        // complies 5/8 times
        return (lastRound[0] & lastRound[1]) & lastRound[2];
    }
}

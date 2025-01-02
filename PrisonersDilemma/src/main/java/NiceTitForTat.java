import java.util.List;

public class NiceTitForTat implements Strategy{
    private final String gameKey;

    public NiceTitForTat(String gameKey) {
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
        if (lastRound.length == 0) return true;

        // complies 5/8 times
        return (lastRound[0] | lastRound[1]) | lastRound[2];
    }
}

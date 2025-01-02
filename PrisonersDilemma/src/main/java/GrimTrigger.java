import java.util.List;

public class GrimTrigger implements Strategy{
    private final String gameKey;

    public GrimTrigger(String gameKey) {
        this.gameKey = gameKey;
    }
    private boolean trigger = false;

    @Override
    public String getName() {
        return gameKey;
    }


    @Override
    public boolean decide() {
        List<boolean[]> history = StrategyRepository.getInstance().getGameHistory(gameKey);
        if (history == null) return true;

        boolean[] lastRound = history.get(history.size() - 1);

        for (boolean move : lastRound) {
            if (!move) trigger = true;
        }
        if (trigger) return false;
        return true;
    }
}

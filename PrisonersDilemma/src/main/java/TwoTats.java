import java.util.List;

public class TwoTats implements Strategy {
    private final String gameKey;

    public TwoTats(String gameKey) {
        this.gameKey = gameKey;
    }
    @Override
    public String getName() {
        return gameKey;
    }

    @Override
    public boolean decide() {
        List<boolean[]> history = StrategyRepository.getInstance().getGameHistory(gameKey);
        if (history == null || history.size() <= 2) return true;
        boolean[] lastRound = history.get(history.size() - 1);
        boolean[] secondLast = history.get(history.size() - 2);
        boolean defect1 = false;
        boolean defect2 = false;

        for (boolean move : lastRound) {
            if (!move) defect1 = true;
        }
        for (boolean move :secondLast) {
            if (!move) defect2 = true;
        }

        return !(defect1 && defect2); // only defects when last two rounds defect
    }
}

import java.util.List;

public class AlwaysComply implements Strategy{
    private final String gameKey;

    public AlwaysComply(String gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public String getName() {
        return gameKey;
    }
    @Override
    public boolean decide() {
        return true;
    }
}


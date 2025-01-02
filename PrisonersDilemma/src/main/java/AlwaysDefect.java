import java.util.List;

public class AlwaysDefect implements Strategy{
    private final String gameKey;

    public AlwaysDefect(String gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public String getName() {
        return gameKey;
    }

    @Override
    public boolean decide() {
        return false;
    }
}

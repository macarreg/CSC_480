import java.util.Random;

public class RandomChoice implements Strategy{
    private final String gameKey;

    public RandomChoice(String gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public String getName() {
        return gameKey;
    }

    @Override
    public boolean decide() {
        Random random = new Random();
        return random.nextBoolean();
    }
}

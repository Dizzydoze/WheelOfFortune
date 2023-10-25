import java.util.Random;

public class WheelOfFortuneRandomAIPlayer implements WheelOfFortunePlayer{

    String playerId;

    public WheelOfFortuneRandomAIPlayer(){
        this.playerId = "randomAI";
    }
    @Override
    public char nextGuess() {
        // simply return random chars
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    @Override
    public String playerId() {
        return this.playerId;
    }

    @Override
    public void reset() {

    }
}

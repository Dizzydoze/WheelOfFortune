import java.util.Random;

/**
 * Concrete class for Ramdom AI Player with its own guessing strategy
 */
public class WheelOfFortuneRandomAIPlayer implements WheelOfFortunePlayer{

    String playerId;

    /**
     * Constructor, setting id to randomAI
     */
    public WheelOfFortuneRandomAIPlayer(){
        this.playerId = "randomAI";
    }

    /**
     * Get a new guess from its strategy
     * @return char single new character
     */
    @Override
    public char nextGuess() {
        // simply return random chars
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    /**
     * Get its own player id
     * @return String current player id
     */
    @Override
    public String playerId() {
        return this.playerId;
    }

    /**
     * reset data members if needed
     */
    @Override
    public void reset() {

    }
}

/**
 * WheelOfFortunePlayer Interface
 * This interface should define the following methods needed by all WheelOfFortunePlayers:
 *
 * Define At least 3 Concrete implementations of WheelOfFortunePlayer
 * Code three implementations of the WheelOfFortunePlayer interface.
 */
public interface WheelOfFortunePlayer {
    /**
     * char nextGuess() — get the next guess from the player
     */
    public char nextGuess();

    /**
     * String playerId() — an id for the player
     */
    public String playerId();

    /**
     * void reset() — reset the player to start a new game
     */
    public void reset();
}

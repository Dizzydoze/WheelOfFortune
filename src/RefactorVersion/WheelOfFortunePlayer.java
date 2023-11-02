/**
 * WheelOfFortunePlayer Interface
 * Define At least 3 Concrete implementations of WheelOfFortunePlayer
 * Code three implementations of the WheelOfFortunePlayer interface.
 */
public interface WheelOfFortunePlayer {
    /**
     * char nextGuess() — get the next guess from the player
     * @return char single guess character from the player
     */
    char nextGuess();

    /**
     * String playerId() — an id for the player
     * @return String the player id
     */
    String playerId();

    /**
     * void reset() — reset the player to start a new game
     */
    void reset();
}

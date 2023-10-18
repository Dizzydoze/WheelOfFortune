/**
 * WheelOfFortuneUserGame, a concrete extension of WheelOfFortune.
 * This class should override the “getGuess” method of the abstract WheelOfFortune class, using Scanner to get the guess.
 * WheelOfFortuneUserGame should allow the user to play each game with a random phrase,
 * and if there are more phrases, ask after the game if the player wants to continue.
 * Once a particular phrase is used, it should be discarded from the phrase list so it isn’t chosen again.
 *
 * For both WheelOfFortunedUserGame and WheelOfFortuneAIGame,
 * the main function should just call playAll() to play a set of games, like in the code below:
 * public static void main(String [] args) {
 *    HangmanUserGame hangmanUserGame = new HangmanUserGame();
 *    GamesRecord record = hangmanUserGame.playAll();
 *    System.out.println(record);  // or call specific functions of record
 * }
 */
public class WheelOfFortuneUserGame extends WheelOfFortune{

    @Override
    public char getGuess(String previousGuesses) {
        return 0;
    }

    /**
     * The class should implement the play() and playNext() methods from Game in order to make these things happen.
     */
    @Override
    public GameRecord play() {
        return null;
    }

    /**
     * The class should implement the play() and playNext() methods from Game in order to make these things happen.
     */
    @Override
    public boolean playNext() {
        return false;
    }

    public static void main(String[] args) {

    }
}

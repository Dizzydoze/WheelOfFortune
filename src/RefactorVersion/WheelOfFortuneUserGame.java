import java.util.Objects;
import java.util.Scanner;

/**
 * WheelOfFortuneUserGame, a concrete extension of WheelOfFortune.
 * WheelOfFortuneUserGame should allow the user to play each game with a random phrase,
 * and if there are more phrases, ask after the game if the player wants to continue.
 * Once a particular phrase is used, it should be discarded from the phrase list, so it isn’t chosen again.
 */
public class WheelOfFortuneUserGame extends WheelOfFortune{


    public WheelOfFortuneUserGame(){

    }

    /**
     * This class should override the “getGuess” method of the abstract WheelOfFortune class
     * using Scanner to get the guess
     */
    @Override
    public char getGuess(String previousGuesses) {
        Scanner scanner = new Scanner(System.in);

        // make sure the input is legal
        try {
            String input = scanner.nextLine();

            // check for validation
            if (!Character.isLetter(input.charAt(0))) {
                System.out.println("[TRY AGAIN] Only English Letter is Allowed!");
                return ' ';
            } else if (input.length() != 1) {
                System.out.println("[TRY AGAIN] Exact ONE Character for Each Guess!");
                return ' ';
            } else if (this.previousGuesses.contains(Character.toString(input.charAt(0)))) {
                System.out.println("[TRY AGAIN] You've already guessed it before.");
                return ' ';
            } else{
                // only add to buffer if it is valid and never been added before
                this.previousGuesses.add(input);
                System.out.println("[GUESS BUFFER] " + this.previousGuesses);
                return input.charAt(0);
            }
        }catch (StringIndexOutOfBoundsException e){
            return ' ';
        }
    }

    /**
     * AllGamesRecord playAll()— a method that plays a set of games and records
     * and returns an AllGamesRecord object for the set.
     */
    @Override
    public AllGamesRecord playAll() {
        return null;
    }

    /**
     * The class should implement the play() method from Game in order to make these things happen.
     * GameRecord play()-- plays a game and returns a GameRecord
     */
    @Override
    public GameRecord play() {
        return null;
    }

    /**
     * The class should implement the playNext() method from Game in order to make these things happen.
     * boolean playNext() -- asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * The function should return a boolean.
     */
    @Override
    public boolean playNext() {
        return false;
    }

    /**
     *  For both WheelOfFortuneUserGame and WheelOfFortuneAIGame,
     *  the main function should just call playAll() to play a set of games
     *  WheelOfFortuneUserGame should allow the user to play until they quit or run out of phrases.
     */
    public static void main(String[] args) {
        WheelOfFortuneUserGame wheelOfFortuneUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord allGamesRecord = wheelOfFortuneUserGame.playAll();
        System.out.println(allGamesRecord);
    }
}

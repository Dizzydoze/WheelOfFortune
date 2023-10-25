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
        super.readPhrases();                        // initiate and generate phraseList
    }

    @Override
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while(!this.phraseList.isEmpty()){          // check if there are still phrases to guess, ask for next game
            if (!this.playNext()){                  // quit if user choose to stop
                System.out.println("User quited the game.");
                return allGamesRecord;
            }
            else{                                   // user choose to play the next game
                GameRecord gameRecord = this.play();
                allGamesRecord.add(gameRecord);     // add record to the record list
            }
        }
        System.out.println("All Games have been completed.");
        return allGamesRecord;                      // return all records if run out of phrases
    };

    /**
     * This class should override the “getGuess” method of the abstract WheelOfFortune class
     * using Scanner to get the guess
     */
    @Override
    public char getGuess(String previousGuesses) {
        Scanner scanner = new Scanner(System.in);

        // keep retry until input is valid
        try {
            String input = scanner.nextLine();
            // check for validation, make sure the input is legal
            if (!Character.isLetter(input.charAt(0))) {
                System.out.println("[TRY AGAIN] Only English Letter is Allowed!");
                return ' ';
            } else if (input.length() != 1) {
                System.out.println("[TRY AGAIN] Exact ONE Character for Each Guess!");
                return ' ';
            } else if (previousGuesses.contains(Character.toString(input.charAt(0)))) {
                System.out.println("[TRY AGAIN] You've already guessed it before.");
                return ' ';
            } else{
                return input.charAt(0);     // legal input, return and process
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return ' ';
    }

    /**
     * The class should implement the play() method from Game in order to make these things happen.
     * GameRecord play()-- plays a game and returns a GameRecord
     */
    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        String previousGuesses = "";                            // store previous guess characters
        super.randomPhrase();                                   // get a new random phrase from the phraseList
        super.generateHiddenPhrase();                           // generate a hiddenPhrase base on the new phrase
        while (true){
            if (!(gameRecord.score > 0)){                          // lose the game if score turns to 0
                System.out.println("You Lose![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);             // remove the finished game from phraseList
                return gameRecord;
            }
            if (this.hiddenPhrase.indexOf("*") == -1){
                System.out.println("You Win![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);             // remove the finished game from phraseList
                return gameRecord;
            }

            char guess = this.getGuess(previousGuesses);        // get new guess character
            if (!Character.isWhitespace(guess)){
                previousGuesses += guess;                       // add it into buffer
//                System.out.println("[GUESS BUFFER] " + previousGuesses);
                if (!super.processGuess(guess)){
                    gameRecord.score -= 1;
                    System.out.println("[BAD GUESS]Score -1 ! " + "[PlayerId]"
                            + gameRecord.playerId + "[Current Score] " + gameRecord.score);
                }
                else{
                    System.out.println("[GOOD GUESS][Current Hidden Phrase] " + this.hiddenPhrase);
                }
            }
        }
    }

    /**
     * The class should implement the playNext() method from Game in order to make these things happen.
     * boolean playNext() -- asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * The function should return a boolean.
     */
    @Override
    public boolean playNext() {
        while (true){
            System.out.println("Are you ready for the next game? <Y/N>");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input, "Y") || Objects.equals(input, "y")){
                return true;
            }
            else if (Objects.equals(input, "N") || Objects.equals(input, "n")) {
                return false;
            }
        }
    }

    /**
     *  For both WheelOfFortuneUserGame and WheelOfFortuneAIGame,
     *  the main function should just call playAll() to play a set of games
     *  WheelOfFortuneUserGame should allow the user to play until they quit or run out of phrases.
     */
    public static void main(String[] args) {
        WheelOfFortuneUserGame wheelOfFortuneUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord allGamesRecord = wheelOfFortuneUserGame.playAll();
        if (allGamesRecord.arr.isEmpty()){
            System.out.println("No Games were played.");
        }
        else{
            int rank = 1;
            for (int score: allGamesRecord.highGameList("AutoGenUserPlayer", allGamesRecord.arr.size())){
                System.out.println("[AutoGenUserPlayer]" + "TOP " + rank + ":" + score);
                rank ++;
            }
            System.out.println("[AutoGenUserPlayer]Average Score: "+ allGamesRecord.average("AutoGenUserPlayer"));
        }
    }
}

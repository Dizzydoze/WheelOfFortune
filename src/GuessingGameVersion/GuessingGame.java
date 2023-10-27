import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * ## Part 2. Mastermind and WheelOfFortune are Similar Games
 * Mastermind and WheelOfFortune are two games with similarities.
 * The task for part 2 is to design and code a user-based Mastermind game and re-factor WheelOfFortune
 * so that as much of the code as possible for the two games is placed in a superclass named “GuessingGame”.
 * Create a new project and repo for part 2 and begin with your code from part 1.
 * The goal of part 2 is to encapsulate code within GuessingGame
 * so that it can be reused in WheelOfFortune and Mastermind
 */
public abstract class GuessingGame extends Game{

    List<String> phraseList = new ArrayList<>();        // list of phrases we are going to guess
    String phrase = "";                                 // the phrase we need to guess
    StringBuilder hiddenPhrase;                         // covered phrase generated base on the guess phrase
    int index;                                          // index of current guess phrase
    final String colors="RGYBOP";
    StringBuilder phraseSB;                             // for MasterMind processGuess
    StringBuilder guessSB = new StringBuilder();        // for MasterMind processGuess
    StringBuilder roundResult = new StringBuilder();    // save results for each round

    /**
     * read all the phrases in file and store them into phraseList for picking
     */
    public void readPhrases(){
        try{
            if (this instanceof WheelOfFortuneGame){
                this.phraseList = Files.readAllLines(Paths.get("/Users/single/workspaces/WheelOfFortune/src/phrases.txt"));
            } else if (this instanceof MasterMindGame) {
                // generate secret color combination
                int phraseCount = 3;
                String currentPhrase = "";
                Random random = new Random();
                for (int i = 0; i < phraseCount; i++) {
                    for (int j = 0; j < 4; j++) {
                        int ranInt = random.nextInt(6);     // allow duplicates
                        currentPhrase += colors.charAt(ranInt);
                    }
                    this.phraseList.add(currentPhrase);
                    currentPhrase = "";
                }
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * randomPhrase: a single phrase randomly chosen from a list
     */
    public void randomPhrase() {
        Random random = new Random();
        this.index = random.nextInt(this.phraseList.size());
        this.phrase = this.phraseList.get(this.index);
        System.out.println("[Phrase] " + this.phrase);
        if (this instanceof MasterMindGame) {this.phraseSB = new StringBuilder(this.phrase);}   // secretSB is the same as secret at the beginning
    }

    /**
     * Generate the initial hidden phrase covered by coverSign
     */
    public void generateHiddenPhrase(String coverSign){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.phrase.length(); i++) {
            char c = this.phrase.charAt(i);
            if(Character.isLetter(c)){
                sb.append(coverSign);
            }else{
                sb.append(c);
            }
        }
        this.hiddenPhrase = sb;
        System.out.println("[HIDDEN PHRASE] " + this.hiddenPhrase);
    }

    /**
     * Get guess from command line input
     * @param previousGuesses buffer for checking input duplicates
     * @return string of valid guess input
     */
    public String getGuess(String previousGuesses){
        Scanner scanner = new Scanner(System.in);
        // while exits on boolean
        while (true){
            if (this instanceof MasterMindGame){
                System.out.printf("Please enter a 4 letter guess from [%s]:\n", colors);
                String input = scanner.nextLine();
                // check input && buffer
                if (this.checkValidMasterMind(input)){
                    this.guessSB = new StringBuilder(input);    // create guessSB for partial check
                    return input;
                }
            }
            else if (this instanceof WheelOfFortuneGame) {
                String input = scanner.nextLine();
                // check input && buffer
                if (this.checkValidWOF(input) && !this.checkBuffer(previousGuesses, input)){
                    return input;
                }
            }
        }
    }

    private boolean checkBuffer(String previousGuesses, String input){
        if (previousGuesses.contains(input)){
            System.out.println("[TRY AGAIN] You've already guessed it before.");
            return true;
        }
        return false;
    }

    /**
     * Helper method, only use to check validation of input for MasterMind game.
     * @param input input from the command line
     * @return boolean
     */
    private boolean checkValidMasterMind(String input){
        // check length validation
        if (input.length() != 4) {
            System.out.println("[TRY AGAIN]Exact 4 Letters Each Guess!");
            return false;
        }
        // check color validation
        for (int i = 0; i < input.length(); i++) {
            if (colors.indexOf(input.charAt(i)) == -1){
                System.out.println("[TRY AGAIN]Only Colors within " + colors + " Allowed!");
                return false;
            }
        }
        // check letter validation
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetter(input.charAt(i))) {
                System.out.println("[TRY AGAIN]Only English Letters Allowed!");
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method, only use to check validation of input for WheelOfFortune game.
     * @param input input from the command line
     * @return boolean
     */
    private boolean checkValidWOF(String input){
        // check for validation
        if (!Character.isLetter(input.charAt(0))) {
            System.out.println("[TRY AGAIN] Only English Letter is Allowed!");
            return false;
        } else if (input.length() != 1) {
            System.out.println("[TRY AGAIN] Exact ONE Character for Each Guess!");
            return false;
        }
        return true;
    }

    public abstract boolean processGuess(String input);

    /**
     * Loop through the phraseList and try to play all of them.
     * @return AllGamesRecord, a list of gameRecords for each of the game
     */
    @Override
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        this.readPhrases();
        // use while loop to avoid concurrent modification exception
        while (!this.phraseList.isEmpty()){
            this.randomPhrase();                    // get a random phrase base on the size of phraseList
            GameRecord gameRecord = this.play();
            allGamesRecord.add(gameRecord);
            if (!this.playNext()){return allGamesRecord;}
        }
        System.out.println("You've Completed All the Games!");
        return allGamesRecord;
    }

    /**
     * Play a single game.
     * @return  GameRecord for a single game
     */
    @Override
    public GameRecord play() {
        this.roundResult = new StringBuilder();                  // reset the roundResult for each new game
        GameRecord gameRecord = new GameRecord();
        String previousGuesses = "";                             // store previous guess characters
        this.generateHiddenPhrase("*");                 // generate a hiddenPhrase base on the new phrase
        while (true){
            if (!(gameRecord.score > 80)){                       // lose the game if score turns to 80
                System.out.println("You Lose![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);              // remove the finished game from phraseList
                return gameRecord;
            }
            if (this instanceof WheelOfFortuneGame && this.hiddenPhrase.indexOf("*") == -1){
                System.out.println("You Win![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);               // remove the finished game from phraseList
                return gameRecord;
            }
            String input = this.getGuess(previousGuesses);        // get new guess character
            previousGuesses += input;                             // add new input into buffer

            boolean processResult = this.processGuess(input);
            if (this instanceof MasterMindGame && processResult){
                System.out.println("You Win![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                return gameRecord;
            }

            if (!processResult){
                gameRecord.score -= 1;
                System.out.println("[BAD GUESS]Score -1! " + "[PlayerId]"
                        + gameRecord.playerId + "[Current Score]" + gameRecord.score);
            }
            else{
                System.out.println("[GOOD GUESS][Current Hidden Phrase] " + this.hiddenPhrase);
            }
        }
    }

    /**
     * boolean playNext(): asks if the next game should be played
     * this will be called even to check if the first game should be played.
     * @return boolean
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
}

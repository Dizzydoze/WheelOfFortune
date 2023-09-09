import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * **version 4: WheelOfFortuneBot**
 * Modify your version 3 so that the game plays the same but the player is a bot.
 * - There is no user input-- when you run the app, it runs through the game showing the bot's guesses and the system's feedback.
 * - You can  points by just creating a not that intelligent bot player,
 * e.g., one that guesses 'a', then 'b', then 'c', and so on, until they win or lose.
 * Implement the simple player first, then strategize and make it better for full or even extra credit.
 */
public class WheelOfFortuneBot {

    String phrase;
    StringBuilder hiddenPhrase;
    Set<String> previousGuesses = new HashSet<>();

    public String botGuess() throws InterruptedException {
        System.out.println("[BOT GUESSING]...");
        // strategy 1 try to guess based on frequency
        String[] orderedString = {"e", "t", "a", "o", "i", "n", "s", "h", "r", "l",
                "u", "d", "c", "m", "f", "w", "y", "g", "p", "b", "v", "k", "j", "x", "q", "z"};
        for (int i = 0; i < orderedString.length; i++) {
            String guess = orderedString[i];
            // strategy 2: check the buffer, never guess same char twice.
            if (!this.previousGuesses.contains(guess)){
                // add to buffer
                this.previousGuesses.add(guess);
                // pretend to be human
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(3));
                return guess;
            }
        }
        return "";
    }

    public void randomPhrase() {
        // randomPhrase -- returns a single phrase randomly chosen from a list
        List<String> phraseList = null;
        try{
            phraseList = Files.readAllLines(Paths.get("./../phrases.txt"));
        } catch (IOException e){
            System.out.println(e);
        }
        Random rand = new Random();
        int r = rand.nextInt(3);
        this.phrase =  phraseList.get(r);
    }

    public void generateHiddenPhrase(){
        // generateHiddenPhrase -- returns the initial hidden phrase
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.phrase.length(); i++) {
            char c = this.phrase.charAt(i);
            if(Character.isLetter(c)){
                sb.append("*");
            }else{
                sb.append(c);
            }
        }
        this.hiddenPhrase = sb;
        System.out.println("[HIDDEN PHRASE] " + this.hiddenPhrase);
    }

    public boolean processGuess(String guess){
        //processGuess -- returns whether a letter matches, and modifies the partially hidden phrase if there is a match.
        if (this.phrase.indexOf(guess.charAt(0)) == -1 && this.phrase.indexOf(guess.toUpperCase().charAt(0)) == -1) {
            return false;
        }else{
            // modify the hidden phrase
            for (int i = 0; i < this.phrase.length(); i++) {
                if (this.phrase.charAt(i) == guess.charAt(0)){
                    this.hiddenPhrase.setCharAt(i, guess.charAt(0));
                } else if (this.phrase.charAt(i) == guess.toUpperCase().charAt(0)) {
                    this.hiddenPhrase.setCharAt(i, guess.toUpperCase().charAt(0));
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Initiate instance of the game
        WheelOfFortuneBot game = new WheelOfFortuneBot();

        // get random phrase
        game.randomPhrase();
        // generate hidden phrase
        game.generateHiddenPhrase();
        // initiate chances
        int chances = 20;

        while (true){
            if (chances < 1) {
                System.out.println("[GAME OVER]");
                return;
            }
            if (game.hiddenPhrase.indexOf("*") == -1){
                System.out.println("[BOT WINS]!!!!!");
                System.out.println("[ANSWER] " + game.phrase);
                return;
            }
            // get guess letter
            String guessLetter = game.botGuess();
            if (!guessLetter.isEmpty()) {
                // process guess
                if (!game.processGuess(guessLetter)) {
                    chances -= 1;
                    System.out.println("[BAD GUESS] " + guessLetter + " Chance - 1! [CHANCE REMAIN] " + chances);
                } else{
                    System.out.println("[GOOD GUESS] " + guessLetter + " [Current Hidden Phrase] " + game.hiddenPhrase);
                }
            }
        }
    }
}

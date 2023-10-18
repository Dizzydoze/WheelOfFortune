import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * version 3, WheelOfFortuneObject: provide the same methods as in version 2, but define them as instance methods.
 * Define data members within the class for "phrase", "hiddenPhrase", and "previousGuesses", and use those in your code.
 * Because of the data members, you will have fewer parameters in your methods for this version.
 */
public class WheelOfFortuneObject {

    String phrase;
    StringBuilder hiddenPhrase;
    Set<String> previousGuesses = new HashSet<>();
    int chances = 5;
    Boolean vowelsPicked = false;

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

    public String getGuess() {
        // getGuess-- gets input from user and returns it.
        Scanner scanner = new Scanner(System.in);
        if (this.vowelsPicked){
            System.out.println("Guess A Letter: [Enter 'entire' to Guess Entire Phrase]");
        }else{
            System.out.println("Guess A Letter: [Enter 'vowel' to Buy a Vowel][Enter 'entire' to Guess Entire Phrase]");
        }
        // make sure the input is legal
        try {
            String input = scanner.nextLine();
            if (Objects.equals(input, "vowel")){
                this.buyAVowel();
                return "";
            }

            if (Objects.equals(input, "entire")){
                this.guessEntirePhrase();
                return "";
            }

            // check for validation
            if (!Character.isLetter(input.charAt(0))) {
                System.out.println("[TRY AGAIN] Only English Letter is Allowed!");
                return "";
            } else if (input.length() != 1) {
                System.out.println("[TRY AGAIN] Exact ONE Character for Each Guess! [CHANCES REMAIN] " + this.chances);
                return "";
            } else if (this.previousGuesses.contains(Character.toString(input.charAt(0)))) {
                System.out.println("[TRY AGAIN] You've already guessed it before. [CHANCES REMAIN] " + this.chances);
                return "";
            } else{
                // only add to buffer if it is valid and never been added before
                this.previousGuesses.add(input);
                System.out.println("[GUESS BUFFER] " + this.previousGuesses);
                return input;
            }
        }catch (StringIndexOutOfBoundsException e){
            return "";
        }
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

    public void buyAVowel() {
        if (this.vowelsPicked) {
            System.out.println("You Already Got One! No More Vowels!");
            return;
        }
        System.out.println("Select one vowel from \"[a, e, i, o, u]\". Only one chance, pick carefully!");
        Scanner scanner = new Scanner(System.in);
        String vowel = scanner.nextLine();
        for (int i = 0; i < this.phrase.length(); i++) {
            if (this.phrase.charAt(i) == vowel.charAt(0)){
                this.hiddenPhrase.setCharAt(i, vowel.charAt(0));
            }
        }
        System.out.println("[Vowels Bought][Current Hidden Phrase] " + this.hiddenPhrase);
        this.vowelsPicked = true;
    }

    public void guessEntirePhrase(){
        // guess entire phrase for only one chance, game over if wrong
        System.out.println("[ENTIRE PHRASE] Only ONE Chance, Win or Lose!");
        Scanner scanner = new Scanner(System.in);
        String entirePhrase = scanner.nextLine();
        if (Objects.equals(entirePhrase.toLowerCase(), this.phrase.toLowerCase())){
            for (int i = 0; i < this.phrase.length(); i++) {
                this.hiddenPhrase.setCharAt(i, this.phrase.charAt(i));  //review the whole sentence
            }
        } else {
            this.chances = 0;
        }
    }


    public static void main(String[] args){
        // Initiate instance of the game
        WheelOfFortuneObject game = new WheelOfFortuneObject();

        // get random phrase
        game.randomPhrase();
        // generate hidden phrase
        game.generateHiddenPhrase();

        while (true){
            if (game.chances < 1) {
                System.out.println("[GAME OVER] Correct Answer: " + game.phrase);
                return;
            }
            if (game.hiddenPhrase.indexOf("*") == -1){
                System.out.println("[YOU WIN]!!!!!");
                System.out.println("[ANSWER] " + game.phrase);
                return;
            }
            // get guess letter
            String guessLetter = game.getGuess();
            if (!guessLetter.isEmpty()) {
                // process guess
                if (!game.processGuess(guessLetter)) {
                    game.chances -= 1;
                    System.out.println("[BAD GUESS] Chance - 1! [CHANCE REMAIN] " + game.chances);
                } else{
                    System.out.println("[GOOD GUESS][Current Hidden Phrase] " + game.hiddenPhrase);
                }
            }
        }
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Abstract class WheelOfFortune, extends Game.
 * This class will have much of the code from your existing WheelOfFortune class,
 * including readPhrases, randomPhrase, getHiddenPhrase, and processGuess.

 * And of course WheelOfFortune needs to implement the abstract methods in its parent Game.
 */
public abstract class WheelOfFortune extends Game {

    /**
     * It should also define an abstract method getGuess(String previousGuesses),
     * which returns a char, thus requiring all concrete WheelOfFortune games to implement it.
     */
    public abstract char getGuess(String previousGuesses);

    // TODO: readPhrases, read all the phrases in our file and return list maybe.


    /**
     * randomPhrase -- returns a single phrase randomly chosen from a list
     */
    public void randomPhrase() {
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

    /**
     * generateHiddenPhrase -- returns the initial hidden phrase
     */
    public void generateHiddenPhrase(){
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


    /**
     * processGuess -- returns whether a letter matches, and modifies the partially hidden phrase if there is a match.
     */
    public boolean processGuess(String guess){
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

}

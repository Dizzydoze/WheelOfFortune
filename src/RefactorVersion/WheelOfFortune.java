import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Abstract class WheelOfFortune, extends Game.
 * This class will have much of the code from your existing WheelOfFortune class,
 * including readPhrases, randomPhrase, getHiddenPhrase, and processGuess.
 * And of course WheelOfFortune needs to implement the abstract methods in its parent Game.
 */
public abstract class WheelOfFortune extends Game {

    List<String> phraseList;
    int index;  // index of current guessing phrase
    String phrase;
    StringBuilder hiddenPhrase;

    /**
     * It should also define an abstract method getGuess(String previousGuesses),
     * which returns a char, thus requiring all concrete WheelOfFortune games to implement it.
     */
    public abstract char getGuess(String previousGuesses);

    /**
     * read phrases from a local file and save the content to our data member list
     */
    public void readPhrases(){
        try{
            this.phraseList = Files.readAllLines(Paths.get("src/phrases.txt"));
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * randomPhrase -- get a single phrase randomly chosen from a list and save it to phrase data member
     */
    public void randomPhrase() {
        Random rand = new Random();
        this.index = rand.nextInt(this.phraseList.size());
        this.phrase =  this.phraseList.get(this.index);
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
     * @return boolean whether the guess is a good guess or a bad guess
     */
    public boolean processGuess(char guess){
        if (this.phrase.indexOf(guess) == -1 && this.phrase.indexOf(Character.toUpperCase(guess)) == -1
                && this.phrase.indexOf(Character.toLowerCase(guess)) == -1) {
            return false;
        }else{
            // modify the hidden phrase
            for (int i = 0; i < this.phrase.length(); i++) {
                if (this.phrase.charAt(i) == guess){
                    this.hiddenPhrase.setCharAt(i, guess);
                } else if (this.phrase.charAt(i) == Character.toUpperCase(guess)) {
                    this.hiddenPhrase.setCharAt(i, Character.toUpperCase(guess));
                } else if (this.phrase.charAt(i) == Character.toLowerCase(guess)){
                    this.hiddenPhrase.setCharAt(i, Character.toLowerCase(guess));
                }
            }
            return true;
        }
    }
}

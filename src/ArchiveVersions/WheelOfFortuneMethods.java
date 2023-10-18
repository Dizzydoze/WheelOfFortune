import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * **version 2, WheelOfFortuneMethods**:
 * In this version, provide the following **static** methods, but **don't define any data members**:
 * - randomPhrase -- returns a single phrase randomly chosen from a list
 * - generateHiddenPhrase -- returns the initial hidden phrase
 * - getGuess-- gets input from user and returns it.
 * - processGuess -- returns whether a letter matches, and modifies the partially hidden phrase if there is a match.
 * Please use these exact method names along with appropriate parameters.
 */
public class WheelOfFortuneMethods {
    public static void main(String[] args) {
        // get random phrase from files
        String rp = randomPhrase();
        System.out.println(rp);

        // generate hidden phrase
        StringBuilder hp = generateHiddenPhrase(rp);
        System.out.println(hp);

        // Initiate guess buffer
        Set<String> guessBuffer = new HashSet<>();
        // Initiate chances
        int chances = 10;

        while (true){
            if (chances < 0) {
                System.out.println("[GAME OVER]");
                return;
            }
            if (hp.indexOf("*") == -1){
                System.out.println("[YOU WIN]!!!!!");
                System.out.println("[ANSWER] " + rp);
                return;
            }

            String guess = getGuess(guessBuffer, chances);
            if (!guess.isEmpty()) {
                // process guess
                if (!processGuess(guess, rp, hp)) {
                    chances -= 1;
                    System.out.println("[BAD GUESS] Chance - 1! [CHANCE REMAIN] " + chances);
                } else{
                    System.out.println("[GOOD GUESS][Current Hidden Phrase] " + hp);
                }
            }
        }
    }

    public static String randomPhrase() {
        // randomPhrase -- returns a single phrase randomly chosen from a list
        List<String> phraseList = null;
        try{
            phraseList = Files.readAllLines(Paths.get("./../phrases.txt"));
        } catch (IOException e){
            System.out.println(e);
        }
        Random rand = new Random();
        int r = rand.nextInt(3);
        return phraseList.get(r);
    }

    public static StringBuilder generateHiddenPhrase(String rp){
        // generateHiddenPhrase -- returns the initial hidden phrase
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rp.length(); i++) {
            char c = rp.charAt(i);
            if(Character.isLetter(c)){
                sb.append("*");
            }else{
                sb.append(c);
            }
        }
        return sb;
    }

    public static String getGuess(Set<String> guessBuffer, int chances) {
        // getGuess-- gets input from user and returns it.

        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess A Letter: ");
        String input = scanner.nextLine();

        // check for validation
        if (!Character.isLetter(input.charAt(0))) {
            System.out.println("[TRY AGAIN] Only English Letter is Allowed!");
            return "";
        } else if (input.length() != 1) {
            System.out.println("[TRY AGAIN] Only ONE Character for Each Guess! [CHANCES REMAIN] " + chances);
            return "";
        } else if (guessBuffer.contains(Character.toString(input.charAt(0)))) {
            System.out.println("[TRY AGAIN] You've already guessed it before. [CHANCES REMAIN] " + chances);
            return "";
        } else{
            // only add to buffer if it is valid and never been added before
            guessBuffer.add(input);
            System.out.println("[GUESS BUFFER] " + guessBuffer);
            return input;
        }

    }

    public static boolean processGuess(String guess, String rp, StringBuilder hp){
        //processGuess -- returns whether a letter matches, and modifies the partially hidden phrase if there is a match.
        if (rp.indexOf(guess.charAt(0)) == -1 && rp.indexOf(guess.toUpperCase().charAt(0)) == -1) {
            return false;
        }else{
            // modify the hidden phrase
            for (int i = 0; i < rp.length(); i++) {
                if (rp.charAt(i) == guess.charAt(0)){
                    hp.setCharAt(i, guess.charAt(0));
                } else if (rp.charAt(i) == guess.toUpperCase().charAt(0)) {
                    hp.setCharAt(i, guess.toUpperCase().charAt(0));
                }
            }
            return true;
        }
    }
}

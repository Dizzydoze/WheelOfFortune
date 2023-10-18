import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * version 1, WheelOfFortuneMain: Write the program within a single “main” method.
 * Do not define other methods other than main. Name the class HangmanMain.
 */
public class WheelOfFortuneMain {

    public static void main(String[] args) {
        List<String> phraseList=null;

        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("./../phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        String phrase = phraseList.get(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))){
                sb.append("*");
            }
            else{
                sb.append(phrase.charAt(i));
            }
        }
        System.out.println("[Encrypt] " + sb);

        // Initiating chances
        int chances = 5;

        // Buffer the guessed string
        Set<String> guessBuffer = new HashSet<>();

        while (true){
            // Check remaining chances first
            if (chances < 0) {
                System.out.println("[GAME OVER] YOU LOST!");
                break;
            }

            // Interactively input the guessed letter
            System.out.println("[GUESS BUFFER] " + guessBuffer);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Guess A Letter: ");
            String input = scanner.nextLine();

            // Assert validation of input
            if (input.length() != 1) {
                System.out.println("[TRY AGAIN] Only ONE Character for Each Guess! [CHANCES REMAIN] " + chances);
                continue;
            } else if (!Character.isLetter(input.charAt(0))){
                System.out.println("[TRY AGAIN] Only English Letter is Allowed! [CHANCES REMAIN] " + chances);
                continue;
            } else if(guessBuffer.contains(Character.toString(input.charAt(0)))){
                System.out.println("[TRY AGAIN] You've already guessed it before. [CHANCES REMAIN] " + chances);
                continue;
            }

            // Assert existence of input
            if (phrase.indexOf(input.charAt(0)) == -1 && phrase.indexOf(input.toUpperCase().charAt(0)) == -1) {
                guessBuffer.add(input);
                chances -= 1;
                System.out.println("[BAD GUESS] Chances - 1! [CHANCES REMAIN] " + chances);
            } else {
                guessBuffer.add(input);

                // Decrypt characters
                System.out.println("[GOOD GUESS] [CHANCES REMAIN] " + chances);
                for (int i = 0; i < phrase.length(); i++) {
                    if (phrase.charAt(i) == input.charAt(0)) {
                        sb.setCharAt(i, input.charAt(0));
                    }else if (phrase.charAt(i) == input.toUpperCase().charAt(0)) {
                        sb.setCharAt(i, input.toUpperCase().charAt(0));
                    }
                }

                // Fully decrypted
                if (sb.indexOf("*") == -1){
                    System.out.println("[SUCCEED] Congrats! Fully Decrypted!");
                    System.out.println("[RESULT] " + sb);
                    break;
                } else {
                    System.out.println("[CURRENT RESULT] " + sb);
                }
            }
        }
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Concrete class for AI Players which implements WOFPlayer and play with its own strategy
 */
public class WheelOfFortuneCharFreqAIPlayer implements WheelOfFortunePlayer{

    String playerId;
    List<String> charList;
    int index;

    /**
     * Constructor for AI Player
     * @throws IOException Error might exists if file location or format is incorrect
     */
    public WheelOfFortuneCharFreqAIPlayer() throws IOException {
        this.playerId = "charFreqAI";
        this.index = 0;
        try{
            this.charList = Files.readAllLines(Paths.get("/Users/single/workspaces/WheelOfFortune/src/charFrequency.txt"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Player calls this function to get a new guess
     * @return single guess AI get from its strategy
     */
    @Override
    public char nextGuess() {
        // guess base on frequency of English characters
        char guess = this.charList.get(this.index).charAt(0);
        this.index ++;
        return guess;
    }

    /**
     * Get the player of current player
     * @return String the player id for current AI player
     */
    @Override
    public String playerId() {
        return this.playerId;
    }

    /**
     * reset the data member index back to 0
     */
    @Override
    public void reset() {
        this.index = 0;
    }
}

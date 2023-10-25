import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WheelOfFortuneCharFreqAIPlayer implements WheelOfFortunePlayer{

    String playerId;
    List<String> charList;
    int index;

    public WheelOfFortuneCharFreqAIPlayer() throws IOException {
        this.playerId = "charFreqAI";
        this.index = 0;
        try{
            this.charList = Files.readAllLines(Paths.get("/Users/single/workspaces/WheelOfFortune/src/charFrequency.txt"));
        }catch (IOException e){
            System.out.println(e);
        }
    }
    @Override
    public char nextGuess() {
        // guess base on frequency of English characters
        char guess = this.charList.get(this.index).charAt(0);
        this.index ++;
        return guess;
    }

    @Override
    public String playerId() {
        return this.playerId;
    }

    @Override
    public void reset() {
        this.index = 0;
    }
}

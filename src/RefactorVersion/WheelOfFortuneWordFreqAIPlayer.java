import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete method for Word Frequency AI Player with its strategy
 */
public class WheelOfFortuneWordFreqAIPlayer implements WheelOfFortunePlayer{

    String playerId;
    List<String> wordList;
    List<String> charList;
    int index;

    /**
     * Constructor set default values to our playerId and index, read strategy files for initiation
     * @throws IOException Error might exists if file location or format is incorrect
     */
    public WheelOfFortuneWordFreqAIPlayer() throws IOException{
        this.playerId = "wordFreqAI";
        this.index = 0;
        try{
            this.charList = Files.readAllLines(Paths.get("/Users/single/workspaces/WheelOfFortune/src/charFrequency.txt"));
            this.wordList = Files.readAllLines(Paths.get("/Users/single/workspaces/WheelOfFortune/src/wordFrequency.txt"));

        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Get next guess for current AI player
     * @return char a new guess character from the strategy
     */
    @Override
    public char nextGuess() {
        // get new guess base on frequency of characters and words
        List<Character> sortedCharList = this.mostFreqChars();
        char guess = sortedCharList.get(this.index);
        this.index ++;
        return guess;
    }

    /**
     * Get current player id
     * @return String current player id
     */
    @Override
    public String playerId() {
        return this.playerId;
    }

    /**
     * reset data member id to 0 for the next game
     */
    @Override
    public void reset() {
        this.index = 0;
    }

    /**
     * get most frequent chars in most frequent words
     */
    private List<Character> mostFreqChars(){
        Map<Character, Integer> charCountMap = new HashMap<>();
        List<String> topWords = this.wordList.subList(0, 5000);     // top 5000 frequent words
        for (String word: topWords){
            for (int i = 0; i < word.length(); i++) {
                // count char occurrence and save to hashmap
                charCountMap.put(word.charAt(i), charCountMap.getOrDefault(word.charAt(i), 0) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(charCountMap.entrySet());

        // Sort the list based on the counts in descending order
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Create a list of characters from the sorted entries
        List<Character> sortedCharList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : entryList) {
            sortedCharList.add(entry.getKey());
        }

        return sortedCharList;
    }
}

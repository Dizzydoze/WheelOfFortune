import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WheelOfFortuneWordFreqAIPlayer implements WheelOfFortunePlayer{

    String playerId;
    List<String> wordList;
    List<String> charList;
    int index;

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
    @Override
    public char nextGuess() {
        // get new guess base on frequency of characters and words
        List<Character> sortedCharList = this.mostFreqChars();
        char guess = sortedCharList.get(this.index);
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

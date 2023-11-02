import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Concrete class for a list storing all game records
 */
public class AllGamesRecord {

    ArrayList<GameRecord> arr;

    /**
     * Constructor, create a new array list for record storage
     */
    public AllGamesRecord(){
        this.arr = new ArrayList<>();
    }

    /**
     * add(GameRecord)-- adds a GameRecord to the AllGamesRecord
     */
    public void add(GameRecord gameRecord){
        this.arr.add(gameRecord);
    }

    /**
     * average()-- returns the average score for all games added to the record
     * @return average score for all games in the record list
     */
    public float average(){
        int totalScore = 0;
        for (GameRecord gameRecord : this.arr) {
            totalScore += gameRecord.score;
        }
        return Math.round((float)(totalScore*100 / this.arr.size())/100);
    }

    /**
     * average(playerId) -- returns the average score for all games of a particular player
     * @return float average score for all scores recorded for specific playerid
     */
    public float average(String playerId){
        int totalScore = 0;
        int count = 0;
        for (GameRecord gameRecord : this.arr) {
            if (Objects.equals(gameRecord.playerId, playerId)) {
                totalScore += gameRecord.score;
                count += 1;
            }
        }
        return Math.round((float) (totalScore * 100 / count) /100);
    }

    /**
     * highGameList(n)-- returns a sorted list of the top n scores including player and score.
     * This method should use the Collections class to sort the game instances.
     * @return ArrayList<GameRecord> top n records in a list of game records
     */
    public ArrayList<GameRecord> highGameList(int n){
        this.arr.sort(Collections.reverseOrder());
        ArrayList<GameRecord> sortedArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sortedArr.add(this.arr.get(i));
        }
        return sortedArr;
    }

    /**
     * highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player.
     * This method should use the Collections class to sort the game instances.
     * @return ArrayList<GameRecord> top n records in a list of game records for specific player id
     */
    public ArrayList<Integer> highGameList(String playerId, int n){
        this.arr.sort(Collections.reverseOrder());
        ArrayList<Integer> sortedArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Objects.equals(this.arr.get(i).playerId, playerId)){
                sortedArr.add(this.arr.get(i).score);
            }
        }
        return sortedArr;
    }
}

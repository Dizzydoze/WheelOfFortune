import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class AllGamesRecord {

    ArrayList<GameRecord> arr;

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
     average()-- returns the average score for all games added to the record
     */
    public int average(){
        int totalScore = 0;
        for (GameRecord gameRecord : this.arr) {
            totalScore += gameRecord.score;
        }
        return totalScore / this.arr.size();
    }

    /**
     average(playerId) -- returns the average score for all games of a particular player
     */
    public int average(String playerId){
        int totalScore = 0;
        int count = 0;
        for (GameRecord gameRecord : this.arr) {
            if (Objects.equals(gameRecord.playerId, playerId)) {
                totalScore += gameRecord.score;
                count += 1;
            }
        }
        return totalScore / count;
    }

    /**
     highGameList(n)-- returns a sorted list of the top n scores including player and score.
     This method should use the Collections class to sort the game instances.
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
     highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player.
     This method should use the Collections class to sort the game instances.
     */
    public ArrayList<Integer> highGameList(String playerId,int n){
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

/**
 * GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
 * It must implement Comparable and provide a default implementation of compareTo which compares scores.
 */
public class GameRecord implements Comparable<GameRecord> {

    int score;
    String playerId;

    public GameRecord(){
        this.score = 100;    // -1 for each miss
        this.playerId = "AutoGenUserPlayer";
    }

    @Override
    public int compareTo(GameRecord o) {
        return Integer.compare(this.score, o.score);
    }
}
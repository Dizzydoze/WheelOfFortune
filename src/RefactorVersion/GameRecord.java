/**
 * GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
 * It must implement Comparable and provide a default implementation of compareTo which compares scores.
 */
public class GameRecord implements Comparable<GameRecord> {

    int score;
    String playerId;

    public GameRecord(){
        this.score = 0;
    }

    @Override
    public int compareTo(GameRecord o) {
        return Integer.compare(this.score, o.score);
    }
}

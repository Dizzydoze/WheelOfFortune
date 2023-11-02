/**
 * GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
 * It must implement Comparable and provide a default implementation of compareTo which compares scores.
 */
public class GameRecord implements Comparable<GameRecord> {

    int score;
    String playerId;

    /**
     * Constructor set defualt values to score and default id for player
     */
    public GameRecord(){
        this.score = 100;    // -1 for each miss
        this.playerId = "AutoGenUserPlayer";
    }

    /**
     * Constructor set score to default value and player id to specifc argument value
     * @param playerId assign specific player id
     */
    public GameRecord(String playerId){
        this.score = 100;
        this.playerId = playerId;
    }

    /**
     * Overridden function compareTo for comparison usage
     * @param o GameRecord instance to be compared
     * @return result of integer comparison
     */
    @Override
    public int compareTo(GameRecord o) {
        return Integer.compare(this.score, o.score);
    }
}

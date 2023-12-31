/**
 * Abstract class Game will encapsulate the code for looping through a set of games and recording the results.
 */
public abstract class Game {

    /**
     * AllGamesRecord playAll()— a method that plays a set of games and records
     * @return an AllGamesRecord object for the set
     */
    public abstract AllGamesRecord playAll();

    /**
     * GameRecord play()-- plays a game
     * @return a GameRecord
     */
    public abstract GameRecord play();

    /**
     * boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played)
     * @return a boolean
     */
    public abstract boolean playNext();


}

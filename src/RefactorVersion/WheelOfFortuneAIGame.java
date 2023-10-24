import java.util.ArrayList;

/**
 * WheelOfFortuneAIGame, another concrete extension of WheelOfFortune.
 * For each AI player specified, this class will play a game for each phrase read in from the file.
 * So if there are m players and n phrases, Game’s playAll() should play m*n games.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune{
    // list of players inside this one, but only one for UserGame
    // two different main app, one for AI, one for user, won't combine
    // only one instance of this game ,add a series of player into the game, and call playall.
    // reset data member of the player

    /**
     * WheelOfFortuneAIGame should have three constructors.
     * One should set the WheelOfFortunePlayer to a default player
     */
    public WheelOfFortuneAIGame(){}

    /**
     * one should allow the client to specify a single concrete WheelOfFortunePlayer,
     */
    public WheelOfFortuneAIGame(WheelOfFortunePlayer wheelOfFortunePlayer){}

    /**
     * one should accept a list of WheelOfFortunePlayers.
     */
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> wheelOfFortunePlayers){}

    @Override
    public AllGamesRecord playAll() {
        return null;
    }

    /**
     * The class should implement the play() method from Game in order to make these things happen.
     * GameRecord play()-- plays a game and returns a GameRecord
     */
    @Override
    public GameRecord play() {
        return null;
    }

    /**
     * The class should implement the playNext() method from Game in order to make these things happen.
     * boolean playNext() -- asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * The function should return a boolean.
     */
    @Override
    public boolean playNext() {
        return false;
    }

    @Override
    public char getGuess(String previousGuesses) {
        return 0;
    }

    /**
     * As with WheelOfFortuneUserGame, WheelOfFortuneAIGame’s main should call Game’s playAll() to play the set of games,
     * and play() and playNext() should be defined to make this happen.
     * WheelOfFortuneAIGame should create at least three different players,
     * then call playAll() to run through all the phrases for each player.
     */
    public static void main(String[] args) {

    }
}

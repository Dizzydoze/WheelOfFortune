import java.util.ArrayList;

/**
 * WheelOfFortuneAIGame, another concrete extension of WheelOfFortune.
 * For each AI player specified, this class will play a game for each phrase read in from the file.
 * So if there are m players and n phrases, Game’s playAll() should play m*n games.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune{

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

    /**
     * As with WheelOfFortuneUserGame, WheelOfFortunedAIGame’s main should call Game’s playAll() to play the set of games,
     * and play() and playNext() should be defined to make this happen.
     */
    public static void main(String[] args) {

    }

}

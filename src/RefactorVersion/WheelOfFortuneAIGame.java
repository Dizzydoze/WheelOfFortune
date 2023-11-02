import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * WheelOfFortuneAIGame, another concrete extension of WheelOfFortune.
 * For each AI player specified, this class will play a game for each phrase read in from the file.
 * So if there are m players and n phrases, Game’s playAll() should play m*n games.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune{

    ArrayList<WheelOfFortunePlayer> arr;            // list of AI players
    WheelOfFortunePlayer currentPlayer;             // current player

    /**
     * WheelOfFortuneAIGame should have three constructors.
     * One should set the WheelOfFortunePlayer to a default player
     */
    public WheelOfFortuneAIGame(){
        WheelOfFortuneRandomAIPlayer wheelOfFortuneRandomAIPlayer = new WheelOfFortuneRandomAIPlayer();
        this.arr.add(wheelOfFortuneRandomAIPlayer);
    }

    /**
     * one should allow the client to specify a single concrete WheelOfFortunePlayer,
     */
    public WheelOfFortuneAIGame(WheelOfFortunePlayer wheelOfFortunePlayer){
        this.arr.add(wheelOfFortunePlayer);
    }

    /**
     * one should accept a list of WheelOfFortunePlayers.
     */
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> wheelOfFortunePlayers){
        this.arr = wheelOfFortunePlayers;
    }

    /**
     * Play all the phrases in the list for current player(who called this func)
     * @return AllGamesRecord which contains a list for storing all record instance related to each player
     */
    @Override
    public AllGamesRecord playAll() {
        // double loop
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        for (WheelOfFortunePlayer player: this.arr){
            // update new current player
            this.currentPlayer = player;
            // for every player, we generate a whole new game set
            this.readPhrases();
            int phraseSize = this.phraseList.size();
            for (int i = 0; i < phraseSize; i++) {
                // get a phrase from the rest of the phraseList
                this.randomPhrase();
                this.generateHiddenPhrase();
                GameRecord gameRecord = this.play();
                allGamesRecord.add(gameRecord);
            }
        }
        return allGamesRecord;
    }

    /**
     * The class should implement the play() method from Game in order to make these things happen.
     * GameRecord play()-- plays a game
     * @return returns a GameRecord
     */
    @Override
    public GameRecord play() {
        Random random = new Random();                           // random for sleep
        GameRecord gameRecord = new GameRecord(this.currentPlayer.playerId());
        String previousGuesses = "";                            // store previous guess characters
        while (true){
            if (!(gameRecord.score > 60)){                       // lose the game if score turns to 0
                System.out.println("AI Lose![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);             // remove the finished game from phraseList
                this.currentPlayer.reset();
                return gameRecord;
            }
            if (this.hiddenPhrase.indexOf("*") == -1){
                System.out.println("AI Win![PlayerId]" + gameRecord.playerId + "[Final Score] " + gameRecord.score);
                this.phraseList.remove(this.index);             // remove the finished game from phraseList
                this.currentPlayer.reset();
                return gameRecord;
            }

            char guess = this.getGuess(previousGuesses);        // get new guess character
            if (!Character.isWhitespace(guess)){
                previousGuesses += guess;                       // add it into buffer
//                System.out.println("[GUESS BUFFER] " + previousGuesses);
                if (!super.processGuess(guess)){
                    gameRecord.score -= 1;
                    System.out.println("[PlayerId]" + gameRecord.playerId + "[Current Score] " + gameRecord.score + "[BAD GUESS]Score -1!");
                }
                else{
                    System.out.println("[PlayerId]" + gameRecord.playerId + "[GOOD GUESS][Current Hidden Phrase] " + this.hiddenPhrase);
                }
            }
            try{
                Thread.sleep(random.nextInt(100, 300));     // sleep 0.1s ~ 0.3s
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * The class should implement the playNext() method from Game in order to make these things happen.
     * boolean playNext() -- asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * @return return a boolean.
     */
    @Override
    public boolean playNext() {
        return true;           // AI will play all games, so it will always be true
    }

    /**
     * get a new guess from the player
     * @param previousGuesses send in a current buffer storing the guessed characters and keep it updated
     * @return char the guess character we get
     */
    @Override
    public char getGuess(String previousGuesses) {
        char guess = this.currentPlayer.nextGuess();
        // try again if there's a repeated guess
        if (previousGuesses.contains(Character.toString(guess))){
            return ' ';
        }
        return guess;
    }

    /**
     * As with WheelOfFortuneUserGame, WheelOfFortuneAIGame’s main should call Game’s playAll() to play the set of games,
     * and play() and playNext() should be defined to make this happen.
     * WheelOfFortuneAIGame should create at least three different players,
     * then call playAll() to run through all the phrases for each player.
     */
    public static void main(String[] args) throws IOException {

        // 3 AI players are going to play through all the games
        ArrayList<WheelOfFortunePlayer> arr = new ArrayList<>();
        arr.add(new WheelOfFortuneRandomAIPlayer());
        arr.add(new WheelOfFortuneCharFreqAIPlayer());
        arr.add(new WheelOfFortuneWordFreqAIPlayer());

        // add players to the AI game
        WheelOfFortuneAIGame wheelOfFortuneAIGame = new WheelOfFortuneAIGame(arr);

        // start to play
        AllGamesRecord allGamesRecord = wheelOfFortuneAIGame.playAll();

        System.out.println("--------------------------------------------------------");

        // rank top 3
        ArrayList<GameRecord> rankList = allGamesRecord.highGameList(3);
        for (int i = 0; i < rankList.size(); i++) {
            String strFormat = String.format("[TOP]%d [PlayerId]%s [Score]%d",
                    i + 1, rankList.get(i).playerId, rankList.get(i).score);
            System.out.println(strFormat);
        }

        // average score of all games
        float avg = allGamesRecord.average();
        System.out.println("Average score of all 9 games: " + avg);

        // average score of each player
        float avgRandom = allGamesRecord.average("randomAI");
        float avgCharFreq = allGamesRecord.average("charFreqAI");
        float avgWordFreq = allGamesRecord.average("wordFreqAI");
        String avgEach = String.format("Average score of each player: \n[randomAI]%f\n[charFreqAI]%f\n[wordFreqAI]%f", avgRandom, avgCharFreq, avgWordFreq);
        System.out.println(avgEach);
    }
}

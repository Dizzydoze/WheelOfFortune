import java.util.ArrayList;

public class WheelOfFortuneGame extends GuessingGame{

    public WheelOfFortuneGame() {
        super();
    }

    /**
     * Check whether a letter matches, and partially modifies the hiddenPhrase if there is a match.
     * @param input guess input from command line
     * @return boolean
     */
    @Override
    public boolean processGuess(String input){
        if (this.phrase.indexOf(input.charAt(0)) == -1 && this.phrase.indexOf(input.toUpperCase().charAt(0)) == -1) {
            return false;
        }else{
            // modify the hidden phrase
            for (int i = 0; i < this.phrase.length(); i++) {
                if (this.phrase.charAt(i) == input.charAt(0)){
                    this.hiddenPhrase.setCharAt(i, input.charAt(0));
                } else if (this.phrase.charAt(i) == input.toUpperCase().charAt(0)) {
                    this.hiddenPhrase.setCharAt(i, input.toUpperCase().charAt(0));
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        WheelOfFortuneGame game = new WheelOfFortuneGame();
        AllGamesRecord allGamesRecord = game.playAll();

        // display top N of the games
        ArrayList<GameRecord> topList = allGamesRecord.highGameList(2);
        for (int i = 0; i < topList.size(); i++) {
            System.out.printf("TOP %d[PlayerId]%s[Score]%d\n", i + 1, topList.get(i).playerId, topList.get(i).score);
        }

        // display average score of the games
        float avgScore = allGamesRecord.average();
        System.out.println("[Average Score]" + avgScore);
    }
}



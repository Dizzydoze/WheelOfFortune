import java.util.ArrayList;

public class MasterMindGame extends GuessingGame{

    public MasterMindGame() {
        super();
    }

    /**
     * Check number of exact matches of input.
     * @param input guessing string
     * @return number of exact matches
     */
    private int computeExacts(String input) {
        // reset both guessSB and secretSB each round
        this.phraseSB = new StringBuilder(this.phrase);
        int exact = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == this.phrase.charAt(i)){
                exact += 1;
                // mark exact match as * in both secretSB and guessSB to skip, always use SB, as we keep changing the same SB
                this.phraseSB = new StringBuilder(this.phraseSB.substring(0, i) + '*' + this.phraseSB.substring(i + 1));
                this.guessSB = new StringBuilder(this.guessSB.substring(0, i) + '*' + this.guessSB.substring(i+1));
            }
        }
        return exact;
    }

    /**
     * Check number of partial matches of input.
     * @param input guessing string
     * @return number of partial matches
     */
    private int computePartials(String input) {

        int partial = 0;
        for (int i = 0; i < input.length(); i++) {
            // always use guessSB, secretSB for checking, as we keep changing both SB to *
            if(this.guessSB.charAt(i)!= '*' && this.phraseSB.indexOf(String.valueOf(this.guessSB.charAt(i)))!=-1){
                // mark partial match in both stringBuilder as * to skip duplication
                int partialSecretIndex = this.phraseSB.indexOf(String.valueOf(this.guessSB.charAt(i)));
                this.phraseSB = new StringBuilder(this.phraseSB.substring(0, partialSecretIndex) + '*'
                        + this.phraseSB.substring(partialSecretIndex+1));
                this.guessSB = new StringBuilder(this.guessSB.substring(0, i) + '*' + this.guessSB.substring(i+1));
                partial += 1;
            }
        }
        return partial;
    }

    /**
     * Process guessing input, compute exact and partial matches.
     * @param input guessing input string
     * @return boolean
     */
    @Override
    public boolean processGuess(String input){
        int exact = this.computeExacts(input);
        int partial = this.computePartials(input);
        // only return ture if all char matches
        if (exact == this.phrase.length()){
            return true;
        }
        else{
            String roundResult = "[GUESS]" + input + "[PARTIAL]" + partial + "[EXACT]" + exact + "\n";
            this.roundResult.append(roundResult);
            System.out.println(this.roundResult);
            return false;
        }
    }

    public static void main(String[] args) {
        MasterMindGame game = new MasterMindGame();
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

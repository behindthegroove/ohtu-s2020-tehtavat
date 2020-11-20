package ohtu;

public class TennisGame {
    
    private int score1 = 0;
    private int score2 = 0;
    private String player1;
    private String player2;

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            score1 += 1;
        else
            score2 += 1;
    }

    public String getScore() {
        String score = "";

        if (score1==score2) {
            score = getEqualScore();
        } else if (score1>=4 || score2>=4) {
            score = getWinOrAdvantage();
        } else {
            score = getPlayerScores();
        }

        return score;
    }

    private String getEqualScore() {
        switch (score1) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private String getWinOrAdvantage() {
        int scoreDifference = score1 - score2;

        if (scoreDifference == 1)
            return "Advantage player1";
        else if (scoreDifference == -1)
            return "Advantage player2";
        else if (scoreDifference >= 2)
            return "Win for player1";
        else
            return "Win for player2";
    }

    private String getPlayerScores() {
        String score = "";

        int playerScore = 0;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                playerScore = score1;
            } else {
                score += "-";
                playerScore = score2;
            }

            switch (playerScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }

        return score;
    }

}
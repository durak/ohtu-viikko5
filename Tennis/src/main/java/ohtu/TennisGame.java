package ohtu;

public class TennisGame {

    private int player1Points = 0;
    private int player2Points = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Points += 1;
        } else {
            player2Points += 1;
        }
    }

    public String getScore() {

        if (gameIsDeuce()) {
            return "Deuce";
        }

        if (gameIsEven()) {
            return pointsToScore(player1Points) + "-All";
        }

        if (player1HasAdvantage()) {
            return "Advantage player1";
        }

        if (player2HasAdvantage()) {
            return "Advantage player2";
        }

        if (player1HasWon()) {
            return "Win for player1";
        }

        if (player2HasWon()) {
            return "Win for player2";
        }

        // game is not even and each playr has less than 4 points
        return pointsToScore(player1Points) + "-" + pointsToScore(player2Points);
    }

    private boolean gameIsDeuce() {
        return player1Points >= 4 && gameIsEven();
    }

    private boolean gameIsEven() {
        return player1Points == player2Points;
    }

    private boolean player1HasAdvantage() {
        return player1Points >= 4 && pointDifference() == 1;
    }

    private boolean player2HasAdvantage() {
        return player2Points >= 4 && pointDifference() == -1;
    }

    private boolean player1HasWon() {
        return player1Points >= 4 && pointDifference() >= 2;
    }

    private boolean player2HasWon() {
        return player2Points >= 4 && pointDifference() <= -2;
    }

    private int pointDifference() {
        return player1Points - player2Points;
    }

    private String pointsToScore(int points) {

        switch (points) {
            case 0:
                return "Love";

            case 1:
                return "Fifteen";

            case 2:
                return "Thirty";

            case 3:
                return "Forty";

            default:
                return "";

        }
    }

}

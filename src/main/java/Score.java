import java.util.Date;

public class Score
{
    Date date;
    Player player1;
    Player player2;
    int player1Wins;
    int player2Wins;

    @Override
    public String toString()
    {
        return "date: " + date
                + " player1: " + player1
                + " player2: " + player2
                + " player1Wins: " + player1Wins
                + " player2Wins: " + player2Wins;
    }
}
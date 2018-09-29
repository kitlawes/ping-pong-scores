import java.util.Date;

public class Score
{
    private Date date;
    private Player player1;
    private Player player2;
    private int player1Wins;
    private int player2Wins;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Player getPlayer1()
    {
        return player1;
    }

    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }

    public Player getPlayer2()
    {
        return player2;
    }

    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    public int getPlayer1Wins()
    {
        return player1Wins;
    }

    public void setPlayer1Wins(int player1Wins)
    {
        this.player1Wins = player1Wins;
    }

    public int getPlayer2Wins()
    {
        return player2Wins;
    }

    public void setPlayer2Wins(int player2Wins)
    {
        this.player2Wins = player2Wins;
    }

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
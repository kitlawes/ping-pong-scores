import java.util.Date;

public class Score
{
    private Date date;
    private Player player;
    private Player opponent;
    private int playerWins;
    private int opponentWins;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Player getOpponent()
    {
        return opponent;
    }

    public void setOpponent(Player opponent)
    {
        this.opponent = opponent;
    }

    public int getPlayerWins()
    {
        return playerWins;
    }

    public void setPlayerWins(int playerWins)
    {
        this.playerWins = playerWins;
    }

    public int getOpponentWins()
    {
        return opponentWins;
    }

    public void setOpponentWins(int opponentWins)
    {
        this.opponentWins = opponentWins;
    }

    @Override
    public String toString()
    {
        return "date: " + date
                + " player: " + player
                + " opponent: " + opponent
                + " playerWins: " + playerWins
                + " opponentWins: " + opponentWins;
    }
}
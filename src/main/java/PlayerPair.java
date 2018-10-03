public class PlayerPair
{
    private Player player;
    private Player opponent;

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

    @Override
    public String toString()
    {
        return player + "-" + opponent;
    }

    public static PlayerPair getPlayerPair(String key)
    {
        PlayerPair playerPair = new PlayerPair();
        String[] players = key.split("-");
        playerPair.setPlayer(Player.valueOf(players[0]));
        playerPair.setOpponent(Player.valueOf(players[1]));
        return playerPair;
    }
}
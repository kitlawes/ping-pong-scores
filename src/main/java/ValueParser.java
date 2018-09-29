import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValueParser
{
    private List<Score> scores;

    public ValueParser(List<List<Object>> values)
    {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

        scores = new ArrayList<>();
        for (int i = 0; i < values.size(); i++)
        {
            List<Object> row = values.get(i);
            if (row.size() >= 1 && String.valueOf(row.get(0)).matches("\\d{2}/\\d{2}/\\d{4}"))
            {
                try
                {
                    Score score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.ANTONIO);
                    score.setOpponent(Player.KIT);
                    String playerWins = String.valueOf(row.get(2));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    String opponentWins = String.valueOf(row.get(3));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);
                    
                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.KIT);
                    score.setOpponent(Player.ANTONIO);
                    playerWins = String.valueOf(row.get(3));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(2));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.KIT);
                    score.setOpponent(Player.HUNOR);
                    playerWins = String.valueOf(row.get(5));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(6));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.HUNOR);
                    score.setOpponent(Player.KIT);
                    playerWins = String.valueOf(row.get(6));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(5));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.HUNOR);
                    score.setOpponent(Player.ANTONIO);
                    playerWins = String.valueOf(row.get(8));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(9));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.ANTONIO);
                    score.setOpponent(Player.HUNOR);
                    playerWins = String.valueOf(row.get(9));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(8));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.ANTONIO);
                    score.setOpponent(Player.JIPESH);
                    playerWins = String.valueOf(row.get(11));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(12));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.JIPESH);
                    score.setOpponent(Player.ANTONIO);
                    playerWins = String.valueOf(row.get(12));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(11));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.KIT);
                    score.setOpponent(Player.JIPESH);
                    playerWins = String.valueOf(row.get(14));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(15));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.JIPESH);
                    score.setOpponent(Player.KIT);
                    playerWins = String.valueOf(row.get(15));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(14));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.HUNOR);
                    score.setOpponent(Player.JIPESH);
                    playerWins = String.valueOf(row.get(17));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(18));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);

                    score = new Score();
                    score.setDate(dateParser.parse(String.valueOf(row.get(0))));
                    score.setPlayer(Player.JIPESH);
                    score.setOpponent(Player.HUNOR);
                    playerWins = String.valueOf(row.get(18));
                    score.setPlayerWins(Integer.valueOf(playerWins.isEmpty() ? "0" : playerWins));
                    opponentWins = String.valueOf(row.get(17));
                    score.setOpponentWins(Integer.valueOf(opponentWins.isEmpty() ? "0" : opponentWins));
                    scores.add(score);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Score> getScores()
    {
        return scores;
    }

    public List<Score> getScoresForDate(List<Score> scores, Date date)
    {
        List<Score> scoresForDate = new ArrayList<Score>();
        for (Score score : scores)
        {
            if (score.getDate().equals(date))
            {
                scoresForDate.add(score);
            }
        }
        return scoresForDate;
    }

    public List<Score> getScoresForDateRange(List<Score> scores, Date start, Date end)
    {
        List<Score> scoresForDate = new ArrayList<Score>();
        for (Score score : scores)
        {
            if (score.getDate().equals(start)
                    || score.getDate().after(start) && score.getDate().before(end)
                    || score.getDate().equals(end))
            {
                scoresForDate.add(score);
            }
        }
        return scoresForDate;
    }

    public List<Score> getScoresForPlayer(List<Score> scores, Player player)
    {
        List<Score> scoresForPlayer = new ArrayList<Score>();
        for (Score score : scores)
        {
            if (score.getPlayer() == player)
            {
                scoresForPlayer.add(score);
            }
        }
        return scoresForPlayer;
    }

    public List<Score> getScoresForOpponent(List<Score> scores, Player opponent)
    {
        List<Score> scoresForOpponent = new ArrayList<Score>();
        for (Score score : scores)
        {
            if (score.getOpponent() == opponent)
            {
                scoresForOpponent.add(score);
            }
        }
        return scoresForOpponent;
    }
}
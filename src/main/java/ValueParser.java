import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ValueParser
{
    public static List<Score> parseValues(List<List<Object>> values)
    {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < values.size(); i++)
        {
            List<Object> row = values.get(i);
            if (row.size() >= 1 && String.valueOf(row.get(0)).matches("\\d{2}/\\d{2}/\\d{4}"))
            {
                try
                {
                    Score score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.ANTONIO;
                    score.player2 = Player.KIT;
                    String player1Wins = String.valueOf(row.get(2));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    String player2Wins = String.valueOf(row.get(3));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);

                    score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.KIT;
                    score.player2 = Player.HUNOR;
                    player1Wins = String.valueOf(row.get(5));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    player2Wins = String.valueOf(row.get(6));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);

                    score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.HUNOR;
                    score.player2 = Player.ANTONIO;
                    player1Wins = String.valueOf(row.get(8));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    player2Wins = String.valueOf(row.get(9));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);

                    score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.ANTONIO;
                    score.player2 = Player.JIPESH;
                    player1Wins = String.valueOf(row.get(11));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    player2Wins = String.valueOf(row.get(12));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);

                    score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.KIT;
                    score.player2 = Player.JIPESH;
                    player1Wins = String.valueOf(row.get(14));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    player2Wins = String.valueOf(row.get(15));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);

                    score = new Score();
                    score.date = dateParser.parse(String.valueOf(row.get(0)));
                    score.player1 = Player.HUNOR;
                    score.player2 = Player.JIPESH;
                    player1Wins = String.valueOf(row.get(17));
                    score.player1Wins = Integer.valueOf(player1Wins.isEmpty() ? "0" : player1Wins);
                    player2Wins = String.valueOf(row.get(18));
                    score.player2Wins = Integer.valueOf(player2Wins.isEmpty() ? "0" : player2Wins);
                    scores.add(score);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return scores;
    }
}
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ScoresAnalyzer
{
    private ValueParser valueParser;

    public ScoresAnalyzer(ValueParser valueParser)
    {
        this.valueParser = valueParser;
    }

    public void numberOfGamesWonForDateForPlayerForOpponent(Date date, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDate(
                                valueParser.getScores(),
                                date),
                        player),
                opponent);
        
        System.out.println(scores);
    }
}
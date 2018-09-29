import java.util.Date;
import java.util.List;

public class ScoresAnalyzer
{
    private ValueParser valueParser;

    public ScoresAnalyzer(ValueParser valueParser)
    {
        this.valueParser = valueParser;
    }

    public int numberOfGames(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDateRange(
                        valueParser.getScores(), start, end),
                player);
        if (opponent != Player.ANY)
        {
            scores = valueParser.getScoresForOpponent(
                    scores, opponent);
        }
        int games = 0;
        for (Score score : scores)
        {
            if (outcome == GameOutcome.WIN || outcome == GameOutcome.ANY)
            {
                games += score.getPlayerWins();
            }
            if (outcome == GameOutcome.LOSE || outcome == GameOutcome.ANY)
            {
                games += score.getOpponentWins();
            }
        }
        return games;
    }
}
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

    public Player player(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        int gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
        int gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
        switch (outcome)
        {
            case WIN:
                return gamesWon > gamesLost ? player : gamesWon < gamesLost ? opponent : Player.NONE;
            case LOSE:
                return gamesWon < gamesLost ? player : gamesWon > gamesLost ? opponent : Player.NONE;
        }
        return Player.ANY;
    }

    public int percentage(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        int gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
        int gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
        int gamesPlayed = gamesWon + gamesLost;
        if (gamesPlayed == 0) {
            return 0;
        }
        switch (outcome)
        {
            case WIN:
                return (int) Math.round((double) gamesWon / gamesPlayed * 100);
            case LOSE:
                return (int) Math.round((double) gamesLost / gamesPlayed * 100);
        }
        return 100;
    }
}
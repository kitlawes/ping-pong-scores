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
        List<Score> scores = valueParser.getScoresForDateRange(
                valueParser.getScores(), start, end);
        if (player != Player.ANY)
        {
            scores = valueParser.getScoresForPlayer(
                    scores, player);
        }
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
        if (player == Player.ANY && opponent == Player.ANY)
        {
            return games / 2;
        }
        return games;
    }

    public Player playerWithOutcome(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
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

    public int percentageOfGamesWithOutcome(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        int gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
        int gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
        int gamesPlayed = gamesWon + gamesLost;
        if (gamesPlayed == 0)
        {
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

    public double averageNumberOfGames(GameOutcome outcome, Date start, Date end, int days, Player player, Player opponent)
    {
        int gamesWon = 0;
        int gamesLost = 0;
        int gamesPlayed;
        if (player == Player.ANY && opponent == Player.ANY)
        {
            gamesPlayed = numberOfGames(GameOutcome.ANY, start, end, player, opponent);
        }
        else
        {
            gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
            gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
            gamesPlayed = gamesWon + gamesLost;
        }
        int numberOfDatesInRange = valueParser.getNumberOfDatesInRange(start, end);
        int divisor = numberOfDatesInRange / days;
        switch (outcome)
        {
            case WIN:
                return (double) Math.round((double) gamesWon / divisor * 100) / 100;
            case LOSE:
                return (double) Math.round((double) gamesLost / divisor * 100) / 100;
            case ANY:
                return (double) Math.round((double) gamesPlayed / divisor * 100) / 100;
        }
        return 0;
    }
}
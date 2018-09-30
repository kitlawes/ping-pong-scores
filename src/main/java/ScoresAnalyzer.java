import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ScoresAnalyzer
{
    private ValueParser parser;

    public ScoresAnalyzer(ValueParser parser)
    {
        this.parser = parser;
    }

    public int numberOfGames(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = parser.getScoresForDateRange(
                parser.getScores(), start, end);
        if (player != Player.ANY)
        {
            scores = parser.getScoresForPlayer(
                    scores, player);
        }
        if (opponent != Player.ANY)
        {
            scores = parser.getScoresForOpponent(
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

    public double averageNumberOfGames(GameOutcome outcome, Date start, Date end, int intervalDays, Player player,
                                       Player opponent)
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
        int numberOfDatesInRange = parser.getNumberOfDatesInRange(start, end);
        int divisor = numberOfDatesInRange / intervalDays;
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

    public int mostGames(GameOutcome outcome, Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int mostGames = 0;
        int games = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            games += numberOfGames(outcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                mostGames = Math.max(mostGames, games);
                games = 0;
            }
        }
        return mostGames;
    }

    public int numberOfIntervals(Intervals intervals, IntervalGames intervalGames, GameOutcome outcome,
                                 Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int mostConsecutiveIntervals = 0;
        int gamesPlayed = 0;
        int gamesWon = 0;
        int gamesLost = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            gamesPlayed += numberOfGames(GameOutcome.ANY, date, date, player, opponent);
            gamesWon += numberOfGames(GameOutcome.WIN, date, date, player, opponent);
            gamesLost += numberOfGames(GameOutcome.LOSE, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (intervalGames == IntervalGames.AT_LEAST_ONE
                        && (outcome == GameOutcome.ANY && gamesPlayed >= 1
                        || outcome == GameOutcome.WIN && gamesWon >= 1
                        || outcome == GameOutcome.LOSE && gamesLost >= 1)
                        || intervalGames == IntervalGames.WITHOUT_ANY
                        && (outcome == GameOutcome.ANY && gamesPlayed == 0
                        || outcome == GameOutcome.WIN && gamesWon == 0
                        || outcome == GameOutcome.LOSE && gamesLost == 0)
                        || intervalGames == IntervalGames.MORE_FREQUENT
                        && (outcome == GameOutcome.WIN && gamesWon > gamesLost
                        || outcome == GameOutcome.LOSE && gamesLost > gamesWon)
                        || intervalGames == IntervalGames.EQUALLY_FREQUENT
                        && gamesWon == gamesLost
                        || intervalGames == IntervalGames.LESS_FREQUENT
                        && (outcome == GameOutcome.WIN && gamesWon < gamesLost
                        || outcome == GameOutcome.LOSE && gamesLost < gamesWon))
                {
                    numberOfIntervals++;
                    mostConsecutiveIntervals = Math.max(mostConsecutiveIntervals, numberOfIntervals);
                }
                else if (intervals == Intervals.MOST_CONSECUTIVE)
                {
                    numberOfIntervals = 0;
                }
                gamesPlayed = 0;
                gamesWon = 0;
                gamesLost = 0;
            }
        }
        switch (intervals)
        {
            case ANY:
                return numberOfIntervals;
            case MOST_CONSECUTIVE:
                return mostConsecutiveIntervals;
        }
        return 0;
    }

    public Date dateOfNumberOfGames(int numberOfGames, GameOutcome outcome, Date start, Date end, Player player,
                                    Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int games = 0;
        for (Date date : dates)
        {
            games += numberOfGames(outcome, date, date, player, opponent);
            if (games >= numberOfGames)
            {
                return date;
            }
        }
        return null;
    }
}
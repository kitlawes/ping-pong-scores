import java.util.Collections;
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
        int numberOfDatesInRange = valueParser.getNumberOfDatesInRange(start, end);
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
        List<Date> dates = valueParser.getDatesInRange(start, end);
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

    public int numberOfIntervalsWithAtLeastOneGame(GameOutcome outcome, Date start, Date end, int intervalDays,
                                                   Player player, Player opponent)
    {
        List<Date> dates = valueParser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int games = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            games += numberOfGames(outcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (games >= 1)
                {
                    numberOfIntervals++;
                }
                games = 0;
            }
        }
        return numberOfIntervals;
    }

    public int numberOfIntervalsWithoutAnyGames(GameOutcome outcome, Date start, Date end, int intervalDays,
                                                Player player, Player opponent)
    {
        List<Date> dates = valueParser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int games = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            games += numberOfGames(outcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (games == 0)
                {
                    numberOfIntervals++;
                }
                games = 0;
            }
        }
        return numberOfIntervals;
    }

    public int numberOfIntervalsWithGameOutcomeMoreFrequentThanGameOutcome(GameOutcome moreFrequentOutcome,
        GameOutcome lessFrequentOutcome, Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = valueParser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int moreFrequentOutcomeGames = 0;
        int lessFrequentOutcomeGames = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            moreFrequentOutcomeGames += numberOfGames(moreFrequentOutcome, date, date, player, opponent);
            lessFrequentOutcomeGames += numberOfGames(lessFrequentOutcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (moreFrequentOutcomeGames > lessFrequentOutcomeGames)
                {
                    numberOfIntervals++;
                }
                moreFrequentOutcomeGames = 0;
                lessFrequentOutcomeGames = 0;
            }
        }
        return numberOfIntervals;
    }

    public int numberOfIntervalsWithGameOutcomeEqualToGameOutcome(GameOutcome outcome,
        GameOutcome equalOutcome, Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = valueParser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int outcomeGames = 0;
        int equalOutcomeGames = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            outcomeGames += numberOfGames(outcome, date, date, player, opponent);
            equalOutcomeGames += numberOfGames(equalOutcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (outcomeGames == equalOutcomeGames)
                {
                    numberOfIntervals++;
                }
                outcomeGames = 0;
                equalOutcomeGames = 0;
            }
        }
        return numberOfIntervals;
    }
}
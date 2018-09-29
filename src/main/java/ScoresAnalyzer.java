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

    public int numberOfGamesPlayedForDateForPlayer(Date date, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDate(
                        valueParser.getScores(), date),
                player);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesPlayedForDateRangeForPlayer(Date start, Date end, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDateRange(
                        valueParser.getScores(), start, end),
                player);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesPlayedForPlayer(Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScores(), player);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesWonForDateForPlayer(Date date, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDate(
                        valueParser.getScores(), date),
                player);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesWonForDateRangeForPlayer(Date start, Date end, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDateRange(
                        valueParser.getScores(), start, end),
                player);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesWonForPlayer(Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScores(), player);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesLostForDateForPlayer(Date date, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDate(
                        valueParser.getScores(), date),
                player);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }

    public int numberOfGamesLostForDateRangeForPlayer(Date start, Date end, Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScoresForDateRange(
                        valueParser.getScores(), start, end),
                player);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }

    public int numberOfGamesLostForPlayer(Player player)
    {
        List<Score> scores = valueParser.getScoresForPlayer(
                valueParser.getScores(), player);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }

    public int numberOfGamesPlayedForDateForPlayerForOpponent(Date date, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDate(
                                valueParser.getScores(), date),
                        player),
                opponent);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesPlayedForDateRangeForPlayerForOpponent(Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDateRange(
                                valueParser.getScores(), start, end),
                        player),
                opponent);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesPlayedForPlayerForOpponent(Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScores(), player),
                opponent);
        int gamesPlayed = 0;
        for (Score score : scores)
        {
            gamesPlayed += score.getPlayerWins();
            gamesPlayed += score.getOpponentWins();
        }
        return gamesPlayed;
    }

    public int numberOfGamesWonForDateForPlayerForOpponent(Date date, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDate(
                                valueParser.getScores(), date),
                        player),
                opponent);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesWonForDateRangeForPlayerForOpponent(Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDateRange(
                                valueParser.getScores(), start, end),
                        player),
                opponent);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesWonForPlayerForOpponent(Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScores(), player),
                opponent);
        int gamesWon = 0;
        for (Score score : scores)
        {
            gamesWon += score.getPlayerWins();
        }
        return gamesWon;
    }

    public int numberOfGamesLostForDateForPlayerForOpponent(Date date, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDate(
                                valueParser.getScores(), date),
                        player),
                opponent);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }

    public int numberOfGamesLostForDateRangeForPlayerForOpponent(Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScoresForDateRange(
                                valueParser.getScores(), start, end),
                        player),
                opponent);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }

    public int numberOfGamesLostForPlayerForOpponent(Player player, Player opponent)
    {
        List<Score> scores = valueParser.getScoresForOpponent(
                valueParser.getScoresForPlayer(
                        valueParser.getScores(), player),
                opponent);
        int gamesLost = 0;
        for (Score score : scores)
        {
            gamesLost += score.getOpponentWins();
        }
        return gamesLost;
    }
}
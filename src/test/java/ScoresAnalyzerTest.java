import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoresAnalyzerTest
{
    private static ScoresAnalyzer analyzer;
    private static Date earliestDate;
    private static Date latestDate;

    @BeforeAll
    static void initAll()
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = null;
        try
        {
            values = reader.readSpreadsheet();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (GeneralSecurityException e)
        {
            e.printStackTrace();
        }

        ValueParser parser = new ValueParser(values);

        analyzer = new ScoresAnalyzer(parser);

        earliestDate = parser.getEarliestDate();
        latestDate = parser.getLatestDate();
    }

    @Test
    public void numberOfGamesPlayed()
    {
        // number of games played for date
        assertEquals(19,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANY,
                        Player.ANY));
        // number of games played for date range
        assertEquals(61,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANY,
                        Player.ANY));
        // number of games played
        assertEquals(757,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void numberOfGamesPlayedForPlayer()
    {
        // number of games played for date for player
        assertEquals(11,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games played for date range for player
        assertEquals(36,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games played for player
        assertEquals(368,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfGamesWonForPlayer()
    {
        // number of games won for date for player
        assertEquals(3,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games won for date range for player
        assertEquals(14,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games won for player
        assertEquals(178,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfGamesLostForPlayer()
    {
        // number of games lost for date for player
        assertEquals(8,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games lost for date range for player
        assertEquals(22,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // number of games lost for player
        assertEquals(190,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfGamesPlayedForPlayerForOpponent()
    {
        // number of games played for date for player for opponent
        assertEquals(7,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games played for date range for player for opponent
        assertEquals(11,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games played for player for opponent
        assertEquals(231,
                analyzer.numberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfGamesWonForPlayerForOpponent()
    {
        // number of games won for date for player for opponent
        assertEquals(2,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games won for date range for player for opponent
        assertEquals(5,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games won for player for opponent
        assertEquals(114,
                analyzer.numberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfGamesLostForPlayerForOpponent()
    {
        // number of games lost for date for player for opponent
        assertEquals(5,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games lost for date range for player for opponent
        assertEquals(6,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // number of games lost for player for opponent
        assertEquals(117,
                analyzer.numberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void winnerForPlayerForOpponent()
    {
        // winner for date for player for opponent
        assertEquals(Player.KIT,
                analyzer.playerWithOutcome(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // winner for date range for player for opponent
        assertEquals(Player.KIT,
                analyzer.playerWithOutcome(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // winner for player for opponent
        assertEquals(Player.KIT,
                analyzer.playerWithOutcome(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void loserForPlayerForOpponent()
    {
        // loser for date for player for opponent
        assertEquals(Player.ANTONIO,
                analyzer.playerWithOutcome(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // loser for date range for player for opponent
        assertEquals(Player.ANTONIO,
                analyzer.playerWithOutcome(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // loser for player for opponent
        assertEquals(Player.ANTONIO,
                analyzer.playerWithOutcome(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void percentageOfGamesWonForPlayer()
    {
        // percentage of games won for date for player
        assertEquals(new Double(27.27272727272727),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // percentage of games won for date range for player
        assertEquals(new Double(38.88888888888889),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // percentage of games won for player
        assertEquals(new Double(48.369565217391305),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void percentageOfGamesLostForPlayer()
    {
        // percentage of games lost for date for player
        assertEquals(new Double(72.72727272727273),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // percentage of games lost for date range for player
        assertEquals(new Double(61.111111111111114),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.ANY));
        // percentage of games lost for player
        assertEquals(new Double(51.63043478260869),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void percentageOfGamesWonForPlayerForOpponent()
    {
        // percentage of games won for date for player for opponent
        assertEquals(new Double(28.57142857142857),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // percentage of games won for date range for player for opponent
        assertEquals(new Double(45.45454545454545),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // percentage of games won for player for opponent
        assertEquals(new Double(49.35064935064935),
                analyzer.percentageOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void percentageOfGamesLostForPlayerForOpponent()
    {
        // percentage of games lost for date for player for opponent
        assertEquals(new Double(71.42857142857143),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // percentage of games lost for date range for player for opponent
        assertEquals(new Double(54.54545454545454),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO,
                        Player.KIT));
        // percentage of games lost for player for opponent
        assertEquals(new Double(50.649350649350644),
                analyzer.percentageOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void averagePercentageOfGamesWonForPlayer()
    {
        // average percentage of games won for date for player
        assertEquals(new Double(26.785714285714285),
                analyzer.averagePercentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO));
        // average percentage of games won for date range for player
        assertEquals(new Double(40.72727272727273),
                analyzer.averagePercentageOfGames(
                        GameOutcome.WIN,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO));
        // average percentage of games won for player
        assertEquals(new Double(48.968952194758636),
                analyzer.averagePercentageOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO));
    }

    @Test
    public void averagePercentageOfGamesLostForPlayer()
    {
        // average percentage of games lost for date for player
        assertEquals(new Double(73.21428571428572),
                analyzer.averagePercentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        Player.ANTONIO));
        // average percentage of games lost for date range for player
        assertEquals(new Double(59.272727272727266),
                analyzer.averagePercentageOfGames(
                        GameOutcome.LOSE,
                        new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                        new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                        Player.ANTONIO));
        // average percentage of games lost for player
        assertEquals(new Double(51.03104780524135),
                analyzer.averagePercentageOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO));
    }

    @Test
    public void averageNumberOfGamesPlayed()
    {
        // average number of games played in a day
        assertEquals(12.62,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // average number of games played in a week
        assertEquals(63.08,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void averageNumberOfGamesInADayForPlayer()
    {
        // average number of games played in a day for player
        assertEquals(6.13,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // average number of games won in a day for player
        assertEquals(2.97,
                analyzer.averageNumberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // average number of games lost in a day for player
        assertEquals(3.17,
                analyzer.averageNumberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void averageNumberOfGamesInAWeekForPlayer()
    {
        // average number of games played in a day for player
        assertEquals(30.67,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // average number of games won in a day for player
        assertEquals(14.83,
                analyzer.averageNumberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // average number of games lost in a day for player
        assertEquals(15.83,
                analyzer.averageNumberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void averageNumberOfGamesInADayForPlayerForOpponent()
    {
        // average number of games played in a day for player for opponent
        assertEquals(3.85,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // average number of games won in a day for player for opponent
        assertEquals(1.9,
                analyzer.averageNumberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // average number of games lost in a day for player for opponent
        assertEquals(1.95,
                analyzer.averageNumberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void averageNumberOfGamesInAWeekForPlayerForOpponent()
    {
        // average number of games played in a week for player for opponent
        assertEquals(19.25,
                analyzer.averageNumberOfGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // average number of games won in a week for player for opponent
        assertEquals(9.5,
                analyzer.averageNumberOfGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // average number of games lost in a week for player for opponent
        assertEquals(9.75,
                analyzer.averageNumberOfGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostGamesPlayed()
    {
        // most games played in a day
        assertEquals(28,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // most games played in a week
        assertEquals(98,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void mostGamesForPlayer()
    {
        // most games played in a day for player
        assertEquals(19,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most games played in a week for player
        assertEquals(59,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most games won in a day for player
        assertEquals(13,
                analyzer.mostGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most games won in a week for player
        assertEquals(35,
                analyzer.mostGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most games lost in a day for player
        assertEquals(11,
                analyzer.mostGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most games lost in a week for player
        assertEquals(32,
                analyzer.mostGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostGamesForPlayerForOpponent()
    {
        // most games played in a day for player for opponent
        assertEquals(18,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most games played in a week for player for opponent
        assertEquals(44,
                analyzer.mostGames(
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most games won in a day for player for opponent
        assertEquals(12,
                analyzer.mostGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most games won in a week for player for opponent
        assertEquals(27,
                analyzer.mostGames(
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most games lost in a day for player for opponent
        assertEquals(9,
                analyzer.mostGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most games lost in a week for player for opponent
        assertEquals(23,
                analyzer.mostGames(
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfIntervalsWithAtLeastOneGamePlayed()
    {
        // number of days with at least one game played
        assertEquals(54,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // number of weeks with at least one game played
        assertEquals(11,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithAtLeastOneGameForPlayer()
    {
        // number of days with at least one game played for player
        assertEquals(37,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with at least one game played for player
        assertEquals(9,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // number of days with at least one game won for player
        assertEquals(35,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with at least one game won for player
        assertEquals(8,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // number of days with at least one game lost for player
        assertEquals(36,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with at least one game lost for player
        assertEquals(9,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithAtLeastOneGameForPlayerForOpponent()
    {
        // number of days with at least one game played for player for opponent
        assertEquals(32,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with at least one game played for player for opponent
        assertEquals(9,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // number of days with at least one game won for player for opponent
        assertEquals(28,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with at least one game won for player for opponent
        assertEquals(8,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // number of days with at least one game lost for player for opponent
        assertEquals(29,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with at least one game lost for player for opponent
        assertEquals(9,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfIntervalsWithoutAnyGamesPlayed()
    {
        // number of days without any games played
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // number of weeks without any games played
        assertEquals(1,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithoutAnyGamesForPlayer()
    {
        // number of days without any games played for player
        assertEquals(23,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks without any games played for player
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // number of days without any games won for player
        assertEquals(25,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks without any games won for player
        assertEquals(4,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // number of days without any games lost for player
        assertEquals(24,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks without any games lost for player
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithoutAnyGamesForPlayerForOpponent()
    {
        // number of days without any games played for player for opponent
        assertEquals(28,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks without any games played for player for opponent
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // number of days without any games won for player for opponent
        assertEquals(32,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks without any games won for player for opponent
        assertEquals(4,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // number of days without any games lost for player for opponent
        assertEquals(31,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks without any games lost for player for opponent
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfIntervalsWithGamesWonGreaterThanGamesLostForPlayer()
    {
        // number of days with games won greater than games lost for player
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with games won greater than games lost for player
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithGamesWonGreaterThanGamesLostForPlayerForOpponent()
    {
        // number of days with games won greater than games lost for player for opponent
        assertEquals(15,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with games won greater than games lost for player for opponent
        assertEquals(4,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfIntervalsWithGamesWonEqualToGamesLostForPlayer()
    {
        // number of days with games won equal to games lost for player
        assertEquals(24,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with games won equal to games lost for player
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithGamesWonEqualToGamesLostForPlayerForOpponent()
    {
        // number of days with games won equal to games lost for player for opponent
        assertEquals(28,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with games won equal to games lost for player for opponent
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void numberOfIntervalsWithGamesWonLessThanGamesLostForPlayer()
    {
        // number of days with games won less than games lost for player
        assertEquals(20,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // number of weeks with games won less than games lost for player
        assertEquals(7,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void numberOfIntervalsWithGamesWonLessThanGamesLostForPlayerForOpponent()
    {
        // number of days with games won less than games lost for player for opponent
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // number of weeks with games won less than games lost for player for opponent
        assertEquals(5,
                analyzer.numberOfIntervals(
                        Intervals.ANY,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostConsecutiveIntervalsWithAtLeastOneGamePlayed()
    {
        // most consecutive days with at least one game played
        assertEquals(30,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // most consecutive weeks with at least one game played
        assertEquals(11,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithAtLeastOneGameForPlayer()
    {
        // most consecutive days with at least one game played for player
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with at least one game played for player
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive days with at least one game won for player
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with at least one game won for player
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive days with at least one game lost for player
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with at least one game lost for player
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithAtLeastOneGameForPlayerForOpponent()
    {
        // most consecutive days with at least one game played for player for opponent
        assertEquals(10,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with at least one game played for player for opponent
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive days with at least one game won for player for opponent
        assertEquals(10,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with at least one game won for player for opponent
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive days with at least one game lost for player for opponent
        assertEquals(9,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with at least one game lost for player for opponent
        assertEquals(6,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.AT_LEAST_ONE,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostConsecutiveIntervalsWithoutAnyGamesPlayed()
    {
        // most consecutive days without any games played
        assertEquals(5,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANY,
                        Player.ANY));
        // most consecutive weeks without any games played
        assertEquals(1,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithoutAnyGamesForPlayer()
    {
        // most consecutive days without any games played for player
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks without any games played for player
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive days without any games won for player
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks without any games won for player
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive days without any games lost for player
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks without any games lost for player
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithoutAnyGamesForPlayerForOpponent()
    {
        // most consecutive days without any games played for player for opponent
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks without any games played for player for opponent
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive days without any games won for player for opponent
        assertEquals(17,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks without any games won for player for opponent
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive days without any games lost for player for opponent
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks without any games lost for player for opponent
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.WITHOUT_ANY,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonGreaterThanGamesLostForPlayer()
    {
        // most consecutive days with games won greater than games lost for player
        assertEquals(7,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with games won greater than games lost for player
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonGreaterThanGamesLostForPlayerForOpponent()
    {
        // most consecutive days with games won greater than games lost for player for opponent
        assertEquals(7,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with games won greater than games lost for player for opponent
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.MORE_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonEqualToGamesLostForPlayer()
    {
        // most consecutive days with games won equal to games lost for player
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with games won equal to games lost for player
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonEqualToGamesLostForPlayerForOpponent()
    {
        // most consecutive days with games won equal to games lost for player for opponent
        assertEquals(16,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with games won equal to games lost for player for opponent
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.EQUALLY_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonLessThanGamesLostForPlayer()
    {
        // most consecutive days with games won less than games lost for player
        assertEquals(3,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.ANY));
        // most consecutive weeks with games won less than games lost for player
        assertEquals(4,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void mostConsecutiveIntervalsWithGamesWonLessThanGamesLostForPlayerForOpponent()
    {
        // most consecutive days with games won less than games lost for player for opponent
        assertEquals(4,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        1,
                        Player.ANTONIO,
                        Player.KIT));
        // most consecutive weeks with games won less than games lost for player for opponent
        assertEquals(2,
                analyzer.numberOfIntervals(
                        Intervals.MOST_CONSECUTIVE,
                        IntervalGames.LESS_FREQUENT,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        5,
                        Player.ANTONIO,
                        Player.KIT));
    }

    @Test
    public void dateOf100GamesPlayed()
    {
        // date of 100 games played
        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 26).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANY,
                        Player.ANY));
    }

    @Test
    public void dateOf100GamesForPlayer()
    {
        // date of 100 games played for player
        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 31).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
        // date of 100 games won for player
        assertEquals(new GregorianCalendar(2018, Calendar.AUGUST, 16).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
        // date of 100 games lost for player
        assertEquals(new GregorianCalendar(2018, Calendar.AUGUST, 10).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.ANY));
    }

    @Test
    public void dateOf100GamesForPlayerForOpponent()
    {
        // date of 100 games played for player for opponent
        assertEquals(new GregorianCalendar(2018, Calendar.AUGUST, 13).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.ANY,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
        // date of 100 games won for player for opponent
        assertEquals(new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.WIN,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
        // date of 100 games lost for player for opponent
        assertEquals(new GregorianCalendar(2018, Calendar.SEPTEMBER, 20).getTime(),
                analyzer.dateOfNumberOfGames(
                        100,
                        GameOutcome.LOSE,
                        earliestDate,
                        latestDate,
                        Player.ANTONIO,
                        Player.KIT));
    }
}
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
        assertEquals(27,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        // percentage of games won for date range for player
        assertEquals(39,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        // percentage of games won for player
        assertEquals(48,
                analyzer.percentageOfGamesWithOutcome(
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
        assertEquals(73,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        // percentage of games lost for date range for player
        assertEquals(61,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        // percentage of games lost for player
        assertEquals(52,
                analyzer.percentageOfGamesWithOutcome(
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
        assertEquals(29,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        // percentage of games won for date range for player for opponent
        assertEquals(45,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        // percentage of games won for player for opponent
        assertEquals(49,
                analyzer.percentageOfGamesWithOutcome(
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
        assertEquals(71,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        // percentage of games lost for date range for player for opponent
        assertEquals(55,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        // percentage of games lost for player for opponent
        assertEquals(51,
                analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
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
}
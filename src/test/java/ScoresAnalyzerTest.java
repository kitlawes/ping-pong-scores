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
    public void numberOfGamesPlayedForPlayer()
    {
        // number of games played for date for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY),
                11);
        // number of games played for date range for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY),
                36);
        // number of games played for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY),
                368);
    }

    @Test
    public void numberOfGamesWonForPlayer()
    {
        // number of games won for date for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY),
                3);
        // number of games won for date range for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY),
                14);
        // number of games won for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY),
                178);
    }

    @Test
    public void numberOfGamesLostForPlayer()
    {
        // number of games lost for date for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY),
                8);
        // number of games lost for date range for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY),
                22);
        // number of games lost for player
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY),
                190);
    }

    @Test
    public void numberOfGamesPlayedForPlayerForOpponent()
    {
        // number of games played for date for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                7);
        // number of games played for date range for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                11);
        // number of games played for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                231);
    }

    @Test
    public void numberOfGamesWonForPlayerForOpponent()
    {
        // number of games won for date for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                2);
        // number of games won for date range for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                5);
        // number of games won for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                114);
    }

    @Test
    public void numberOfGamesLostForPlayerForOpponent()
    {
        // number of games lost for date for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                5);
        // number of games lost for date range for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                6);
        // number of games lost for player for opponent
        assertEquals(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                117);
    }

    @Test
    public void winnerForPlayerForOpponent()
    {
        // winner for date for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                Player.KIT);
        // winner for date range for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                Player.KIT);
        // winner for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                Player.KIT);
    }

    @Test
    public void loserForPlayerForOpponent()
    {
        // loser for date for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                Player.ANTONIO);
        // loser for date range for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                Player.ANTONIO);
        // loser for player for opponent
        assertEquals(analyzer.playerWithOutcome(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                Player.ANTONIO);
    }

    @Test
    public void percentageOfGamesWonForPlayer()
    {
        // percentage of games won for date for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY),
                27);
        // percentage of games won for date range for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY),
                39);
        // percentage of games won for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY),
                48);
    }

    @Test
    public void percentageOfGamesLostForPlayer()
    {
        // percentage of games lost for date for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY),
                73);
        // percentage of games lost for date range for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY),
                61);
        // percentage of games lost for player
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY),
                52);
    }

    @Test
    public void percentageOfGamesWonForPlayerForOpponent()
    {
        // percentage of games won for date for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                29);
        // percentage of games won for date range for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                45);
        // percentage of games won for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                49);
    }

    @Test
    public void percentageOfGamesLostForPlayerForOpponent()
    {
        // percentage of games lost for date for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT),
                71);
        // percentage of games lost for date range for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT),
                55);
        // percentage of games lost for player for opponent
        assertEquals(analyzer.percentageOfGamesWithOutcome(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT),
                51);
    }

    @Test
    @Disabled
    public void averageGamesPlayedForDateForPlayer()
    {
        // average games played in a day
        assertEquals(analyzer.averageNumberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                Player.ANY,
                Player.ANY),
                0);


        // average games won in a week for player for opponent
        assertEquals(analyzer.averageNumberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                Player.ANTONIO,
                Player.KIT),
                0);
    }
}
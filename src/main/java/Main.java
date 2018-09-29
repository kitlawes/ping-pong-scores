import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = reader.readSpreadsheet();

        ValueParser parser = new ValueParser(values);
        
        ScoresAnalyzer analyzer = new ScoresAnalyzer(parser);
        
        final Date earliestDate = parser.getEarliestDate();
        final Date latestDate = parser.getLatestDate();
        
        System.out.println("numberOfGamesPlayedForDateForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesPlayedForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesPlayedForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("numberOfGamesWonForDateForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesWonForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesWonForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("numberOfGamesLostForDateForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesLostForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("numberOfGamesLostForPlayer");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("numberOfGamesPlayedForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesPlayedForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesPlayedForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("numberOfGamesWonForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesWonForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesWonForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("numberOfGamesLostForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesLostForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesLostForPlayerForOpponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
    }
}
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
        
        System.out.println("number of games played for date for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games played for date range for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games played for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("number of games won for date for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games won for date range for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games won for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("number of games lost for date for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games lost for date range for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("number of games lost for player");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("number of games played for date for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games played for date range for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games played for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("number of games won for date for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games won for date range for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games won for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("number of games lost for date for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games lost for date range for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("number of games lost for player for opponent");
        System.out.println(analyzer.numberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("winner for date for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("winner for date range for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("winner for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("loser for date for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("loser for date range for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("loser for player for opponent");
        System.out.println(analyzer.player(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("percentage of games won for date for player");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("percentage of games won for date range for player");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("percentage of games won for player");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("percentage of games lost for date for player");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("percentage of games lost for date range for player");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.ANY));
        System.out.println("percentage of games lost for player");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.ANY));
        
        System.out.println();
        System.out.println("percentage of games won for date for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("percentage of games won for date range for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("percentage of games won for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
        System.out.println("percentage of games lost for date for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("percentage of games lost for date range for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("percentage of games lost for player for opponent");
        System.out.println(analyzer.percentage(
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                Player.ANTONIO,
                Player.KIT));
    }
}
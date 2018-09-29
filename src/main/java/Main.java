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
        System.out.println("numberOfGamesPlayedForDateForPlayer");
        System.out.println(analyzer.numberOfGamesPlayedForDateForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesPlayedForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGamesPlayedForDateRangeForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesPlayedForPlayer");
        System.out.println(analyzer.numberOfGamesPlayedForPlayer(
                Player.ANTONIO));
        System.out.println("numberOfGamesWonForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesWonForDateForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesWonForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesWonForDateRangeForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesWonForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesWonForPlayerForOpponent(
                Player.ANTONIO,
                Player.KIT));
    }
}
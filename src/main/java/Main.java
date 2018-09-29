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
        
        System.out.println();
        System.out.println("numberOfGamesWonForDateForPlayer");
        System.out.println(analyzer.numberOfGamesWonForDateForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesWonForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGamesWonForDateRangeForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesWonForPlayer");
        System.out.println(analyzer.numberOfGamesWonForPlayer(
                Player.ANTONIO));
        
        System.out.println();
        System.out.println("numberOfGamesLostForDateForPlayer");
        System.out.println(analyzer.numberOfGamesLostForDateForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesLostForDateRangeForPlayer");
        System.out.println(analyzer.numberOfGamesLostForDateRangeForPlayer(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO));
        System.out.println("numberOfGamesLostForPlayer");
        System.out.println(analyzer.numberOfGamesLostForPlayer(
                Player.ANTONIO));
        
        System.out.println();
        System.out.println("numberOfGamesPlayedForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesPlayedForDateForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesPlayedForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesPlayedForDateRangeForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesPlayedForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesPlayedForPlayerForOpponent(
                Player.ANTONIO,
                Player.KIT));
        
        System.out.println();
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
        
        System.out.println();
        System.out.println("numberOfGamesLostForDateForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesLostForDateForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesLostForDateRangeForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesLostForDateRangeForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                new GregorianCalendar(2018, Calendar.JULY, 20).getTime(),
                Player.ANTONIO,
                Player.KIT));
        System.out.println("numberOfGamesLostForPlayerForOpponent");
        System.out.println(analyzer.numberOfGamesLostForPlayerForOpponent(
                Player.ANTONIO,
                Player.KIT));
    }
}
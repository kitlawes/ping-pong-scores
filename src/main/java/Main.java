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
        analyzer.numberOfGamesWonForDateForPlayerForOpponent(
                new GregorianCalendar(2018, Calendar.JULY, 16).getTime(),
                Player.ANTONIO,
                Player.KIT);
    }
}
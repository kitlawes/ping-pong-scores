import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = reader.readSpreadsheet();

        ValueParser parser = new ValueParser();
        List<Score> scores = parser.parseValues(values);
        System.out.println(scores);
    }
}
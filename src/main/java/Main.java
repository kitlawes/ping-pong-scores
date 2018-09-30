import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = reader.readSpreadsheet();
        ValueParser parser = new ValueParser(values);
        ScoresAnalyzer analyzer = new ScoresAnalyzer(parser);
        PerformanceGraph graph = new PerformanceGraph(parser, analyzer);
        graph.drawPerformanceGraph();
    }
}
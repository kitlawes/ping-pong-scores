import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class Main
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = reader.readSpreadsheet();
        ValueParser parser = new ValueParser(values);
        ScoresAnalyzer analyzer = new ScoresAnalyzer(parser);
//        PerformanceGraph graph = new PerformanceGraph(parser, analyzer);
//        graph.drawPerformanceGraph();

        final Date earliestDate = parser.getEarliestDate();
        final Date latestDate = parser.getLatestDate();

        System.out.println("players ordered by number of games played");
        System.out.println(analyzer.playersOrderedByNumberOfGames(
                GameOutcome.ANY,
                earliestDate,
                latestDate));
        System.out.println("players ordered by number of games won");
        System.out.println(analyzer.playersOrderedByNumberOfGames(
                GameOutcome.WIN,
                earliestDate,
                latestDate));
        System.out.println("players ordered by number of games lost");
        System.out.println(analyzer.playersOrderedByNumberOfGames(
                GameOutcome.LOSE,
                earliestDate,
                latestDate));

    }
}
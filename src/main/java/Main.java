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
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by number of games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by number of games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("pairs of players ordered by number of games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by number of games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by number of games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("players ordered by percentage of games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.PERCENTAGE_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by number of games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.PERCENTAGE_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("pairs of players ordered by percentage of games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.PERCENTAGE_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by number of games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.PERCENTAGE_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("players ordered by average number of games played in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games won in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games lost in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games played in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by average number of games won in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by average number of games lost in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by average number of games played in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games won in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games lost in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games played in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players by average number of games won in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players by average number of games lost in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most games played in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games won in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games lost in a day");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games played in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most games won in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most games lost in a week");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most games played in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games won in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games lost in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games played in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most games won in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most games lost in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.MOST_GAMES,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));
        
        System.out.println();
        System.out.println("players ordered by days with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));
    }
}
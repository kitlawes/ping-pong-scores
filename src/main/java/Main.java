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
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
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

        System.out.println();
        System.out.println("players ordered by days without any games played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days without any games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days without any games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks without any games played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks without any games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks without any games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by days with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days without any games played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days without any games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days without any games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks without any games played");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks without any games won");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks without any games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                OrderCriterion.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        int gamesPlayed = 0;
        while (true)
        {
            gamesPlayed += 100;
            List<Map.Entry<Player, Object>> orderedPlayers = analyzer.orderedPlayers(
                    OrderCriterion.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesPlayed,
                    GameOutcome.ANY,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesPlayed + " games played");
                System.out.println(orderedPlayers);
            } else
            {
                break;
            }
        }

        System.out.println();
        int gamesWon = 0;
        while (true)
        {
            gamesWon += 100;
            List<Map.Entry<Player, Object>> orderedPlayers = analyzer.orderedPlayers(
                    OrderCriterion.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesWon,
                    GameOutcome.WIN,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesWon + " games won");
                System.out.println(orderedPlayers);
            } else
            {
                break;
            }
        }

        System.out.println();
        int gamesLost = 0;
        while (true)
        {
            gamesLost += 100;
            List<Map.Entry<Player, Object>> orderedPlayers = analyzer.orderedPlayers(
                    OrderCriterion.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesLost,
                    GameOutcome.LOSE,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesLost + " games lost");
                System.out.println(orderedPlayers);
            } else
            {
                break;
            }
        }
    }
}
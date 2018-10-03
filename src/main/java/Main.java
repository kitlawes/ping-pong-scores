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

        final Date earliestDate = parser.getEarliestDate();
        final Date latestDate = parser.getLatestDate();

//        LineGraph lineGraph = new LineGraph(parser, analyzer);
//        lineGraph.drawGraph(Statistic.PERCENTAGE_OF_GAMES,
//                null,
//                null,
//                GameOutcome.WIN,
//                null,
//                earliestDate,
//                latestDate,
//                new PlayerPair[]{PlayerPair.getPlayerPair("KIT-ANY"),
//                        PlayerPair.getPlayerPair("HUNOR-ANY")},
//                true);
        BarChart barChart = new BarChart(analyzer);
        barChart.drawGraph(Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null,
                new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-ANY"),
                        PlayerPair.getPlayerPair("KIT-ANY"),
                        PlayerPair.getPlayerPair("HUNOR-ANY"),
                        PlayerPair.getPlayerPair("JIPESH-ANY"),
                        PlayerPair.getPlayerPair("ANY-ANY")});

        if (true)
        {
            return;
        }

        System.out.println("players ordered by number of games played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by number of games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by number of games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
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
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by number of games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by number of games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("players ordered by percentage of games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by percentage of games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
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
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("pairs of players ordered by percentage of games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("players ordered by average percentage of games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null));
        System.out.println("players ordered by average percentage of games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null));

        System.out.println();
        System.out.println("players ordered by average number of games played in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games won in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games lost in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by average number of games played in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by average number of games won in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by average number of games lost in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
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
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games won in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games lost in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players by average number of games played in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players by average number of games won in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players by average number of games lost in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most games played in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games won in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games lost in a day");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most games played in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most games won in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most games lost in a week");
        System.out.println(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
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
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games won in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games lost in a day");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most games played in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most games won in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most games lost in a week");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by days with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by days without any games played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days without any games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days without any games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks without any games played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks without any games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks without any games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by days with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by days with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by weeks with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by days with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by weeks with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks with at least one game played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days without any games played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days without any games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days without any games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks without any games played");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks without any games won");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks without any games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks without any games played");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games won");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive days with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("players ordered by most consecutive weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("players ordered by most consecutive weeks with games won less than games lost");
        System.out.println(analyzer.orderedPlayers(
                Statistic.INTERVALS,
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
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with games won greater than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won equal to games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won less than games lost");
        System.out.println(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
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
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
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
            }
            else
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
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
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
            }
            else
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
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
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
            }
            else
            {
                break;
            }
        }

        System.out.println();
        gamesPlayed = 0;
        while (true)
        {
            gamesPlayed += 100;
            List<Map.Entry<PlayerPair, Object>> orderedPairsOfPlayers = analyzer.orderedPairsOfPlayers(
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesPlayed,
                    GameOutcome.ANY,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesPlayed + " games played");
                System.out.println(orderedPairsOfPlayers);
            }
            else
            {
                break;
            }
        }

        System.out.println();
        gamesWon = 0;
        while (true)
        {
            gamesWon += 100;
            List<Map.Entry<PlayerPair, Object>> orderedPairsOfPlayers = analyzer.orderedPairsOfPlayers(
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesWon,
                    GameOutcome.WIN,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesWon + " games won");
                System.out.println(orderedPairsOfPlayers);
            }
            else
            {
                break;
            }
        }

        System.out.println();
        gamesLost = 0;
        while (true)
        {
            gamesLost += 100;
            List<Map.Entry<PlayerPair, Object>> orderedPairsOfPlayers = analyzer.orderedPairsOfPlayers(
                    Statistic.DATE_OF_NUMBER_OF_GAMES,
                    null,
                    null,
                    gamesLost,
                    GameOutcome.LOSE,
                    earliestDate,
                    latestDate,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesLost + " games lost");
                System.out.println(orderedPairsOfPlayers);
            }
            else
            {
                break;
            }
        }
    }
}
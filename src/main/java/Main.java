import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        SpreadsheetReader reader = new SpreadsheetReader();
        List<List<Object>> values = reader.readSpreadsheet();
        ValueParser parser = new ValueParser(values);
        ScoresAnalyzer analyzer = new ScoresAnalyzer(parser);

        FileWriter writer = new FileWriter(parser, analyzer);
        writer.writeFile();

        if (true)
        {
            return;
        }

        final Date EARLIEST_DATE = parser.getEarliestDate();
        final Date LATEST_DATE = parser.getLatestDate();

        System.out.println("players ordered by number of games played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("players ordered by number of games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("players ordered by number of games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                null));

        System.out.println();
        System.out.println("pairs of players ordered by number of games played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("pairs of players ordered by number of games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("pairs of players ordered by number of games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                null));

        System.out.println();
        System.out.println("players ordered by percentage of games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("players ordered by percentage of games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                null));

        System.out.println();
        System.out.println("pairs of players ordered by percentage of games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("pairs of players ordered by percentage of games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                null));

        System.out.println();
        System.out.println("players ordered by average percentage of games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                null));
        System.out.println("players ordered by average percentage of games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                null));

        System.out.println();
        System.out.println("players ordered by average number of games played in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by average number of games won in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by average number of games lost in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by average number of games played in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by average number of games won in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by average number of games lost in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by average number of games played in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players by average number of games won in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players by average number of games lost in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players by average number of games played in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players by average number of games won in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players by average number of games lost in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by most games played in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most games won in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most games lost in a day");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most games played in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most games won in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most games lost in a week");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most games played in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most games won in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most games lost in a day");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most games played in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most games won in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most games lost in a week");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by days with at least one game played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days with at least one game won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days with at least one game lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by weeks with at least one game played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks with at least one game won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks with at least one game lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days with at least one game played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days with at least one game won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days with at least one game lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by weeks with at least one game played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks with at least one game lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by days without any games played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days without any games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days without any games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by weeks without any games played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks without any games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks without any games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days without any games played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days without any games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days without any games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by weeks without any games played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks without any games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks without any games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by days with games won greater than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days with games won equal to games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by days with games won less than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by weeks with games won greater than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks with games won equal to games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by weeks with games won less than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by days with games won greater than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days with games won equal to games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by days with games won less than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by weeks with games won greater than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks with games won equal to games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by weeks with games won less than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with at least one game played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days with at least one game won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days with at least one game lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive weeks with at least one game played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks with at least one game lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days with at least one game played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days with at least one game lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with at least one game lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days without any games played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days without any games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days without any games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive weeks without any games played");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks without any games won");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks without any games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days without any games played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days without any games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks without any games played");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games won");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks without any games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("players ordered by most consecutive days with games won greater than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days with games won equal to games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive days with games won less than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("players ordered by most consecutive weeks with games won greater than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks with games won equal to games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("players ordered by most consecutive weeks with games won less than games lost");
        prettyPrint(analyzer.orderedPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));

        System.out.println();
        System.out.println("pairs of players ordered by most consecutive days with games won greater than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won equal to games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive days with games won less than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                1));
        System.out.println("pairs of players ordered by most consecutive weeks with games won greater than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won equal to games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
                5));
        System.out.println("pairs of players ordered by most consecutive weeks with games won less than games lost");
        prettyPrint(analyzer.orderedPairsOfPlayers(
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                EARLIEST_DATE,
                LATEST_DATE,
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesPlayed + " games played");
                prettyPrint(orderedPlayers);
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesWon + " games won");
                prettyPrint(orderedPlayers);
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPlayers.isEmpty())
            {
                System.out.println("players ordered by date of " + gamesLost + " games lost");
                prettyPrint(orderedPlayers);
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesPlayed + " games played");
                prettyPrint(orderedPairsOfPlayers);
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesWon + " games won");
                prettyPrint(orderedPairsOfPlayers);
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
                    EARLIEST_DATE,
                    LATEST_DATE,
                    1);
            if (!orderedPairsOfPlayers.isEmpty())
            {
                System.out.println("pairs of players ordered by date of " + gamesLost + " games lost");
                prettyPrint(orderedPairsOfPlayers);
            }
            else
            {
                break;
            }
        }
    }

    public static <T> void prettyPrint(List<Map.Entry<T, Object>> orderedPlayersOrPlayerPairs)
    {
        System.out.print("[");
        for (int i = 0; i < orderedPlayersOrPlayerPairs.size(); i++)
        {
            if (i > 0)
            {
                System.out.print(", ");
            }
            Map.Entry<T, Object> playerOrPlayerPair = orderedPlayersOrPlayerPairs.get(i);
            T key = playerOrPlayerPair.getKey();
            Object value = playerOrPlayerPair.getValue();
            System.out.print(key + "=");
            if (value instanceof Integer)
            {
                System.out.print(value);
            }
            if (value instanceof Double)
            {
                DecimalFormat formatter = new DecimalFormat("0.00");
                System.out.print(formatter.format(value));
            }
            if (value instanceof Date)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd");
                System.out.print(formatter.format(value));
            }
        }
        System.out.println("]");
    }
}
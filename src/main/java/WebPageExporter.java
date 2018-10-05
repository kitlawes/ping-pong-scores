import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class WebPageExporter
{
    private ScoresAnalyzer analyzer;
    private BarChart barChart;
    private LineGraph lineGraph;

    private Date earliestDate;
    private Date latestDate;

    private static final PlayerPair[] ANY_ANY = new PlayerPair[]{PlayerPair.getPlayerPair("ANY-ANY")};
    private static final PlayerPair[] PLAYER_ANY = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-ANY"),
            PlayerPair.getPlayerPair("KIT-ANY"),
            PlayerPair.getPlayerPair("HUNOR-ANY"),
            PlayerPair.getPlayerPair("JIPESH-ANY")};
    private static final PlayerPair[] PLAYER_PLAYER_UNORDERED = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-KIT"),
            PlayerPair.getPlayerPair("ANTONIO-HUNOR"),
            PlayerPair.getPlayerPair("ANTONIO-JIPESH"),
            PlayerPair.getPlayerPair("KIT-HUNOR"),
            PlayerPair.getPlayerPair("KIT-JIPESH"),
            PlayerPair.getPlayerPair("HUNOR-JIPESH")};
    private static final PlayerPair[] PLAYER_PLAYER_ORDERED = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-KIT"),
            PlayerPair.getPlayerPair("ANTONIO-HUNOR"),
            PlayerPair.getPlayerPair("ANTONIO-JIPESH"),
            PlayerPair.getPlayerPair("KIT-ANTONIO"),
            PlayerPair.getPlayerPair("KIT-HUNOR"),
            PlayerPair.getPlayerPair("KIT-JIPESH"),
            PlayerPair.getPlayerPair("HUNOR-ANTONIO"),
            PlayerPair.getPlayerPair("HUNOR-KIT"),
            PlayerPair.getPlayerPair("HUNOR-JIPESH"),
            PlayerPair.getPlayerPair("JIPESH-ANTONIO"),
            PlayerPair.getPlayerPair("JIPESH-HUNOR"),
            PlayerPair.getPlayerPair("JIPESH-KIT")};

    private int textHeight;
    private int margin;
    private int graphWidth;
    private int graphHeight;
    private int graphLeftOffset;
    private int graphBottomOffset;
    private int statisticsIndex;
    private int imageWidth;
    private int imageHeight;

    public WebPageExporter(ValueParser parser, ScoresAnalyzer analyzer)
    {
        this.analyzer = analyzer;
        barChart = new BarChart(analyzer);
        lineGraph = new LineGraph(parser, analyzer);

        earliestDate = parser.getEarliestDate();
        latestDate = parser.getLatestDate();

        textHeight = 16;
        margin = 10;
        graphWidth = 500;
        graphHeight = 250;
        graphLeftOffset = 250;
        graphBottomOffset = 250;
        imageWidth = (margin + graphLeftOffset + graphWidth) * 3 + margin;
        imageHeight = textHeight * 3 + margin + graphHeight + graphBottomOffset + margin;
        statisticsIndex = 0;
    }

    public void exportToWebPage()
    {
        File directory = new File("ping_pong_statistics");
        if (!directory.exists())
        {
            directory.mkdir();
        }
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("ping_pong_statistics/ping_pong_statistics.html", "UTF-8");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        exportStatistics(writer,
                "number of games played",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by number of games played",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by number of games won",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by number of games lost",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by number of games played",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by number of games won",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by number of games lost",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "players ordered by percentage of games won",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by percentage of games lost",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by percentage of games won",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by percentage of games lost",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "players ordered by average percentage of games won",
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average percentage of games lost",
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null,
                PLAYER_ANY);

        exportStatistics(writer,
                "average number of games played in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);
        exportStatistics(writer,
                "average number of games played in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by average number of games played in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average number of games won in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average number of games lost in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average number of games played in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average number of games won in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by average number of games lost in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by average number of games played in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players by average number of games won in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players by average number of games lost in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players by average number of games played in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players by average number of games won in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players by average number of games lost in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "most games played in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);
        exportStatistics(writer,
                "most games played in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by most games played in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most games won in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most games lost in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most games played in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most games won in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most games lost in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by most games played in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most games won in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most games lost in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most games played in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most games won in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most games lost in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "days with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);
        exportStatistics(writer,
                "weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by days with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by days with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "days without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);
        exportStatistics(writer,
                "weeks without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by days without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by days without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "players ordered by days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "most consecutive days with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by most consecutive days with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "most consecutive days without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY);

        exportStatistics(writer,
                "players ordered by most consecutive days without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by most consecutive days without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_UNORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        exportStatistics(writer,
                "players ordered by most consecutive days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);
        exportStatistics(writer,
                "players ordered by most consecutive weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_ANY);

        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);
        exportStatistics(writer,
                "pairs of players ordered by most consecutive weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5,
                PLAYER_PLAYER_ORDERED);

        int gamesPlayed = 100;
        while (exportStatistics(writer,
                "date of " + gamesPlayed + " games played",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesPlayed,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                ANY_ANY))
        {
            gamesPlayed += 100;
        }

        gamesPlayed = 100;
        while (exportStatistics(writer,
                "players ordered by date of " + gamesPlayed + " games played",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesPlayed,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY))
        {
            gamesPlayed += 100;
        }

        int gamesWon = 100;
        while (exportStatistics(writer,
                "players ordered by date of " + gamesWon + " games won",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesWon,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY))
        {
            gamesWon += 100;
        }

        int gamesLost = 100;
        while (exportStatistics(writer,
                "players ordered by date of " + gamesLost + " games lost",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesLost,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_ANY))
        {
            gamesLost += 100;
        }

        gamesPlayed = 100;
        while (exportStatistics(writer,
                "pairs of players ordered by date of " + gamesPlayed + " games played",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesPlayed,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_UNORDERED))
        {
            gamesPlayed += 100;
        }

        gamesWon = 100;
        while (exportStatistics(writer,
                "pairs of players ordered by date of " + gamesWon + " games won",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesWon,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED))
        {
            gamesWon += 100;
        }

        gamesLost = 100;
        while (exportStatistics(writer,
                "pairs of players ordered by date of " + gamesLost + " games lost",
                Statistic.DATE_OF_NUMBER_OF_GAMES,
                null,
                null,
                gamesLost,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1,
                PLAYER_PLAYER_ORDERED))
        {
            gamesLost += 100;
        }

        writer.close();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
    }

    public boolean exportStatistics(PrintWriter writer, String description, Statistic statistic, Intervals intervals,
                                    IntervalGames intervalGames, Integer numberOfGames, GameOutcome outcome,
                                    Date start, Date end, Integer intervalDays, PlayerPair[] playerPairs)
    {
        System.out.println(description);

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(238, 238, 238));
        g2d.fillRect(0, 0, imageWidth, imageHeight);
        g2d.setColor(Color.BLACK);

        String statisticsText = null;
        if (playerPairs == ANY_ANY)
        {
            List<Map.Entry<Player, Object>> orderedPlayers = new ArrayList<>();
            Map<Player, Object> values = new HashMap<>();
            Object value = analyzer.statistic(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays, Player.ANY, Player.ANY);
            if (value != null)
            {
                values.put(Player.ANY, value);
                orderedPlayers.add(values.entrySet().iterator().next());
            }
            if (orderedPlayers.isEmpty())
            {
                return false;
            }
            statisticsText = prettyPrint(orderedPlayers);
        }
        if (playerPairs == PLAYER_ANY)
        {
            List<Map.Entry<Player, Object>> orderedPlayers = analyzer.orderedPlayers(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays);
            if (orderedPlayers.isEmpty())
            {
                return false;
            }
            statisticsText = prettyPrint(orderedPlayers);
        }
        if (playerPairs == PLAYER_PLAYER_ORDERED || playerPairs == PLAYER_PLAYER_UNORDERED)
        {
            List<Map.Entry<PlayerPair, Object>> orderedPairsOfPlayers = analyzer.orderedPairsOfPlayers(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays);
            if (orderedPairsOfPlayers.isEmpty())
            {
                return false;
            }
            statisticsText = prettyPrint(orderedPairsOfPlayers);
        }

        g2d.drawString(description, 10, textHeight);
        g2d.translate(0, textHeight);
        g2d.drawString(statisticsText, 10, textHeight);
        g2d.translate(0, textHeight);
        g2d.drawString("bar chart", 10, textHeight);
        g2d.translate(0, textHeight);
        JFrame jFrame = barChart.drawGraph(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays, playerPairs);
        jFrame.getContentPane().getComponent(0).paint(g2d);
        jFrame.setVisible(false);
        g2d.translate(margin + graphLeftOffset + graphWidth, -textHeight);
        g2d.drawString("moving average line graph", 10, textHeight);
        g2d.translate(0, textHeight);
        jFrame = lineGraph.drawGraph(statistic, intervals, intervalGames, outcome, start, end, intervalDays, playerPairs, false, 15);
        jFrame.getContentPane().getComponent(0).paint(g2d);
        jFrame.setVisible(false);
        g2d.translate(margin + graphLeftOffset + graphWidth, -textHeight);
        g2d.drawString("cumulative moving average line graph", 10, textHeight);
        g2d.translate(0, textHeight);
        jFrame = lineGraph.drawGraph(statistic, intervals, intervalGames, outcome, start, end, intervalDays, playerPairs, true, 5);
        jFrame.getContentPane().getComponent(0).paint(g2d);
        jFrame.setVisible(false);
        g2d.translate(-(margin + graphLeftOffset + graphWidth) * 2, margin + graphHeight + graphBottomOffset + margin);

        statisticsIndex++;
        try
        {
            ImageIO.write(image, "png", new File("ping_pong_statistics/graphs_" + statisticsIndex + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        writer.write("<img src=\"graphs_" + statisticsIndex + ".png\">\n");

        return true;
    }

    public <T> String prettyPrint(List<Map.Entry<T, Object>> orderedPlayersOrPlayerPairs)
    {
        String string = "[";
        for (int i = 0; i < orderedPlayersOrPlayerPairs.size(); i++)
        {
            if (i > 0)
            {
                string += ", ";
            }
            Map.Entry<T, Object> playerOrPlayerPair = orderedPlayersOrPlayerPairs.get(i);
            T key = playerOrPlayerPair.getKey();
            Object value = playerOrPlayerPair.getValue();
            string += key + "=";
            if (value instanceof Integer)
            {
                string += value;
            }
            if (value instanceof Double)
            {
                DecimalFormat formatter = new DecimalFormat("0.00");
                string += formatter.format(value);
            }
            if (value instanceof Date)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd");
                string += formatter.format(value);
            }
        }
        string += "]";
        return string;
    }
}
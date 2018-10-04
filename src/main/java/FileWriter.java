import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class FileWriter
{
    private ValueParser parser;
    private ScoresAnalyzer analyzer;
    private BarChart barChart;
    private LineGraph lineGraph;

    private Date earliestDate;
    private Date latestDate;

    private PlayerPair[] ANY_ANY;
    private PlayerPair[] PLAYER_ANY;
    private PlayerPair[] PLAYER_PLAYER_UNORDERED;
    private PlayerPair[] PLAYER_PLAYER_ORDERED;

    private int textHeight;
    private int margin;
    private int graphWidth;
    private int graphHeight;
    private int graphLeftOffset;
    private int graphBottomOffset;
    private int numberOfStatistics;
    private int fileWidth;
    private int fileHeight;

    public FileWriter(ValueParser parser, ScoresAnalyzer analyzer)
    {
        this.parser = parser;
        this.analyzer = analyzer;
        barChart = new BarChart(analyzer);
        lineGraph = new LineGraph(parser, analyzer);

        earliestDate = parser.getEarliestDate();
        latestDate = parser.getLatestDate();

        ANY_ANY = new PlayerPair[]{PlayerPair.getPlayerPair("ANY-ANY")};
        PLAYER_ANY = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-ANY"),
                PlayerPair.getPlayerPair("KIT-ANY"),
                PlayerPair.getPlayerPair("HUNOR-ANY"),
                PlayerPair.getPlayerPair("JIPESH-ANY")};
        PLAYER_PLAYER_UNORDERED = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-KIT"),
                PlayerPair.getPlayerPair("ANTONIO-HUNOR"),
                PlayerPair.getPlayerPair("ANTONIO-JIPESH"),
                PlayerPair.getPlayerPair("KIT-HUNOR"),
                PlayerPair.getPlayerPair("KIT-JIPESH"),
                PlayerPair.getPlayerPair("HUNOR-JIPESH")};
        PLAYER_PLAYER_ORDERED = new PlayerPair[]{PlayerPair.getPlayerPair("ANTONIO-KIT"),
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

        textHeight = 16;
        margin = 10;
        graphWidth = 500;
        graphHeight = 250;
        graphLeftOffset = 250;
        graphBottomOffset = 250;
        fileWidth = (margin + graphLeftOffset + graphWidth) * 3 + margin;
        numberOfStatistics = 108;
        fileHeight = textHeight * 3 * numberOfStatistics + (margin + graphHeight + graphBottomOffset) * numberOfStatistics + margin;
    }

    public void writeFile()
    {
        BufferedImage image = new BufferedImage(fileWidth, fileHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        g2d.setColor(new Color(238, 238, 238));
        g2d.fillRect(0, 0, fileWidth, fileHeight);
        g2d.setColor(Color.BLACK);

        drawStatistics(g2d,
                true,
                "players ordered by number of games played",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                true,
                "players ordered by number of games won",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                true,
                "players ordered by number of games lost",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by number of games played",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by number of games won",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by number of games lost",
                Statistic.NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null);

        drawStatistics(g2d,
                true,
                "players ordered by percentage of games won",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                true,
                "players ordered by percentage of games lost",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by percentage of games won",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by percentage of games lost",
                Statistic.PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null);

        drawStatistics(g2d,
                true,
                "players ordered by average percentage of games won",
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                null);
        drawStatistics(g2d,
                true,
                "players ordered by average percentage of games lost",
                Statistic.AVERAGE_PERCENTAGE_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                null);

        drawStatistics(g2d,
                true,
                "players ordered by average number of games played in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by average number of games won in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by average number of games lost in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by average number of games played in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by average number of games won in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by average number of games lost in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by average number of games played in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players by average number of games won in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players by average number of games lost in a day",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players by average number of games played in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players by average number of games won in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players by average number of games lost in a week",
                Statistic.AVERAGE_NUMBER_OF_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by most games played in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most games won in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most games lost in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most games played in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most games won in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most games lost in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games played in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games won in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games lost in a day",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games played in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games won in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most games lost in a week",
                Statistic.MOST_GAMES,
                null,
                null,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by days with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by days without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by weeks without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by days without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks without any games played",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks without any games won",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.ANY,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with at least one game played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with at least one game won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with at least one game lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.AT_LEAST_ONE,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks without any games played",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.ANY,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks without any games won",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks without any games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.WITHOUT_ANY,
                null,
                GameOutcome.LOSE,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                true,
                "players ordered by most consecutive weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);

        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive days with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                1);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with games won greater than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.MORE_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with games won equal to games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.EQUALLY_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);
        drawStatistics(g2d,
                false,
                "pairs of players ordered by most consecutive weeks with games won less than games lost",
                Statistic.INTERVALS,
                Intervals.MOST_CONSECUTIVE,
                IntervalGames.LESS_FREQUENT,
                null,
                GameOutcome.WIN,
                earliestDate,
                latestDate,
                5);

        try
        {
            ImageIO.write(image, "png", new File("ping_pong_statistics.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
    }

    public void drawStatistics(Graphics2D g2d, boolean playerAgainstAny, String description, Statistic statistic,
                               Intervals intervals, IntervalGames intervalGames, Integer numberOfGames,
                               GameOutcome outcome, Date start, Date end, Integer intervalDays)
    {
        System.out.println(description);
        g2d.drawString(description, 10, textHeight);
        g2d.translate(0, textHeight);
        PlayerPair[] playerPairs;
        if (playerAgainstAny)
        {
            g2d.drawString(prettyPrint(analyzer.orderedPlayers(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays)), 10, textHeight);
            playerPairs = PLAYER_ANY;
        }
        else
        {
            g2d.drawString(prettyPrint(analyzer.orderedPairsOfPlayers(statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays)), 10, textHeight);
            if (outcome == GameOutcome.ANY)
            {
                playerPairs = PLAYER_PLAYER_UNORDERED;
            }
            else
            {
                playerPairs = PLAYER_PLAYER_ORDERED;
            }
        }
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
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

public class LineGraph
{
    private ValueParser parser;
    private ScoresAnalyzer analyzer;

    public LineGraph(ValueParser parser, ScoresAnalyzer analyzer)
    {
        this.parser = parser;
        this.analyzer = analyzer;
    }

    public JFrame drawGraph(Statistic statistic, Intervals intervals, IntervalGames intervalGames, GameOutcome outcome,
                            Date start, Date end, Integer intervalDays, final PlayerPair[] playerPairs, boolean cumulative,
                            int movingAverageInterval)
    {
        final List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        Double lowest = null;
        Double highest = null;

        final Map<PlayerPair, Map<Date, Double>> data = new HashMap<>();
        for (PlayerPair playerPair : playerPairs)
        {
            Map<Date, Double> playerPairData = new HashMap<>();
            data.put(playerPair, playerPairData);
            for (int i = 0; i < dates.size(); i++)
            {
                Date date = dates.get(i);
                Double dataPointTotal = new Double(0);
                int dataPointAmount = 0;
                Player player = playerPair.getPlayer();
                Player opponent = playerPair.getOpponent();
                for (int j = Math.max(i - (movingAverageInterval - 1) / 2, 0); j <= Math.min(i + (movingAverageInterval - 1) / 2, dates.size() - 1); j++)
                {
                    Date movingDate = dates.get(j);
                    Date dataPointStart;
                    if (cumulative)
                    {
                        dataPointStart = start;
                    }
                    else
                    {
                        dataPointStart = movingDate;
                    }
                    switch (statistic)
                    {
                        case NUMBER_OF_GAMES:
                            dataPointTotal += new Double(analyzer.numberOfGames(outcome, dataPointStart, movingDate, player, opponent));
                            break;
                        case PERCENTAGE_OF_GAMES:
                            Double dataPoint = analyzer.percentageOfGames(outcome, dataPointStart, movingDate, player, opponent);
                            if (dataPoint != null)
                            {
                                dataPointTotal += dataPoint;
                            }
                            else
                            {
                                continue;
                            }
                            break;
                        case AVERAGE_PERCENTAGE_OF_GAMES:
                            dataPoint = analyzer.averagePercentageOfGames(outcome, dataPointStart, movingDate, player);
                            if (dataPoint != null)
                            {
                                dataPointTotal += dataPoint;
                            }
                            else
                            {
                                continue;
                            }
                            break;
                        case AVERAGE_NUMBER_OF_GAMES:
                            dataPointTotal += analyzer.averageNumberOfGames(outcome, dataPointStart, movingDate, intervalDays, player, opponent);
                            break;
                        case MOST_GAMES:
                            dataPointTotal += new Double(analyzer.mostGames(outcome, dataPointStart, movingDate, intervalDays, player, opponent));
                            break;
                        case INTERVALS:
                            dataPointTotal += new Double(analyzer.numberOfIntervals(intervals, intervalGames, outcome, dataPointStart, movingDate, intervalDays, player, opponent));
                            break;
                    }
                    dataPointAmount++;
                }
                if (dataPointAmount == 0)
                {
                    continue;
                }
                Double dataPoint = dataPointTotal / dataPointAmount;
                playerPairData.put(date, dataPoint);
                if (lowest == null)
                {
                    lowest = dataPoint;
                }
                if (highest == null)
                {
                    highest = dataPoint;
                }
                if (dataPoint != null)
                {
                    lowest = Math.min(lowest, dataPoint);
                    highest = Math.max(highest, dataPoint);
                }
            }
        }
        if ((statistic == Statistic.NUMBER_OF_GAMES
                || statistic == Statistic.MOST_GAMES
                || statistic == Statistic.AVERAGE_NUMBER_OF_GAMES
                || statistic == Statistic.INTERVALS)
                && highest - lowest > 5)
        {
            lowest = Math.floor(lowest / 10) * 10;
            highest = Math.ceil(highest / 10) * 10;
        }

        final Double finalLowest = lowest;
        final Double finalHighest = highest;
        final int margin = 10;
        final int graphWidth = 500;
        final int graphHeight = 250;
        final int graphLeftOffset = 250;
        final int graphBottomOffset = 250;
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);

                int dataAmount = playerPairs.length;

                g.setColor(Color.WHITE);
                g.fillRect(margin + graphLeftOffset, margin, graphWidth, graphHeight);

                List<Color> colours = new ArrayList<>();
                for (int i = 0; i < dataAmount; i++)
                {
                    colours.add(new Color((float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
                }

                int dateAmount = dates.size();
                for (int i = 0; i < dataAmount; i++)
                {
                    Map<Date, Double> playerPairData = data.get(playerPairs[i]);
                    g.setColor(colours.get(i));
                    int j = -1;
                    Double nextDataPoint = null;
                    while (nextDataPoint == null)
                    {
                        j++;
                        nextDataPoint = playerPairData.get(dates.get(j));
                    }
                    DATA_POINT_LOOP:
                    while (nextDataPoint != null)
                    {
                        Double currentDataPoint = nextDataPoint;
                        nextDataPoint = null;
                        int k = j;
                        while (nextDataPoint == null)
                        {
                            k++;
                            if (k == dateAmount)
                            {
                                break DATA_POINT_LOOP;
                            }
                            nextDataPoint = playerPairData.get(dates.get(k));
                        }
                        g.drawLine(margin + graphLeftOffset + (int) Math.round((double) j / (dateAmount - 1) * graphWidth),
                                margin + (int) Math.round((finalHighest - currentDataPoint) / (finalHighest - finalLowest) * graphHeight),
                                margin + graphLeftOffset + (int) Math.round((double) k / (dateAmount - 1) * graphWidth),
                                margin + (int) Math.round((finalHighest - nextDataPoint) / (finalHighest - finalLowest) * graphHeight));
                        j = k;
                    }
                }

                g.setColor(Color.BLACK);
                g.drawLine(margin + graphLeftOffset,
                        margin,
                        margin + graphLeftOffset,
                        margin + graphHeight);
                g.drawLine(margin + graphLeftOffset,
                        margin + graphHeight,
                        margin + graphLeftOffset + graphWidth,
                        margin + graphHeight);

                Graphics2D g2d = (Graphics2D) g;
                SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("EEE MMM dd");
                int betweenLabels = (int) Math.round((double) dateAmount / 20);
                betweenLabels = betweenLabels == 0 ? 1 : betweenLabels;
                for (int i = 0; i < dateAmount - 1; i++)
                {
                    if (i % betweenLabels == 0)
                    {
                        g2d.rotate(Math.toRadians(90));
                        g2d.drawString(simpleDateFormatter.format(dates.get(i)),
                                margin + graphHeight + 20,
                                -(margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth) - 4));
                        g2d.rotate(Math.toRadians(-90));
                    }
                    g.drawLine(margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            margin + graphHeight,
                            margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            margin + graphHeight + 10);
                }

                int maxDecimalPlaces = 0;
                for (int i = 0; i < 10; i++)
                {
                    long value = Math.round(((finalHighest - finalLowest) / 10 * i + finalLowest) % 1 * Math.pow(10, 10));
                    String string = String.valueOf(value);
                    for (int j = string.length() - 1; j >= 0; j--)
                    {
                        if (string.charAt(j) != '0')
                        {
                            maxDecimalPlaces = Math.max(maxDecimalPlaces, j + 1);
                            break;
                        }
                    }
                }
                String format = "0" + (maxDecimalPlaces > 0 ? "." : "");
                for (int i = 0; i < maxDecimalPlaces && i < 2; i++)
                {
                    format += "0";
                }
                DecimalFormat decimalFormatter = new DecimalFormat(format);
                for (int i = 0; i < 10; i++)
                {
                    double value = (finalHighest - finalLowest) / 10 * i + finalLowest;
                    g.drawString(decimalFormatter.format(value),
                            margin + graphLeftOffset - 20 - g.getFontMetrics().stringWidth(decimalFormatter.format(value)),
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i) + 4);
                    g.drawLine(margin + graphLeftOffset - 10,
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i),
                            margin + graphLeftOffset,
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
                }

                for (int i = 0; i < dataAmount; i++)
                {
                    g.drawString(playerPairs[i].toString(),
                            margin + 10 + 10,
                            margin + graphHeight + 10 + 10 + 20 * i);
                    g.setColor(colours.get(i));
                    g.fillRect(margin,
                            margin + graphHeight + 10 + 20 * i,
                            10,
                            10);
                    g.setColor(Color.BLACK);
                    g.drawRect(margin,
                            margin + graphHeight + 10 + 20 * i,
                            10,
                            10);
                }

            }
        };
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        Insets insets = jFrame.getInsets();
        jFrame.setBounds(100,
                100,
                insets.left + margin + graphLeftOffset + graphWidth + margin + insets.right,
                insets.top + margin + graphHeight + graphBottomOffset + margin + insets.bottom);
        return jFrame;
    }
}
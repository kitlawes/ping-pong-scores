import javax.swing.*;
import java.awt.*;
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

    public void drawGraph(Statistic statistic, Intervals intervals, IntervalGames intervalGames, GameOutcome outcome,
                          Date start, Date end, Integer intervalDays, final PlayerPair[] playerPairs, boolean cumulative)
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
            for (Date date : dates)
            {
                Date dataPointStart;
                if (cumulative)
                {
                    dataPointStart = start;
                }
                else
                {
                    dataPointStart = date;
                }
                Double dataPoint = null;
                Player player = playerPair.getPlayer();
                Player opponent = playerPair.getOpponent();
                switch (statistic)
                {
                    case NUMBER_OF_GAMES:
                        dataPoint = new Double(analyzer.numberOfGames(outcome, dataPointStart, date, player, opponent));
                        break;
                    case PERCENTAGE_OF_GAMES:
                        dataPoint = analyzer.percentageOfGames(outcome, dataPointStart, date, player, opponent);
                        break;
                    case AVERAGE_PERCENTAGE_OF_GAMES:
                        dataPoint = analyzer.averagePercentageOfGames(outcome, dataPointStart, date, player);
                        break;
                    case AVERAGE_NUMBER_OF_GAMES:
                        dataPoint = analyzer.averageNumberOfGames(outcome, dataPointStart, date, intervalDays, player, opponent);
                        break;
                    case MOST_GAMES:
                        dataPoint = new Double(analyzer.mostGames(outcome, dataPointStart, date, intervalDays, player, opponent));
                        break;
                    case INTERVALS:
                        dataPoint = new Double(analyzer.numberOfIntervals(intervals, intervalGames, outcome, dataPointStart, date, intervalDays, player, opponent));
                        break;
                }
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
        if (statistic == Statistic.NUMBER_OF_GAMES
                || statistic == Statistic.INTERVALS
                || statistic == Statistic.MOST_GAMES)
        {
            highest = Math.ceil(highest / 10) * 10;
        }

        final Double finalLowest = lowest;
        final Double finalHighest = highest;
        final int margin = 10;
        final int graphWidth = 500;
        final int graphHeight = 250;
        final int graphLeftOffset = 250;
        final int graphBottomOffset = 250;
        JFrame jFrame = new JFrame()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);

                Insets insets = getInsets();
                int topInset = insets.top;
                int leftInset = insets.left;
                int dataAmount = playerPairs.length;

                g.setColor(Color.WHITE);
                g.fillRect(leftInset + margin + graphLeftOffset, topInset + margin, graphWidth, graphHeight);

                List<Color> colours = new ArrayList<>();
                for (int i = 0; i < dataAmount; i++)
                {
                    colours.add(new Color((float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / dataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
//                    colours.add(Color.GRAY);
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
                        g.drawLine(leftInset + margin + graphLeftOffset + (int) Math.round((double) j / (dateAmount - 1) * graphWidth),
                                topInset + margin + (int) Math.round((finalHighest - currentDataPoint) / (finalHighest - finalLowest) * graphHeight),
                                leftInset + margin + graphLeftOffset + (int) Math.round((double) k / (dateAmount - 1) * graphWidth),
                                topInset + margin + (int) Math.round((finalHighest - nextDataPoint) / (finalHighest - finalLowest) * graphHeight));
                        j = k;
                    }
                }

                g.setColor(Color.BLACK);
                g.drawLine(leftInset + margin + graphLeftOffset,
                        topInset + margin,
                        leftInset + margin + graphLeftOffset,
                        topInset + margin + graphHeight);
                g.drawLine(leftInset + margin + graphLeftOffset,
                        topInset + margin + graphHeight,
                        leftInset + margin + graphLeftOffset + graphWidth,
                        topInset + margin + graphHeight);

                Graphics2D g2d = (Graphics2D) g;
                SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("EEE MMM dd");
                for (int i = 0; i < dateAmount - 1; i++)
                {
                    g2d.rotate(Math.toRadians(90));
                    if (i % (dateAmount / 20) == 0)
                    {
                        g2d.drawString(simpleDateFormatter.format(dates.get(i)),
                                topInset + margin + graphHeight + 20,
                                -(leftInset + margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth)));
                    }
                    g2d.rotate(Math.toRadians(-90));
                    g.drawLine(leftInset + margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            topInset + margin + graphHeight,
                            leftInset + margin + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            topInset + margin + graphHeight + 10);
                }

                int maxDecimalPlaces = 0;
                for (int i = 0; i < 10; i++)
                {
                    double value = Math.round(((finalHighest - finalLowest) / 10 * i + finalLowest) * Math.pow(10, 10)) / Math.pow(10, 10);
                    for (int j = 1; j <= 10; j++)
                    {
                        if (value * Math.pow(10, j) % 1 > 0)
                        {
                            maxDecimalPlaces = Math.max(maxDecimalPlaces, j);
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
                            leftInset + margin + graphLeftOffset - 20 - g.getFontMetrics().stringWidth(decimalFormatter.format(value)),
                            topInset + margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
                    g.drawLine(leftInset + margin + graphLeftOffset - 10,
                            topInset + margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i),
                            leftInset + margin + graphLeftOffset,
                            topInset + margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
                }

                for (int i = 0; i < dataAmount; i++)
                {
                    g.drawString(playerPairs[i].toString(),
                            leftInset + margin + 10 + 10,
                            topInset + margin + graphHeight + 10 + 10 + 20 * i);
                    g.setColor(colours.get(i));
                    g.fillRect(leftInset + margin,
                            topInset + margin + graphHeight + 10 + 20 * i,
                            10,
                            10);
                    g.setColor(Color.BLACK);
                    g.drawRect(leftInset + margin,
                            topInset + margin + graphHeight + 10 + 20 * i,
                            10,
                            10);
                }

            }
        };
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        Insets insets = jFrame.getInsets();
        jFrame.setBounds(100,
                100,
                insets.left + margin + graphLeftOffset + graphWidth + margin + insets.right,
                insets.top + margin + graphHeight + graphBottomOffset + margin + insets.bottom);
    }
}
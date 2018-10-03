import javax.swing.*;
import java.awt.*;
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
                          Integer intervalDays, Date start, Date end, final PlayerPair[] playerPairs, boolean cumulative)
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

        final Double finalLowest = lowest;
        final Double finalHighest = highest;
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
                        g.drawLine(leftInset + graphLeftOffset + (int) Math.round((double) j / (dateAmount - 1) * graphWidth),
                                topInset + (int) Math.round((finalHighest - currentDataPoint) / (finalHighest - finalLowest) * graphHeight),
                                leftInset + graphLeftOffset + (int) Math.round((double) k / (dateAmount - 1) * graphWidth),
                                topInset + (int) Math.round((finalHighest - nextDataPoint) / (finalHighest - finalLowest) * graphHeight));
                        j = k;
                    }
                }

                g.setColor(Color.BLACK);
                g.drawLine(leftInset + graphLeftOffset, topInset, leftInset + graphLeftOffset, topInset + graphHeight);
                g.drawLine(leftInset + graphLeftOffset, topInset + graphHeight, leftInset + graphLeftOffset + graphWidth, topInset + graphHeight);
                Graphics2D g2d = (Graphics2D) g;

                for (int i = 0; i < dateAmount; i++)
                {
                    g2d.rotate(Math.toRadians(90));
                    if (i % (dateAmount / 20) == 0)
                    {
                        g2d.drawString(dates.get(i).toString(),
                                topInset + graphHeight + 20,
                                -(leftInset + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth)));
                    }
                    g2d.rotate(Math.toRadians(-90));
                    g.drawLine(leftInset + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            topInset + graphHeight,
                            leftInset + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                            topInset + graphHeight + 10);
                }

                for (double i = finalLowest; i < (finalHighest - finalLowest); i += (finalHighest - finalLowest) / 10)
                {
                    g.drawString(String.valueOf(i),
                            leftInset + graphLeftOffset - 20 - g.getFontMetrics().stringWidth(String.valueOf(i)),
                            topInset + graphHeight - (int) Math.round(i / (finalHighest - finalLowest) * graphHeight));
                    g.drawLine(leftInset + graphLeftOffset - 10,
                            topInset + graphHeight - (int) Math.round(i / (finalHighest - finalLowest) * graphHeight),
                            leftInset + graphLeftOffset,
                            topInset + graphHeight - (int) Math.round(i / (finalHighest - finalLowest) * graphHeight));
                }

                for (int i = 0; i < dataAmount; i++)
                {
                    g.drawString(playerPairs[i].toString(),
                            leftInset + 10 + 10 + 10,
                            topInset + graphHeight + 10 + 10 + 20 * i);
                    g.setColor(colours.get(i));
                    g.fillRect(leftInset + 10,
                            topInset + graphHeight + 10 + 20 * i,
                            10,
                            10);
                    g.setColor(Color.BLACK);
                    g.drawRect(leftInset + 10,
                            topInset + graphHeight + 10 + 20 * i,
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
                insets.left + graphLeftOffset + graphWidth + insets.right,
                insets.top + graphHeight + graphBottomOffset + insets.bottom);
    }
}
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

    public void drawGraph(Statistic statistic, boolean cumulative, Intervals intervals, IntervalGames intervalGames,
                          GameOutcome outcome, Integer intervalDays, final Player[] players, final Player[] opponents)
    {
        Date earliestDate = parser.getEarliestDate();
        Date latestDate = parser.getLatestDate();
        final List<Date> dates = parser.getDatesInRange(earliestDate, latestDate);
        Collections.sort(dates);
        List<String> legendKeys = new ArrayList<>();
        Double lowest = null;
        Double highest = null;

        final Map<Player, Map<Player, Map<Date, Double>>> data = new HashMap<>();
        for (Player player : players)
        {
            Map<Player, Map<Date, Double>> playerData = new HashMap<>();
            data.put(player, playerData);
            for (Player opponent : opponents)
            {
                if (player == opponent && player != Player.ANY)
                {
                    continue;
                }
                HashMap<Date, Double> opponentData = new HashMap<>();
                playerData.put(opponent, opponentData);
                legendKeys.add(player + "-" + opponent);
                for (Date date : dates)
                {
                    Date start = null;
                    if (cumulative)
                    {
                        start = earliestDate;
                    }
                    else
                    {
                        start = date;
                    }
                    Double dataPoint = null;
                    switch (statistic)
                    {
                        case NUMBER_OF_GAMES:
                            dataPoint = new Double(analyzer.numberOfGames(outcome, start, date, player, opponent));
                            break;
                        case PERCENTAGE_OF_GAMES:
                            dataPoint = analyzer.percentageOfGames(outcome, start, date, player, opponent);
                            break;
                        case AVERAGE_PERCENTAGE_OF_GAMES:
                            dataPoint = analyzer.averagePercentageOfGames(outcome, start, date, player);
                            break;
                        case MOST_GAMES:
                            dataPoint = new Double(analyzer.mostGames(outcome, start, date, intervalDays, player, opponent));
                            break;
                        case INTERVALS:
                            dataPoint = new Double(analyzer.numberOfIntervals(intervals, intervalGames, outcome, start, date, intervalDays, player, opponent));
                            break;
                    }
                    opponentData.put(date, dataPoint);
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
        }

        final int finalDataAmount = legendKeys.size();
        final List<String> finalLegendKeys = legendKeys;
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

                List<Color> colours = new ArrayList<>();
                for (int i = 0; i < finalDataAmount; i++)
                {
                    colours.add(new Color((float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
                }

                int dateAmount = dates.size();
                int dataIndex = -1;
                for (Player player : players)
                {
                    Map<Player, Map<Date, Double>> playerData = data.get(player);
                    for (Player opponent : opponents)
                    {
                        if (!playerData.containsKey(opponent))
                        {
                            continue;
                        }
                        Map<Date, Double> opponentData = playerData.get(opponent);
                        dataIndex++;
                        g.setColor(colours.get(dataIndex));
                        int i = -1;
                        Double nextDataPoint = null;
                        while (nextDataPoint == null)
                        {
                            i++;
                            nextDataPoint = opponentData.get(dates.get(i));
                        }
                        DATA_POINT_LOOP:
                        while (nextDataPoint != null)
                        {
                            Double currentDataPoint = nextDataPoint;
                            nextDataPoint = null;
                            int j = i;
                            while (nextDataPoint == null)
                            {
                                j++;
                                if (j == dates.size())
                                {
                                    break DATA_POINT_LOOP;
                                }
                                nextDataPoint = opponentData.get(dates.get(j));
                            }
                            g.drawLine(leftInset + graphLeftOffset + (int) Math.round((double) i / (dateAmount - 1) * graphWidth),
                                    topInset + (int) Math.round((finalHighest - currentDataPoint) / (finalHighest - finalLowest) * graphHeight),
                                    leftInset + graphLeftOffset + (int) Math.round((double) j / (dateAmount - 1) * graphWidth),
                                    topInset + (int) Math.round((finalHighest - nextDataPoint) / (finalHighest - finalLowest) * graphHeight));
                            i = j;
                        }
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

                for (int i = 0; i < finalDataAmount; i++)
                {
                    g.drawString(finalLegendKeys.get(i),
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
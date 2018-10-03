import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BarChart
{
    private ScoresAnalyzer analyzer;

    public BarChart(ScoresAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    public void drawGraph(Statistic statistic, Intervals intervals, IntervalGames intervalGames, Integer numberOfGames,
                          GameOutcome outcome, Date start, Date end, Integer intervalDays, final PlayerPair[] playerPairs)
    {
        final List<Map.Entry<PlayerPair, Object>> orderedPlayerPairs = analyzer.orderedPlayersOrPlayerPairs(Arrays.asList(playerPairs), statistic, intervals, intervalGames, numberOfGames, outcome, start, end, intervalDays);
        final List<String> legendKeys = new ArrayList<>();
        Double lowest = null;
        Double highest = null;
        for (Map.Entry<PlayerPair, Object> orderedPlayerPair : orderedPlayerPairs)
        {
            legendKeys.add(orderedPlayerPair.getKey().getPlayer() + "-" + orderedPlayerPair.getKey().getOpponent());
            Object dataPoint = orderedPlayerPair.getValue();
            if (dataPoint instanceof Integer)
            {
                if (lowest == null)
                {
                    lowest = new Integer(0).doubleValue();
                }
                if (highest == null)
                {
                    highest = ((Integer) dataPoint).doubleValue();
                }
                highest = Math.max(highest, ((Integer) dataPoint).doubleValue());
            }
            if (dataPoint instanceof Double)
            {
                if (lowest == null)
                {
                    lowest = new Double(0);
                }
                if (highest == null)
                {
                    highest = (Double) dataPoint;
                }
                highest = Math.max(highest, (Double) dataPoint);
            }
            if (dataPoint instanceof Date)
            {
                if (lowest == null)
                {
                    lowest = new Double(start.getTime());
                }
                if (highest == null)
                {
                    highest = new Double(((Date) dataPoint).getTime());
                }
                highest = Math.max(highest, new Double(((Date) dataPoint).getTime()));
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

                for (int i = 0; i < finalDataAmount; i++)
                {
                    Object value = orderedPlayerPairs.get(i).getValue();
                    Double dataPoint = null;
                    if (value instanceof Integer)
                    {
                        dataPoint = ((Integer) value).doubleValue();
                    }
                    if (value instanceof Double)
                    {
                        dataPoint = (Double) value;
                    }
                    if (value instanceof Date)
                    {
                        dataPoint = new Double(((Date) value).getTime());
                    }
                    g.setColor(colours.get(i));
//                    g.fillRect(leftInset + graphLeftOffset + (int) Math.round((i * 1.5 + 0.5) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
//                            topInset + graphHeight - (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight),
//                            (int) Math.round(graphWidth / (finalDataAmount * 1.5 + 0.5)),
//                            (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight));
                    g.setColor(Color.BLACK);
                    g.drawRect(leftInset + graphLeftOffset + (int) Math.round((i * 1.5 + 0.5) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            topInset + graphHeight - (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight),
                            (int) Math.round(graphWidth / (finalDataAmount * 1.5 + 0.5)),
                            (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight));
                }

                g.setColor(Color.BLACK);
                g.drawLine(leftInset + graphLeftOffset, topInset, leftInset + graphLeftOffset, topInset + graphHeight);
                g.drawLine(leftInset + graphLeftOffset, topInset + graphHeight, leftInset + graphLeftOffset + graphWidth, topInset + graphHeight);

                Graphics2D g2d = (Graphics2D) g;
                for (int i = 0; i < finalDataAmount; i++)
                {
                    g2d.rotate(Math.toRadians(90));
                    g2d.drawString(legendKeys.get(i).toString(),
                            topInset + graphHeight + 20,
                            -(leftInset + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth)));
                    g2d.rotate(Math.toRadians(-90));
                    g.drawLine(leftInset + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            topInset + graphHeight,
                            leftInset + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            topInset + graphHeight + 10);
                }

                for (int i = 0; i < 10; i++)
                {
                    String label;
                    double value = (finalHighest - finalLowest) / 10 * i + finalLowest;
                    if (orderedPlayerPairs.get(0).getValue() instanceof Date)
                    {
                        label = String.valueOf(new Date(Math.round(value)));
                    }
                    else
                    {
                        label = String.valueOf(value);
                    }
                    g.drawString(label,
                            leftInset + graphLeftOffset - 20 - g.getFontMetrics().stringWidth(label),
                            topInset + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
                    g.drawLine(leftInset + graphLeftOffset - 10,
                            topInset + graphHeight - (int) Math.round((double) graphHeight / 10 * i),
                            leftInset + graphLeftOffset,
                            topInset + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
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
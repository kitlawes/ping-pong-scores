import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class BarChart
{
    private ScoresAnalyzer analyzer;

    public BarChart(ScoresAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    public JFrame drawGraph(Statistic statistic, Intervals intervals, IntervalGames intervalGames, Integer numberOfGames,
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
        if ((statistic == Statistic.NUMBER_OF_GAMES
                || statistic == Statistic.INTERVALS
                || statistic == Statistic.MOST_GAMES)
                && highest - lowest > 5)
        {
            highest = Math.ceil(highest / 10) * 10;
        }
        if (statistic == Statistic.PERCENTAGE_OF_GAMES
                || statistic == Statistic.AVERAGE_PERCENTAGE_OF_GAMES)
        {
            highest = new Double(100);
        }

        final int finalDataAmount = legendKeys.size();
        final List<String> finalLegendKeys = legendKeys;
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

                g.setColor(Color.WHITE);
                g.fillRect(margin + graphLeftOffset, margin, graphWidth, graphHeight);

                List<Color> colours = new ArrayList<>();
                for (int i = 0; i < finalDataAmount; i++)
                {
                    colours.add(new Color((float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                            (float) Math.max(0, Math.min(1, Math.abs(((i + 0.5) / finalDataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
//                    colours.add(Color.GRAY);
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
                    g.fillRect(margin + graphLeftOffset + (int) Math.round((i * 1.5 + 0.5) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            margin + graphHeight - (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight),
                            (int) Math.round(graphWidth / (finalDataAmount * 1.5 + 0.5)),
                            (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight));
                    g.setColor(Color.BLACK);
                    g.drawRect(margin + graphLeftOffset + (int) Math.round((i * 1.5 + 0.5) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            margin + graphHeight - (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight),
                            (int) Math.round(graphWidth / (finalDataAmount * 1.5 + 0.5)),
                            (int) Math.round((dataPoint - finalLowest) / (finalHighest - finalLowest) * graphHeight));
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
                for (int i = 0; i < finalDataAmount; i++)
                {
                    g2d.rotate(Math.toRadians(90));
                    g2d.drawString(legendKeys.get(i).toString(),
                            margin + graphHeight + 20,
                            -(margin + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth) - 4));
                    g2d.rotate(Math.toRadians(-90));
                    g.drawLine(margin + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
                            margin + graphHeight,
                            margin + graphLeftOffset + (int) Math.round((i * 1.5 + 1) / (finalDataAmount * 1.5 + 0.5) * graphWidth),
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
                SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("EEE MMM dd");
                for (int i = 0; i < 10; i++)
                {
                    String label;
                    double value = (finalHighest - finalLowest) / 10 * i + finalLowest;
                    if (orderedPlayerPairs.get(0).getValue() instanceof Date)
                    {
                        label = simpleDateFormatter.format(new Date(Math.round(value)));
                    }
                    else
                    {
                        label = decimalFormatter.format(value);
                    }
                    g.drawString(label,
                            margin + graphLeftOffset - 20 - g.getFontMetrics().stringWidth(label),
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i) + 4);
                    g.drawLine(margin + graphLeftOffset - 10,
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i),
                            margin + graphLeftOffset,
                            margin + graphHeight - (int) Math.round((double) graphHeight / 10 * i));
                }

                for (int i = 0; i < finalDataAmount; i++)
                {
                    g.drawString(finalLegendKeys.get(i),
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
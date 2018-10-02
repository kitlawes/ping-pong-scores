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

    public void drawGraph(Graph graph, boolean cumulative, GameOutcome outcome, final Player[] players, final Player[] opponents)
    {
        Date earliestDate = parser.getEarliestDate();
        Date latestDate = parser.getLatestDate();
        final List<Date> dates = parser.getDatesInRange(earliestDate, latestDate);
        Collections.sort(dates);
        int dataAmount = 0;
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
                dataAmount++;
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
                    switch (graph)
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

        final int finalDataAmount = dataAmount;
        final Double finalLowest = lowest;
        final Double finalHighest = highest;
        final int width = 500;
        final int height = 200;
        final int topInset = 31;
        final int bottomInset = 9;
        final int leftInset = 8;
        final int rightInset = 9;
        JFrame jFrame = new JFrame()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);
//                g.drawLine(leftInset, topInset, width - rightInset, topInset);
//                g.drawLine(width - rightInset, topInset, width - rightInset, height - bottomInset);
//                g.drawLine(width - rightInset, height - bottomInset, leftInset, height - bottomInset);
//                g.drawLine(leftInset, height - bottomInset, leftInset, topInset);
                int dateAmount = dates.size();
                int dataIndex = -1;
                for (Map<Player, Map<Date, Double>> playerData : data.values())
                {
                    for (Map<Date, Double> opponentData : playerData.values())
                    {
                        dataIndex++;
                        g.setColor(new Color((float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                                (float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                                (float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
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
                            g.drawLine(leftInset + (int) Math.round((double) i / (dateAmount - 1) * (width - leftInset - rightInset)),
                                    topInset + (int) Math.round((finalHighest - currentDataPoint) / (finalHighest - finalLowest) * (height - topInset - bottomInset)),
                                    leftInset + (int) Math.round((double) j / (dateAmount - 1) * (width - leftInset - rightInset)),
                                    topInset + (int) Math.round((finalHighest - nextDataPoint) / (finalHighest - finalLowest) * (height - topInset - bottomInset)));
                            i = j;
                        }
                    }
                }
            }
        };
        jFrame.setBounds(100, 100, width, height);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
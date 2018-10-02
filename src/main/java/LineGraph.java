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

    public void drawGraph(Graph graph, GameOutcome outcome, final Player[] players, final Player[] opponents)
    {
        Date earliestDate = parser.getEarliestDate();
        Date latestDate = parser.getLatestDate();
        final List<Date> dates = parser.getDatesInRange(earliestDate, latestDate);
        Collections.sort(dates);
        int dataAmount = 0;
        Integer lowest = null;
        Integer highest = null;

        final Map<Player, Map<Player, Map<Date, Integer>>> data = new HashMap<>();
        for (Player player : players)
        {
            Map<Player, Map<Date, Integer>> playerData = new HashMap<>();
            data.put(player, playerData);
            for (Player opponent : opponents)
            {
                if (player == opponent && player != Player.ANY)
                {
                    continue;
                }
                HashMap<Date, Integer> opponentData = new HashMap<>();
                playerData.put(opponent, opponentData);
                dataAmount++;
                for (Date date : dates)
                {
                    Date start = null;
                    switch (graph)
                    {
                        case NUMBER_OF_GAMES_FOR_EACH_DAY:
                            start = date;
                            break;
                        case NUMBER_OF_GAMES:
                            start = earliestDate;
                            break;
                    }
                    Integer games = analyzer.numberOfGames(
                            outcome,
                            start,
                            date,
                            player,
                            opponent);
                    opponentData.put(date, games);
                    if (lowest == null)
                    {
                        lowest = games;
                    }
                    lowest = Math.min(lowest, games);
                    if (highest == null)
                    {
                        highest = games;
                    }
                    highest = Math.max(highest, games);
                }
            }
        }

        final int finalDataAmount = dataAmount;
        final Integer finalLowest = lowest;
        final Integer finalHighest = highest;
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
                for (Map<Player, Map<Date, Integer>> playerData : data.values())
                {
                    for (Map<Date, Integer> opponentData : playerData.values())
                    {
                        dataIndex++;
                        g.setColor(new Color((float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 0 / 3) % 1 * 3 - 1.5) - 0.25)),
                                (float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 1 / 3) % 1 * 3 - 1.5) - 0.25)),
                                (float) Math.max(0, Math.min(1, Math.abs(((dataIndex + 0.5) / finalDataAmount + (double) 2 / 3) % 1 * 3 - 1.5) - 0.25))));
                        for (int i = 0; i + 1 < dateAmount; i++)
                        {
                            Integer currentGames = opponentData.get(dates.get(i));
                            Integer nextGames = opponentData.get(dates.get(i + 1));
                            g.drawLine(leftInset + (int) Math.round((double) i / (dateAmount - 1) * (width - leftInset - rightInset)),
                                    topInset + (int) Math.round((double) (finalHighest - currentGames) / (finalHighest - finalLowest) * (height - topInset - bottomInset)),
                                    leftInset + (int) Math.round((double) (i + 1) / (dateAmount - 1) * (width - leftInset - rightInset)),
                                    topInset + (int) Math.round((double) (finalHighest - nextGames) / (finalHighest - finalLowest) * (height - topInset - bottomInset)));
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
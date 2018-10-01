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

    public void drawGraph(Graph graph)
    {
        // graph of number of games played
        Date earliestDate = parser.getEarliestDate();
        Date latestDate = parser.getLatestDate();
        final List<Date> dates = parser.getDatesInRange(earliestDate, latestDate);
        Collections.sort(dates);
        Integer lowest = null;
        Integer highest = null;

        final Map<Date, Integer> data = new HashMap<>();
        for (Date date : dates)
        {
            Date start = null;
            switch (graph)
            {
                case NUMBER_OF_GAMES_PLAYED_FOR_EACH_DAY:
                    start = date;
                    break;
                case NUMBER_OF_GAMES_PLAYED:
                    start = earliestDate;
                    break;
            }
            Integer games = analyzer.numberOfGames(
                    GameOutcome.ANY,
                    start,
                    date,
                    Player.ANY,
                    Player.ANY);
            data.put(date, games);
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
                int size = dates.size();
                for (int i = 0; i + 1 < size; i++)
                {
//                    g.drawLine(leftInset, topInset, width - rightInset, topInset);
//                    g.drawLine(width - rightInset, topInset, width - rightInset, height - bottomInset);
//                    g.drawLine(width - rightInset, height - bottomInset, leftInset, height - bottomInset);
//                    g.drawLine(leftInset, height - bottomInset, leftInset, topInset);
                    Integer currentGames = data.get(dates.get(i));
                    Integer nextGames = data.get(dates.get(i + 1));
                    g.drawLine(leftInset + (int) Math.round((double) i / (size - 1) * (width - leftInset - rightInset)),
                            topInset + (int) Math.round((double) (finalHighest - currentGames) / (finalHighest - finalLowest) * (height - topInset - bottomInset)),
                            leftInset + (int) Math.round((double) (i + 1) / (size - 1) * (width - leftInset - rightInset)),
                            topInset + (int) Math.round((double) (finalHighest - nextGames) / (finalHighest - finalLowest) * (height - topInset - bottomInset)));
                }
            }
        };
        jFrame.setBounds(100, 100, width, height);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
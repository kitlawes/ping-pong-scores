import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PerformanceGraph
{
    private ValueParser parser;
    private ScoresAnalyzer analyzer;

    public PerformanceGraph(ValueParser parser, ScoresAnalyzer analyzer)
    {
        this.parser = parser;
        this.analyzer = analyzer;
    }

    public void drawPerformanceGraph()
    {
        final Date earliestDate = parser.getEarliestDate();
        final Date latestDate = parser.getLatestDate();
        List<Date> dates = parser.getDatesInRange(earliestDate, latestDate);
        Collections.sort(dates);

        final ArrayList<Player> players = new ArrayList<>(Arrays.asList(Player.values()));
        players.remove(Player.ANY);
        players.remove(Player.NONE);

        final List<Map<Player, Double>> percentages = new ArrayList<>();
        double lowestPercentage = 100.0;
        double highestPercentage = 0.0;

        int intervalDays = 14;

        for (int i = 0; i + intervalDays < dates.size(); i++)
        {
            Map<Player, Double> map = new HashMap<>();
            percentages.add(map);
            Date startDate = dates.get(i);
            Date endDate = dates.get(i + intervalDays);
            for (Player player : players)
            {
                double percentage = 0;
                for (Player opponent : players)
                {
                    if (player != opponent)
                    {
                        percentage += analyzer.percentageOfGames(
                                GameOutcome.WIN,
                                startDate,
                                endDate,
                                player,
                                opponent);
                    }
                }
                percentage = percentage / (players.size() - 1);
                lowestPercentage = Math.min(lowestPercentage, percentage);
                highestPercentage = Math.max(highestPercentage, percentage);
                map.put(player, percentage);
            }
        }

        final double lowest = lowestPercentage;
        final double highest = highestPercentage;
        final int width = 500;
        final int height = 200;
        final int topInset = 30;
        final int bottomInset = 9;
        final int leftInset = 8;
        final int rightInset = 9;
        JFrame jFrame = new JFrame()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);
                int size = percentages.size();
                for (int i = 0; i + 1 < size; i++)
                {
                    for (Player player : players)
                    {
                        switch (player)
                        {
                            case ANTONIO:
                                g.setColor(new Color(0f / 2f, 1f / 2f, 2f / 2f));
                                break;
                            case KIT:
                                g.setColor(new Color(0f / 2f, 2f / 2f, 1f / 2f));
                                break;
                            case HUNOR:
                                g.setColor(new Color(2f / 2f, 0f / 2f, 1f / 2f));
                                break;
                            case JIPESH:
                                g.setColor(new Color(1f / 2f, 0f / 2f, 2f / 2f));
                                break;
                        }
                        g.drawLine(leftInset + (int) Math.round((double) i / (size - 1) * (width - leftInset - rightInset)),
                                topInset + (int) Math.round((highest - percentages.get(i).get(player)) / (highest - lowest) * (height - topInset - bottomInset)),
                                leftInset + (int) Math.round((double) (i + 1) / (size - 1) * (width - leftInset - rightInset)),
                                topInset + (int) Math.round((highest - percentages.get(i + 1).get(player)) / (highest - lowest) * (height - topInset - bottomInset)));
                    }
                }
            }
        };
        jFrame.setBounds(100, 100, width, height);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
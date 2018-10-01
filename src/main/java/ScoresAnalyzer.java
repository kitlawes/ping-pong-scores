import java.util.*;

public class ScoresAnalyzer
{
    private ValueParser parser;

    public ScoresAnalyzer(ValueParser parser)
    {
        this.parser = parser;
    }

    public int numberOfGames(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        List<Score> scores = parser.getScoresForDateRange(
                parser.getScores(), start, end);
        if (player != Player.ANY)
        {
            scores = parser.getScoresForPlayer(
                    scores, player);
        }
        if (opponent != Player.ANY)
        {
            scores = parser.getScoresForOpponent(
                    scores, opponent);
        }
        int games = 0;
        for (Score score : scores)
        {
            if (outcome == GameOutcome.WIN || outcome == GameOutcome.ANY)
            {
                games += score.getPlayerWins();
            }
            if (outcome == GameOutcome.LOSE || outcome == GameOutcome.ANY)
            {
                games += score.getOpponentWins();
            }
        }
        if (player == Player.ANY && opponent == Player.ANY)
        {
            return games / 2;
        }
        return games;
    }

    public Player playerWithOutcome(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        int gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
        int gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
        switch (outcome)
        {
            case WIN:
                return gamesWon > gamesLost ? player : gamesWon < gamesLost ? opponent : Player.NONE;
            case LOSE:
                return gamesWon < gamesLost ? player : gamesWon > gamesLost ? opponent : Player.NONE;
        }
        return Player.ANY;
    }

    public Double percentageOfGames(GameOutcome outcome, Date start, Date end, Player player, Player opponent)
    {
        int gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
        int gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
        int gamesPlayed = gamesWon + gamesLost;
        if (gamesPlayed == 0)
        {
            return null;
        }
        switch (outcome)
        {
            case WIN:
                return (double) gamesWon / gamesPlayed * 100;
            case LOSE:
                return (double) gamesLost / gamesPlayed * 100;
        }
        return (double) 100;
    }

    public Double averagePercentageOfGames(GameOutcome outcome, Date start, Date end, Player player)
    {
        final ArrayList<Player> players = new ArrayList<>(Arrays.asList(Player.values()));
        players.remove(Player.ANY);
        players.remove(Player.NONE);
        players.remove(Player.ALL);
        Double percentage = new Double(0);
        int numberOfPercentages = 0;
        for (Player opponent : players)
        {
            if (player != opponent)
            {
                Double percentageOfGames = percentageOfGames(
                        outcome,
                        start,
                        end,
                        player,
                        opponent);
                if (percentageOfGames != null)
                {
                    percentage += percentageOfGames;
                    numberOfPercentages++;
                }
            }
        }
        return percentage / numberOfPercentages;
    }

    public double averageNumberOfGames(GameOutcome outcome, Date start, Date end, int intervalDays, Player player,
                                       Player opponent)
    {
        int gamesWon = 0;
        int gamesLost = 0;
        int gamesPlayed;
        if (player == Player.ANY && opponent == Player.ANY)
        {
            gamesPlayed = numberOfGames(GameOutcome.ANY, start, end, player, opponent);
        } else
        {
            gamesWon = numberOfGames(GameOutcome.WIN, start, end, player, opponent);
            gamesLost = numberOfGames(GameOutcome.LOSE, start, end, player, opponent);
            gamesPlayed = gamesWon + gamesLost;
        }
        int numberOfDatesInRange = parser.getNumberOfDatesInRange(start, end);
        int divisor = numberOfDatesInRange / intervalDays;
        switch (outcome)
        {
            case WIN:
                return (double) gamesWon / divisor;
            case LOSE:
                return (double) gamesLost / divisor;
            case ANY:
                return (double) gamesPlayed / divisor;
        }
        return 0;
    }

    public int mostGames(GameOutcome outcome, Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int mostGames = 0;
        int games = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            games += numberOfGames(outcome, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                mostGames = Math.max(mostGames, games);
                games = 0;
            }
        }
        return mostGames;
    }

    public int numberOfIntervals(Intervals intervals, IntervalGames intervalGames, GameOutcome outcome,
                                 Date start, Date end, int intervalDays, Player player, Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int numberOfIntervals = 0;
        int mostConsecutiveIntervals = 0;
        int gamesPlayed = 0;
        int gamesWon = 0;
        int gamesLost = 0;
        for (int i = 0; i < dates.size(); i++)
        {
            Date date = dates.get(i);
            gamesPlayed += numberOfGames(GameOutcome.ANY, date, date, player, opponent);
            gamesWon += numberOfGames(GameOutcome.WIN, date, date, player, opponent);
            gamesLost += numberOfGames(GameOutcome.LOSE, date, date, player, opponent);
            if ((i + 1) % intervalDays == 0)
            {
                if (intervalGames == IntervalGames.AT_LEAST_ONE
                        && (outcome == GameOutcome.ANY && gamesPlayed >= 1
                        || outcome == GameOutcome.WIN && gamesWon >= 1
                        || outcome == GameOutcome.LOSE && gamesLost >= 1)
                        || intervalGames == IntervalGames.WITHOUT_ANY
                        && (outcome == GameOutcome.ANY && gamesPlayed == 0
                        || outcome == GameOutcome.WIN && gamesWon == 0
                        || outcome == GameOutcome.LOSE && gamesLost == 0)
                        || intervalGames == IntervalGames.MORE_FREQUENT
                        && (outcome == GameOutcome.WIN && gamesWon > gamesLost
                        || outcome == GameOutcome.LOSE && gamesLost > gamesWon)
                        || intervalGames == IntervalGames.EQUALLY_FREQUENT
                        && gamesWon == gamesLost
                        || intervalGames == IntervalGames.LESS_FREQUENT
                        && (outcome == GameOutcome.WIN && gamesWon < gamesLost
                        || outcome == GameOutcome.LOSE && gamesLost < gamesWon))
                {
                    numberOfIntervals++;
                    mostConsecutiveIntervals = Math.max(mostConsecutiveIntervals, numberOfIntervals);
                } else if (intervals == Intervals.MOST_CONSECUTIVE)
                {
                    numberOfIntervals = 0;
                }
                gamesPlayed = 0;
                gamesWon = 0;
                gamesLost = 0;
            }
        }
        switch (intervals)
        {
            case ANY:
                return numberOfIntervals;
            case MOST_CONSECUTIVE:
                return mostConsecutiveIntervals;
        }
        return 0;
    }

    public Date dateOfNumberOfGames(int numberOfGames, GameOutcome outcome, Date start, Date end, Player player,
                                    Player opponent)
    {
        List<Date> dates = parser.getDatesInRange(start, end);
        Collections.sort(dates);
        int games = 0;
        for (Date date : dates)
        {
            games += numberOfGames(outcome, date, date, player, opponent);
            if (games >= numberOfGames)
            {
                return date;
            }
        }
        return null;
    }

    public List<Map.Entry<Player, Object>> orderedPlayers(OrderCriterion orderCriterion, Intervals intervals,
                                                          IntervalGames intervalGames, Integer numberOfGames,
                                                          GameOutcome outcome, Date start, Date end,
                                                          Integer intervalDays)
    {
        final ArrayList<Player> players = new ArrayList<>(Arrays.asList(Player.values()));
        players.remove(Player.ANY);
        players.remove(Player.NONE);
        players.remove(Player.ALL);

        Map<Player, Object> orderedPlayers = new HashMap<>();
        for (Player player : players)
        {
            switch (orderCriterion)
            {
                case NUMBER_OF_GAMES:
                    orderedPlayers.put(player, numberOfGames(outcome, start, end, player, Player.ANY));
                    break;
                case PERCENTAGE_OF_GAMES:
                    orderedPlayers.put(player, percentageOfGames(outcome, start, end, player, Player.ANY));
                    break;
                case AVERAGE_PERCENTAGE_OF_GAMES:
                    orderedPlayers.put(player, averagePercentageOfGames(outcome, start, end, player));
                    break;
                case AVERAGE_NUMBER_OF_GAMES:
                    orderedPlayers.put(player, averageNumberOfGames(outcome, start, end, intervalDays, player, Player.ANY));
                    break;
                case MOST_GAMES:
                    orderedPlayers.put(player, mostGames(outcome, start, end, intervalDays, player, Player.ANY));
                    break;
                case INTERVALS:
                    orderedPlayers.put(player, numberOfIntervals(intervals, intervalGames, outcome, start, end, intervalDays, player, Player.ANY));
                    break;
                case DATE_OF_NUMBER_OF_GAMES:
                    Date date = dateOfNumberOfGames(numberOfGames, outcome, start, end, player, Player.ANY);
                    if (date != null)
                    {
                        orderedPlayers.put(player, date);
                    }
                    break;
            }
        }

        List<Map.Entry<Player, Object>> list = new ArrayList<>(orderedPlayers.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Player, Object>>()
        {
            public int compare(Map.Entry<Player, Object> o1, Map.Entry<Player, Object> o2)
            {
                if (o1.getValue() instanceof Integer)
                {
                    return ((Integer) o2.getValue()).compareTo(((Integer) o1.getValue()));
                }
                if (o1.getValue() instanceof Double)
                {
                    return ((Double) o2.getValue()).compareTo(((Double) o1.getValue()));
                }
                if (o1.getValue() instanceof Date)
                {
                    return ((Date) o1.getValue()).compareTo(((Date) o2.getValue()));
                }
                return 0;
            }
        });
        return list;
    }

    public List<Map.Entry<PlayerPair, Object>> orderedPairsOfPlayers(OrderCriterion orderCriterion, Intervals intervals,
                                                                     IntervalGames intervalGames, Integer numberOfGames,
                                                                     GameOutcome outcome, Date start, Date end,
                                                                     Integer intervalDays)
    {
        final ArrayList<Player> players = new ArrayList<>(Arrays.asList(Player.values()));
        players.remove(Player.ANY);
        players.remove(Player.NONE);
        players.remove(Player.ALL);
        final ArrayList<PlayerPair> playerPairs = new ArrayList<>();
        for (int i = 0; i < players.size(); i++)
        {
            Player player = players.get(i);
            for (int j = outcome == GameOutcome.ANY ? i + 1 : 0; j < players.size(); j++)
            {
                Player opponent = players.get(j);
                if (player != opponent)
                {
                    PlayerPair playerPair = new PlayerPair();
                    playerPair.setPlayer(player);
                    playerPair.setOpponent(opponent);
                    playerPairs.add(playerPair);
                }
            }
        }

        Map<PlayerPair, Object> orderedPlayerPairs = new HashMap<>();
        for (PlayerPair playerPair : playerPairs)
        {
            switch (orderCriterion)
            {
                case NUMBER_OF_GAMES:
                    orderedPlayerPairs.put(playerPair, numberOfGames(outcome, start, end, playerPair.getPlayer(), playerPair.getOpponent()));
                    break;
                case PERCENTAGE_OF_GAMES:
                    orderedPlayerPairs.put(playerPair, percentageOfGames(outcome, start, end, playerPair.getPlayer(), playerPair.getOpponent()));
                    break;
                case AVERAGE_NUMBER_OF_GAMES:
                    orderedPlayerPairs.put(playerPair, averageNumberOfGames(outcome, start, end, intervalDays, playerPair.getPlayer(), playerPair.getOpponent()));
                    break;
                case MOST_GAMES:
                    orderedPlayerPairs.put(playerPair, mostGames(outcome, start, end, intervalDays, playerPair.getPlayer(), playerPair.getOpponent()));
                    break;
                case INTERVALS:
                    orderedPlayerPairs.put(playerPair, numberOfIntervals(intervals, intervalGames, outcome, start, end, intervalDays, playerPair.getPlayer(), playerPair.getOpponent()));
                    break;
                case DATE_OF_NUMBER_OF_GAMES:
                    Date date = dateOfNumberOfGames(numberOfGames, outcome, start, end, playerPair.getPlayer(), playerPair.getOpponent());
                    if (date != null)
                    {
                        orderedPlayerPairs.put(playerPair, date);
                    }
                    break;
            }
        }

        List<Map.Entry<PlayerPair, Object>> list = new ArrayList<>(orderedPlayerPairs.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<PlayerPair, Object>>()
        {
            public int compare(Map.Entry<PlayerPair, Object> o1, Map.Entry<PlayerPair, Object> o2)
            {
                if (o1.getValue() instanceof Integer)
                {
                    return ((Integer) o2.getValue()).compareTo(((Integer) o1.getValue()));
                }
                if (o1.getValue() instanceof Double)
                {
                    return ((Double) o2.getValue()).compareTo(((Double) o1.getValue()));
                }
                if (o1.getValue() instanceof Date)
                {
                    return ((Date) o1.getValue()).compareTo(((Date) o2.getValue()));
                }
                return 0;
            }
        });
        return list;
    }
}
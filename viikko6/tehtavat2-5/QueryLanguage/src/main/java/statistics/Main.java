package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println();

        m = new And(
                new Not(new HasAtLeast(1, "goals")),
                new PlaysIn("NYR")
        );

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println();

        m = new And(
                new HasFewerThan(1, "goals"),
                new PlaysIn("NYR")
        );

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println();

        System.out.println(stats.matches(new All()).size());
    }
}

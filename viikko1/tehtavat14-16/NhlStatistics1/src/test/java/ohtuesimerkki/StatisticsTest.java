package ohtuesimerkki;


import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StatisticsTest {
    
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            // oma toteutus rajapinnan metodista 
            // poistaa verkkoyhteyden tarpeen testissä
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchFindsPlayer() {
        assertEquals("Semenko", stats.search("Se").getName());
    }
    
    @Test
    public void searchReturnsNullIfPlayerNotFound() {
        assertEquals(null, stats.search("Waldo"));
    }
    
    @Test
    public void teamFindsCorrectPlayers() {
        String player = readerStub.getPlayers().get(1).getName();
        String found = stats.team("PIT").get(0).getName();
        assertEquals(player, found);
    }
    
    @Test
    public void topScorersFindsCorrectPlayers() {
        String p1 = readerStub.getPlayers().get(4).getName();
        String p2 = readerStub.getPlayers().get(1).getName();
        String found1 = stats.topScorers(2).get(0).getName();
        String found2 = stats.topScorers(2).get(1).getName();
        assertTrue(p1.equals(found1) && p2.equals(found2));
    }
}

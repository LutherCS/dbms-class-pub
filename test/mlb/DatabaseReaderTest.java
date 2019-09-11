package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseReaderTest {
    
    public DatabaseReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class DatabaseReader.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        DatabaseReader instance = new DatabaseReader();
        instance.connect();
    }

    /**
     * Test of disconnect method, of class DatabaseReader.
     */
    @Test(expected = NullPointerException.class)
    public void testDisconnect() {
        System.out.println("disconnect");
        DatabaseReader instance = new DatabaseReader();
        instance.disconnect();
    }

    /**
     * Test of getDivisions method, of class DatabaseReader.
     */
    @Test
    public void testGetDivisions() {
        System.out.println("getDivisions");
        int expResult = 6;
        ArrayList<String> divisions = new ArrayList<>();
        DatabaseReader instance = new DatabaseReader();
        long start = System.nanoTime();
        instance.getDivisions(divisions);
        long end = System.nanoTime();
        assertTrue((end-start) / 1000000 < 50);
        assertEquals(expResult, divisions.size());
    }

    /**
     * Test of getTeams method, of class DatabaseReader.
     */
    @Test
    public void testGetTeams() {
        System.out.println("getTeams");
        String confDiv = "National | Central";
        int expResult = 5;
        ArrayList<String> teams = new ArrayList<>();
        DatabaseReader instance = new DatabaseReader();
        long start = System.nanoTime();
        instance.getTeams(confDiv, teams);
        long end = System.nanoTime();
        assertTrue((end-start) / 1000000 < 500);
        assertEquals(expResult, teams.size());
        
    }

    /**
     * Test of getTeamInfo method, of class DatabaseReader.
     */
    @Test
    public void testGetTeamInfo() {
        System.out.println("getTeamInfo");
        String teamName = "Chicago Cubs";
        DatabaseReader instance = new DatabaseReader();
        int expResult = 114;
        long start = System.nanoTime();
        Team result = instance.getTeamInfo(teamName);
        long end = System.nanoTime();
        assertTrue((end-start) / 1000000 < 50);
        assertEquals(expResult, result.getRoster().size());
    }
    
}

package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseReaderTest {
    
    public DatabaseReaderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("DatabaseReader");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    @Timeout(1)  // No test can exceed 1 sec
    public void setUp() {
    }
    
    @AfterEach
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
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        DatabaseReader instance = new DatabaseReader();
        assertThrows(NullPointerException.class, () -> instance.disconnect());
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
        instance.getDivisions(divisions);
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
        instance.getTeams(confDiv, teams);
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
        Team result = instance.getTeamInfo(teamName);
        assertEquals(expResult, result.getRoster().size());
    }
    
}

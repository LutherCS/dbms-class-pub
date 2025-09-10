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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseReaderTest {

    public DatabaseReaderTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Start testing class DatabaseReader implementation");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finish testing class Databasereader implementation");
    }

    @BeforeEach
    @Timeout(1) // No test can exceed 1 sec
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
        System.out.println("Testing method connect");
        DatabaseReader instance = new DatabaseReader();
        instance.connect();
    }

    /**
     * Test of disconnect method, of class DatabaseReader.
     */
    @Test
    public void testDisconnect() {
        System.out.println("Testing method disconnect");
        DatabaseReader instance = new DatabaseReader();
        assertThrows(NullPointerException.class, () -> instance.disconnect());
    }

    /**
     * Test of getDivisions method, of class DatabaseReader.
     */
    @Test
    public void testGetDivisions() {
        System.out.println("Testing method getDivisions");
        int expResult = 6;
        ArrayList<String> divisions = new ArrayList<>();
        DatabaseReader instance = new DatabaseReader();
        instance.getDivisions(divisions);
        assertEquals(expResult, divisions.size());
    }

    /**
     * Test of getTeams method, of class DatabaseReader.
     */
    @ParameterizedTest
    @ValueSource(strings = { "American | Central", "American | East", "American | West", "National | Central",
            "National | East", "National | West" })
    public void testGetTeams(String confDiv) {
        System.out.println("Testing method getTeams");
        int expResult = 5;
        DatabaseReader instance = new DatabaseReader();
        ArrayList<String> teams = new ArrayList<>();
        instance.getTeams(confDiv, teams);
        assertEquals(expResult, teams.size());
    }

    /**
     * Test of getTeamInfo method, of class DatabaseReader.
     */
    @ParameterizedTest
    @CsvSource({ "Arizona Diamondbacks, 64",
            "Athletics, 60",
            "Atlanta Braves, 73",
            "Baltimore Orioles, 57",
            "Boston Red Sox, 60",
            "Chicago Cubs, 61",
            "Chicago White Sox, 69",
            "Cincinnati Reds, 64",
            "Cleveland Guardians, 55",
            "Colorado Rockies, 55",
            "Detroit Tigers, 63",
            "Houston Astros, 67",
            "Kansas City Royals, 66",
            "Los Angeles Angels, 66",
            "Los Angeles Dodgers, 72",
            "Miami Marlins, 49",
            "Milwaukee Brewers, 64",
            "Minnesota Twins, 57",
            "New York Mets, 70",
            "New York Yankees, 61",
            "Philadelphia Phillies, 66",
            "Pittsburgh Pirates, 47",
            "San Diego Padres, 56",
            "San Francisco Giants, 57",
            "Seattle Mariners, 72",
            "St. Louis Cardinals, 49",
            "Tampa Bay Rays, 64",
            "Texas Rangers, 64",
            "Toronto Blue Jays, 61",
            "Washington Nationals, 56", })
    public void testGetTeamInfo(String teamName, int expResult) {
        System.out.println("Testing method getTeamInfo");
        DatabaseReader instance = new DatabaseReader();
        Team result = instance.getTeamInfo(teamName);
        assertEquals(expResult, result.getRoster().size());
    }

}

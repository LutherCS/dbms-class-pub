package nba;
/**
 * @author Roman Yasinovskyy
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SQLiteExampleTest {
    
    public SQLiteExampleTest() {
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
     * Test of connect method, of class SQLiteExample.
     */
    @Test
    public void testConnect() throws Exception {
        //System.out.println("connect");
        String filename = "nba_example.sqlite";
        SQLiteExample instance = new SQLiteExample();
        Connection expResult = null;
        Connection result = instance.connect(filename);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of createDB method, of class SQLiteExample.
     */
    @Test
    public void testCreateDB() throws Exception {
        //System.out.println("createDB");
        String filename = "nba_example.sqlite";
        SQLiteExample instance = new SQLiteExample();
        instance.createDB(filename);
    }

    /**
     * Test of insertCity method, of class SQLiteExample.
     */
    @Test
    public void testInsertCity() throws Exception {
        //System.out.println("insertCity");
        SQLiteExample instance = new SQLiteExample();
        Connection db_connection = instance.connect("nba_example.sqlite");
        String city = "Denver";
        String state = "CO";
        instance.insertCity(db_connection, city, state);
        Statement statement = db_connection.createStatement();
        String sql = String.format("SELECT COUNT(*) FROM cities WHERE cities.city_name = '%s' AND cities.city_state = '%s';", city, state);
        ResultSet results = statement.executeQuery(sql);
        assertEquals(1, results.getInt(1));
        results.close();
    }

    /**
     * Test of insertTeam method, of class SQLiteExample.
     */
    @Test
    public void testInsertTeam() throws Exception {
        //System.out.println("insertTeam");
        SQLiteExample instance = new SQLiteExample();
        Connection db_connection = instance.connect("nba_example.sqlite");
        //Team team = new Team("Oklahoma City Thunder", "West", "Northwest", "Chesapeake Energy Arena", "Oklahoma City", "OK");
        Team team = new Team("Oklahoma City Thunder", "West", "Northwest", "Chesapeake Energy Arena", "Chicago", "IL");
        instance.insertTeam(db_connection, team);
        Statement statement = db_connection.createStatement();
        String sql = String.format("SELECT COUNT(*) FROM teams WHERE teams.team_name = '%s';", team.getName());
        ResultSet results = statement.executeQuery(sql);
        assertEquals(1, results.getInt(1));
        results.close();
    }
    
}

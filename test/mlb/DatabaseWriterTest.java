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

public class DatabaseWriterTest {
    
    public DatabaseWriterTest() {
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
     * Test of readTeamFromJson method, of class DatabaseWriter.
     */
    @Test
    public void testReadTeamFromJson() {
        System.out.println("readTeamFromJson");
        String filename = "data/mlb_teams.json";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 30;
        ArrayList<Team> result = instance.readTeamFromJson(filename);
        assertEquals(expResult, result.size());
        assertTrue(result.get(0) instanceof Team);
    }

    /**
     * Test of readAddressFromTxt method, of class DatabaseWriter.
     */
    @Test
    public void testReadAddressFromTxt() {
        System.out.println("readAddressFromTxt");
        String filename = "data/mlb_teams.txt";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 30;
        ArrayList<Address> result = instance.readAddressFromTxt(filename);
        assertEquals(expResult, result.size());
        assertTrue(result.get(0) instanceof Address);
    }

    /**
     * Test of readPlayerFromCsv method, of class DatabaseWriter.
     */
    @Test
    public void testReadPlayerFromCsv() {
        System.out.println("readPlayerFromCsv");
        String filename = "data/mlb_players.csv";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 2542;
        ArrayList<Player> result = instance.readPlayerFromCsv(filename);
        assertEquals(expResult, result.size());
        assertTrue(result.get(0) instanceof Player);
    }

    /**
     * Test of createTables method, of class DatabaseWriter.
     */
    @Test
    public void testCreateTables() throws Exception {
        System.out.println("createTables");
        String db_filename = "mlb_test.sqlite";
        DatabaseWriter instance = new DatabaseWriter();
        instance.createTables(db_filename);
    }

    /**
     * Test of writeTeamTable method, of class DatabaseWriter.
     */
    @Test
    public void testWriteTeamTable() throws Exception {
        System.out.println("writeTeamTable");
        String db_filename = "mlb_test.sqlite";
        ArrayList<Team> league = new ArrayList<>();
        league.add(new Team("luther-norse",
                            "NRS",
                            "Luther Norse",
                            "IIAC",
                            "NCAA, Division 3"));
        DatabaseWriter instance = new DatabaseWriter();
        instance.createTables(db_filename);
        instance.writeTeamTable(db_filename, league);
    }

    /**
     * Test of writeAddressTable method, of class DatabaseWriter.
     */
    @Test
    public void testWriteAddressTable() throws Exception {
        System.out.println("writeAddressTable");
        String db_filename = "mlb_test.sqlite";
        ArrayList<Team> league = new ArrayList<>();
        league.add(new Team("luther-norse",
                            "NRS",
                            "Luther Norse",
                            "IIAC",
                            "NCAA, Division 3"));
        DatabaseWriter instance = new DatabaseWriter();
        instance.createTables(db_filename);
        instance.writeTeamTable(db_filename, league);
        ArrayList<Address> addressBook = new ArrayList<>();
        addressBook.add(new Address("Luther Norse",
                               "Baseball Field",
                               "700 College Dr",
                               "Decorah",
                               "IA",
                               "52101",
                               "563-387-2000",
                               "luther.edu"));
        instance.writeAddressTable(db_filename, addressBook);
    }

    /**
     * Test of writePlayerTable method, of class DatabaseWriter.
     */
    @Test
    public void testWritePlayerTable() throws Exception {
        System.out.println("writePlayerTable");
        String db_filename = "mlb_test.sqlite";
        ArrayList<Team> league = new ArrayList<>();
        league.add(new Team("luther-norse",
                            "NRS",
                            "Luther Norse",
                            "IIAC",
                            "NCAA, Division 3"));
        DatabaseWriter instance = new DatabaseWriter();
        instance.createTables(db_filename);
        instance.writeTeamTable(db_filename, league);
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(new Player("1234",
                              "John Doe",
                              "Luther Norse",
                              "P"));
        instance.writePlayerTable(db_filename, roster);
    }
    
}

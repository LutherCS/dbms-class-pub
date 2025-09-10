package mlb;

import java.io.File;
/**
 * @author Roman Yasinovskyy
 */
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseWriterTest {

    public DatabaseWriterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Start testing class DatabaseWriter implementation");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finish testing class DatabaseWriter implementation");
        File db_file = new File("data/mlb/test.sqlite");

        if (db_file.exists()) {
            db_file.delete();
        }
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of readTeamFromJson method, of class DatabaseWriter.
     */
    @Test
    public void testReadTeamFromJson() {
        System.out.println("Testing method readTeamFromJson");
        String filename = "data/mlb/teams.json";
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
    @Disabled
    public void testReadAddressFromTxt() {
        System.out.println("Testing method readAddressFromTxt");
        String filename = "data/mlb/teams.txt";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 30;
        ArrayList<Address> result = instance.readAddressFromTxt(filename);
        assertEquals(expResult, result.size());
        assertTrue(result.get(0) instanceof Address);
    }

    /**
     * Test of readAddressFromCsv method, of class DatabaseWriter.
     */
    @Test
    public void testReadAddressFromCsv() {
        System.out.println("Testing method readAddressFromCsv");
        String filename = "data/mlb/teams.csv";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 30;
        ArrayList<Address> result = instance.readAddressFromCsv(filename);
        assertEquals(expResult, result.size());
        assertTrue(result.get(0) instanceof Address);
    }

    /**
     * Test of readPlayerFromCsv method, of class DatabaseWriter.
     */
    @Test
    public void testReadPlayerFromCsv() {
        System.out.println("readPlayerFromCsv");
        String filename = "data/mlb/players.csv";
        DatabaseWriter instance = new DatabaseWriter();
        int expResult = 1848;
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
        String db_filename = "test.sqlite";
        DatabaseWriter instance = new DatabaseWriter();
        instance.createTables(db_filename);
    }

    /**
     * Test of writeTeamTable method, of class DatabaseWriter.
     */
    @Test
    public void testWriteTeamTable() throws Exception {
        System.out.println("writeTeamTable");
        String db_filename = "test.sqlite";
        ArrayList<Team> league = new ArrayList<>();
        league.add(new Team("luther-norse",
                "NRS",
                "Luther Norse",
                "A-R-C",
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
        String db_filename = "test.sqlite";
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
        String db_filename = "test.sqlite";
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
                "NRS",
                "P"));
        instance.writePlayerTable(db_filename, roster);
    }

}

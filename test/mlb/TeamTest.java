package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {
    Team instance;
    public TeamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Address address = new Address("Luther Norse",
                                      "Baseball Field",
                                      "700 College Dr",
                                      "Decorah",
                                      "IA",
                                      "52101",
                                      "563-387-2000",
                                      "luther.edu");
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(new Player("1234",  "John Doe", "Luther Norse", "P"));
        roster.add(new Player("4321",  "Bobby Tables", "Iowa Corndogs", "SS"));
        instance = new Team("luther-norse",
                            "NRS",
                            "Luther Norse",
                            "IIAC",
                            "NCAA, Division 3");
        instance.setRoster(roster);
        instance.setAddress(address);
        ByteArrayOutputStream byteArrOutStream = null;
        try {
            File fileIn = new File("images/mlb_logo_nrs.jpg");
            FileInputStream fileInStream = new FileInputStream(fileIn);
            byte[] buffer = new byte[1024];
            byteArrOutStream = new ByteArrayOutputStream();
            for (int len; (len = fileInStream.read(buffer)) != -1;) {
                byteArrOutStream.write(buffer, 0, len);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        if (byteArrOutStream != null) {
            instance.setLogo(byteArrOutStream.toByteArray());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Team.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "luther-norse";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbbreviation method, of class Team.
     */
    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        String expResult = "NRS";
        String result = instance.getAbbreviation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Team.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Luther Norse";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConference method, of class Team.
     */
    @Test
    public void testGetConference() {
        System.out.println("getConference");
        String expResult = "IIAC";
        String result = instance.getConference();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivision method, of class Team.
     */
    @Test
    public void testGetDivision() {
        System.out.println("getDivision");
        String expResult = "NCAA, Division 3";
        String result = instance.getDivision();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoster method, of class Team.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        int expResult = 2;
        int result = instance.getRoster().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRoster method, of class Team.
     */
    @Test
    public void testSetRoster() {
        System.out.println("setRoster");
        ArrayList<Player> roster = null;
        instance.setRoster(roster);
        ArrayList<Player> expResult = null;
        ArrayList<Player> result = instance.getRoster();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Team.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Address result = instance.getAddress();
        assertTrue(result instanceof Address);
    }

    /**
     * Test of setAddress method, of class Team.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        Address address = new Address("", "", "", "", "", "", "", "");
        instance.setAddress(address);
        Address result = instance.getAddress();
        assertTrue(result instanceof Address);
    }

    /**
     * Test of getLogo method, of class Team.
     */
    @Test
    public void testGetLogo() {
        System.out.println("getLogo");
        int expResult = 23764;
        int result = instance.getLogo().length;
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogo method, of class Team.
     */
    @Test
    public void testSetLogo() {
        System.out.println("setLogo");
        byte[] logo = null;
        instance.setLogo(logo);
        byte [] expResult = null;
        byte [] result = instance.getLogo();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Team.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Luther Norse\n"
                        + "IIAC | NCAA, Division 3\n"
                        + "Baseball Field\n"
                        + "700 College Dr\n"
                        + "Decorah, IA 52101\n"
                        + "563-387-2000\n"
                        + "luther.edu\n"
                        + "Roster size: 2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

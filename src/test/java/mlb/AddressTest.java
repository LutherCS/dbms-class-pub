package mlb;

/**
 * @author Roman Yasinovskyy
 */

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    Address instance;

    public AddressTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Start testing class Address implementation");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finish testing class Address implementation");
    }

    @BeforeEach
    public void setUp() {
        instance = new Address("Luther Norse",
                "Baseball Field",
                "700 College Dr",
                "Decorah",
                "IA",
                "52101",
                "563-387-2000",
                "luther.edu");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTeam method, of class Address.
     */
    @Test
    public void testGetTeam() {
        System.out.println("Testing method getTeam");
        String expResult = "Luther Norse";
        String result = instance.getTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSite method, of class Address.
     */
    @Test
    public void testGetStadium() {
        System.out.println("Testing method getStadium");
        String expResult = "Baseball Field";
        String result = instance.getStadium();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStreet method, of class Address.
     */
    @Test
    public void testGetStreet() {
        System.out.println("Testing method getStreet");
        String expResult = "700 College Dr";
        String result = instance.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class Address.
     */
    @Test
    public void testGetCity() {
        System.out.println("Testing method getCity");
        String expResult = "Decorah";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Address.
     */
    @Test
    public void testGetState() {
        System.out.println("Testing method getState");
        String expResult = "IA";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZip method, of class Address.
     */
    @Test
    public void testGetZip() {
        System.out.println("Testing method getZip");
        String expResult = "52101";
        String result = instance.getZip();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Address.
     */
    @Test
    public void testGetPhone() {
        System.out.println("Testing method getPhone");
        String expResult = "563-387-2000";
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class Address.
     */
    @Test
    public void testGetUrl() {
        System.out.println("Testing method getUrl");
        String expResult = "luther.edu";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("Testing method toString");
        String expResult = "Baseball Field\n700 College Dr\nDecorah, IA 52101\n563-387-2000\nluther.edu";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}

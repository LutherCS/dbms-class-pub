package mlb;
/**
 * @author Roman Yasinovskyy
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {
    Address instance;
    public AddressTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTeam method, of class Address.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        String expResult = "Luther Norse";
        String result = instance.getTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSite method, of class Address.
     */
    @Test
    public void testGetSite() {
        System.out.println("getSite");
        String expResult = "Baseball Field";
        String result = instance.getSite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStreet method, of class Address.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        String expResult = "700 College Dr";
        String result = instance.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class Address.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        String expResult = "Decorah";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Address.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = "IA";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZip method, of class Address.
     */
    @Test
    public void testGetZip() {
        System.out.println("getZip");
        String expResult = "52101";
        String result = instance.getZip();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Address.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "563-387-2000";
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class Address.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        String expResult = "luther.edu";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Baseball Field\n700 College Dr\nDecorah, IA 52101\n563-387-2000\nluther.edu";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

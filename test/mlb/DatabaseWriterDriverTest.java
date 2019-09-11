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

public class DatabaseWriterDriverTest {
    
    public DatabaseWriterDriverTest() {
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
     * Test of main method, of class DatabaseWriterDriver.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"mlb_test.sqlite"};
        long start = System.nanoTime();
        DatabaseWriterDriver.main(args);
        long end = System.nanoTime();
        long expResult = 50;
        long result = (end-start) / 1000000000;
        assertTrue(expResult > result);
    }
    
}

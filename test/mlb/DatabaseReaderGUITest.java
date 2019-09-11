package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseReaderGUITest {
    
    public DatabaseReaderGUITest() {
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
     * Test of main method, of class DatabaseReaderGUI.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        Robot bot;
        String[] args = null;
        DatabaseReaderGUI.main(args);
        try {
            bot = new Robot();
            // Alt+F4
            bot.keyPress(33);
        } catch (AWTException ex) {
            Logger.getLogger(DatabaseReaderGUITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

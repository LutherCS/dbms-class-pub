package mlb;

/**
 * @author Roman Yasinovskyy
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseReaderGUITest {

    public DatabaseReaderGUITest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("DatabaseReaderGUI");
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
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

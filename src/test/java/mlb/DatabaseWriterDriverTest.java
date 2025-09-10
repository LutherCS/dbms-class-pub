package mlb;

/**
 * @author Roman Yasinovskyy
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseWriterDriverTest {

    public DatabaseWriterDriverTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Start testing class DatabaseWriterDriver implementation");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finish testing class DatabaseWriterDriver implementation");
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class DatabaseWriterDriver.
     */
    @Test
    public void testMain() {
        System.out.println("Testing method main");
        String[] args = { "test.sqlite" };
        DatabaseWriterDriver.main(args);
    }
}

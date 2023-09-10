package mlb;

/**
 * @author Roman Yasinovskyy
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player instance;

    public PlayerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Player");
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Player("1234",
                "John Doe",
                "Luther Norse",
                "P");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Player.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "1234";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "John Doe";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeam method, of class Player.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        String expResult = "Luther Norse";
        String result = instance.getTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        String expResult = "P";
        String result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "John Doe plays for Luther Norse";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}

package aba;

import nba.Team;

/**
 *
 * @author Roman Yasinovskyy
 */

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class SQLiteExampleTest {

    public SQLiteExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        String filename = "test.sqlite";
        SQLiteExample instance = new SQLiteExample();
        try {
            instance.createDB(filename);
        } catch (SQLException e) {
            fail("Should not throw an exception");
        }
    }

    @AfterAll
    public static void tearDownClass() {
        File db_file = new File("data/aba/test.sqlite");

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
     * Test of connect method, of class SQLiteExample.
     */
    @Test
    public void testConnect() {
        // System.out.println("connect");
        String filename = "test.sqlite";
        SQLiteExample instance = new SQLiteExample();
        try {
            Connection result = instance.connect(filename);
            assertNotNull(result);
        } catch (SQLException e) {
            fail("Should never throw an exception");
        }
    }

    /**
     * Test of createDB method, of class SQLiteExample.
     */
    @Test
    public void testCreateDB() {
        // System.out.println("createDB");
        String filename = "test.sqlite";
        SQLiteExample instance = new SQLiteExample();
        try {
            instance.createDB(filename);
        } catch (SQLException e) {
            fail("Should not throw an exception");
        }
    }

    /**
     * Test of insertCity method, of class SQLiteExample.
     */
    @Test
    public void testInsertCity() {
        // System.out.println("insertCity");
        SQLiteExample instance = new SQLiteExample();
        String city = "Baltimore";
        String state = "MD";
        Connection db_connection;
        try {
            db_connection = instance.connect("test.sqlite");
            instance.insertCity(db_connection, city, state);
            Statement statement = db_connection.createStatement();
            String sql = String.format(
                    "SELECT COUNT(*) FROM cities WHERE cities.city_name = '%s' AND cities.city_state = '%s';", city,
                    state);
            ResultSet results = statement.executeQuery(sql);
            assertEquals(1, results.getInt(1));
            results.close();
        } catch (SQLException e) {
            fail("Should not throw an exception");
        }
    }

    /**
     * Test of insertTeam method, of class SQLiteExample.
     */
    @Test
    public void testInsertTeam() {
        // System.out.println("insertTeam");
        SQLiteExample instance = new SQLiteExample();
        Team team = new Team("Kentucky Colonels", "East", "Midwest", "Louisville Convention Center", "Louisville", "KY");
        Connection db_connection;
        try {
            db_connection = instance.connect("test.sqlite");
            instance.insertTeam(db_connection, team);
            Statement statement = db_connection.createStatement();
            String sql = String.format("SELECT COUNT(*) FROM teams WHERE teams.team_name = '%s';", team.getName());
            ResultSet results = statement.executeQuery(sql);
            assertEquals(1, results.getInt(1));
            results.close();
        } catch (SQLException e) {
            fail("Should not throw an exception");
        }
    }

}

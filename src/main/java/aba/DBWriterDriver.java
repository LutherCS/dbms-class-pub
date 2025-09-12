package aba;

/**
 * @author Roman Yasinovskyy
 * This is a basic example of using Java to access SQLite DB.
 * This application creates a new DB with two tables, cities and teams.
 * city(id, name, state)
 * team(id, name, conference, division, site, city_id)
 * Both tables are populated with some initial data.
 * Print statements are commented out for clarity.
 * http://www.sqlitetutorial.net/sqlite-java/
 * https://www.javatpoint.com/sqlite-tutorial
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DBWriterDriver {
    /**
     * main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBWriter sqlite = new DBWriter();
        Connection db_connection = null;
        Statement statement = null;
        ResultSet results = null;
        Logger logger = Logger.getLogger(DBWriterDriver.class.getName());
        logger.setLevel(Level.WARNING);
        /*
         * Create a new database or create a new one.
         */
        try {
            db_connection = sqlite.connect("aba.sqlite");
            DatabaseMetaData meta = db_connection.getMetaData();
            logger.log(Level.INFO, String.format("The driver name is %s.%n", meta.getDriverName()));
            logger.log(Level.INFO, "Creating the database");
            sqlite.createDB(db_connection);
            logger.log(Level.INFO, "Initializing tables");
            initTableArena(db_connection);
            logger.log(Level.INFO, "Table ARENA has been initialized");
            initTableTeam(db_connection);
            logger.log(Level.INFO, "Table TEAM has been initialized");
            logger.log(Level.INFO, "Inserting new arenas");
            sqlite.insertArena(db_connection, "Long Island", "New York", "NY");
            sqlite.insertArena(db_connection, "Pittsburgh Civic Arena", "Pittsburgh", "PA");
            logger.log(Level.INFO, "Arenas have been inserted");
            logger.log(Level.INFO, "Reading ARENA records");
            System.out.printf("Records in the ARENA table%n");
            System.out.printf("%-5s%-30s%-15s%-5s%n", "ID", "Name", "City", "State");
            statement = db_connection.createStatement();
            results = statement.executeQuery("SELECT * FROM arena;");
            while (results.next()) {
                System.out.printf("%-5s%-30s%-15s%-5s%n", results.getInt("id"), results.getString("name"),
                        results.getString("city"), results.getString("state"));
            }
            logger.log(Level.INFO, "Inserting new teams");
            Team nya = new Team("New York Americans", new Arena("Long Island", "New York", "NY"));
            Team pbp = new Team("Pittsburgh Pipers", new Arena("Pittsburgh Civic Arena", "Pittsburgh", "PA"));
            sqlite.insertTeam(db_connection, nya);
            sqlite.insertTeam(db_connection, pbp);
            logger.log(Level.INFO, "Teams have been inserted");
            logger.log(Level.INFO, "Reading TEAM records");
            System.out.printf("Records in the TEAM table%n");
            System.out.printf("%-5s%-30s%-25s%-20s%n", "ID", "Name", "Arena", "City");
            statement = db_connection.createStatement();
            results = statement
                    .executeQuery("SELECT * FROM team INNER JOIN arena ON team.arena = arena.id;");
            while (results.next()) {
                System.out.printf("%-5s%-30s%-25s%-20s%n", results.getInt("id"),
                        results.getString("name"), results.getString("name"),
                        results.getString("city"));
            }
            db_connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add sample records to the ARENA table.
     * 
     * @param db_connection
     * @throws SQLException
     */
    private static void initTableArena(Connection db_connection) throws SQLException {
        String sql = "INSERT INTO ARENA(name, city, state) VALUES (?, ?, ?);";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, "Anaheim Convention Center");
        statement_prepared.setString(2, "Anaheim");
        statement_prepared.setString(3, "CA");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Moody Coliseum");
        statement_prepared.setString(2, "Dallas");
        statement_prepared.setString(3, "TX");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Sam Houston Coliseum");
        statement_prepared.setString(2, "Houston");
        statement_prepared.setString(3, "TX");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Coliseum Arena");
        statement_prepared.setString(2, "Oakland");
        statement_prepared.setString(3, "CA");
        statement_prepared.addBatch();
        /*
         * Commit once the whole batch is executed.
         */
        db_connection.setAutoCommit(false);
        statement_prepared.executeBatch();
        db_connection.commit();
    }

    /**
     * Add sample records to the TEAM table.
     * 
     * @param db_connection
     * @throws SQLException
     */
    private static void initTableTeam(Connection db_connection) throws SQLException {
        String sql = "INSERT INTO team(name, arena) VALUES (?, ?);";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, "Anaheim Amigos");
        statement_prepared.setString(2, "1"); // Anaheim Convention Center
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Dallas Chaparrals");
        statement_prepared.setString(2, "2"); // Moody Coliseum
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Houston Mavericks");
        statement_prepared.setString(2, "3"); // Sam Houston Coliseum
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Oakland Oaks");
        statement_prepared.setString(2, "4"); // Coliseum Arena
        statement_prepared.addBatch();
        /*
         * Commit once the whole batch is executed.
         */
        db_connection.setAutoCommit(false);
        statement_prepared.executeBatch();
        db_connection.setAutoCommit(true);
    }
}

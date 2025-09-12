package aba;

/**
 * @author Roman Yasinovskyy
 * This is a basic example of using Java to access SQLite DB.
 * This class provides simple interfaces to create a new DB, and two tables.
 * Table team(id, name, arena)
 * Table arena(id, name, city)
 * Both tables are populated with some initial data.
 * http://www.sqlitetutorial.net/sqlite-java/
 * https://www.javatpoint.com/sqlite-tutorial
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    /*
     * Database will be created in the *data* directory of the project.
     */
    public final String SQLITEDBPATH = "jdbc:sqlite:data/aba/";

    /**
     * Connect to a database (file) with the specified name
     * If the file does not exist, create it.
     * 
     * @param filename
     * @return Connection to the DB
     * @throws SQLException
     */
    public Connection connect(String filename) throws SQLException {
        Connection db_connection = DriverManager.getConnection(SQLITEDBPATH + filename);

        return db_connection;
    }

    /**
     * Create a new database (file) with the specified name
     * 
     * @param filename
     * @throws SQLException
     */
    public void createDB(Connection db_connection) throws SQLException {
        Statement statement = db_connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS arena;");
        statement.executeUpdate("CREATE TABLE arena ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name TEXT NOT NULL,"
                + "city TEXT NOT NULL,"
                + "state TEXT NOT NULL);");
        /*
         * Enforce foreign keys.
         * https://sqlite.org/pragma.html
         */
        statement.execute("PRAGMA foreign_keys = ON;");
        /*
         * Clear the TEAM table and create it according to the schema.
         */
        statement.executeUpdate("DROP TABLE IF EXISTS team;");
        statement.executeUpdate("CREATE TABLE team ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name TEXT NOT NULL,"
                + "arena,"
                + "FOREIGN KEY (arena) REFERENCES arena(id));");
    }

    /**
     * Insert a new record into the ARENA table.
     * 
     * @param db_connection
     * @param name
     * @param city
     * @param state
     * @throws SQLException
     */
    public void insertArena(Connection db_connection, String name, String city, String state) throws SQLException {
        String sql = "INSERT INTO arena(name, city, state) VALUES(?, ?, ?)";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, name);
        statement_prepared.setString(2, city);
        statement_prepared.setString(3, state);
        statement_prepared.executeUpdate();
        statement_prepared.close();
    }

    public void insertTeam(Connection db_connection, Team team) throws SQLException {
        Statement statement = db_connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT id FROM arena WHERE arena.name = '"
                + team.getArena().getName() + "' AND arena.city = '" + team.getArena().getCity() + "';");
        int arenaId = results.getInt(1);
        String sql = "INSERT INTO team(name, arena) VALUES(?, ?)";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, team.getName());
        statement_prepared.setInt(2, arenaId);
        statement_prepared.executeUpdate();
        statement_prepared.close();
    }
}

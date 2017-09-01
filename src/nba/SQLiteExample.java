package nba;
/**
 * @author Roman Yasinovskyy
 * This is a basic example of using Java to access SQLite DB.
 * This class provides simple interfaces to create a new DB, and two tables.
 * Table city(id, name, state)
 * Table team(id, name, conference, division, site, city_id)
 * Both tables are populated with some initial data.
 * Print statements are commented out for clarity.
 * http://www.sqlitetutorial.net/sqlite-java/
 * https://www.javatpoint.com/sqlite-tutorial
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {
    /* 
     * Database will be created in the *data* directory of the project.
     */
    public final String SQLITEDBPATH = "jdbc:sqlite:data/";
    
    /**
     * Connect to a database (file) with the specified name
     * @param filename
     * @return Connection to the DB
     * @throws SQLException
     */
    public Connection connect(String filename) throws SQLException {
        //System.out.printf("Connecting to the database %s.%n", filename);
        /* 
         * Connect to the database (file). If the file does not exist, create it.
         */
        Connection db_connection = DriverManager.getConnection(SQLITEDBPATH + filename);
        //System.out.printf("Connection to the database has been established.%n");
        /* 
         * Get the database metadata.
         */
        DatabaseMetaData meta = db_connection.getMetaData();
        //System.out.printf("The driver name is %s.%n", meta.getDriverName());
        
        return db_connection;
    }
    /**
     * Create a new database (file) with the specified name
     * @param filename
     * @throws SQLException
     */
    public void createDB(String filename) throws SQLException {
        //System.out.printf("Connecting to the database %s.%n", filename);
        /* 
         * Connect to the database (file). If the file does not exist, create it.
         */
        Connection db_connection = DriverManager.getConnection(SQLITEDBPATH + filename);
        this.createTables(db_connection);
        this.initTableCities(db_connection);
        this.initTableTeams(db_connection);
        //System.out.printf("Connection to the database has been established.%n");
        db_connection.close();
        //System.out.printf("Connection to the database has been closed.%n");
    }
    /**
     * Create tables cities and teams
     * @param db_connection
     * @throws SQLException 
     */
    private void createTables(Connection db_connection) throws SQLException {
        /*
         * Use Statement to execute SQL.
         */
        Statement statement = db_connection.createStatement();
        /*
         * Clear the *cities* table and create it according to the schema.
         */
        statement.executeUpdate("DROP TABLE IF EXISTS cities;");
        //System.out.printf("Creating table *cities*.%n");
        statement.executeUpdate("CREATE TABLE cities ("
                            + "city_id_pk INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                            + "city_name TEXT NOT NULL,"
                            + "city_state TEXT NOT NULL);");
        //System.out.printf("Table *cities* has been created.%n");
        /*
         * Enforce foreign keys.
         * https://sqlite.org/pragma.html
        */
        statement.execute("PRAGMA foreign_keys = ON;");
        /*
         * Clear the *teams* table and create it according to the schema.
         */
        statement.executeUpdate("DROP TABLE IF EXISTS teams;");
        //System.out.printf("Creating table *teams*.%n");
        statement.executeUpdate("CREATE TABLE teams ("
                            + "team_id_pk INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                            + "team_name TEXT NOT NULL,"
                            + "team_conference TEXT NOT NULL,"
                            + "team_division TEXT NOT NULL,"
                            + "team_site TEXT NOT NULL,"
                            + "city_id,"
                            + "FOREIGN KEY (city_id) REFERENCES cities(city_id_pk));");
        //System.out.printf("Table *teams* has been created.%n");
    }
    /**
     * Add sample records to the *cities* table.
     * @param db_connection
     * @throws SQLException 
     */
    private void initTableCities(Connection db_connection) throws SQLException {
        //System.out.printf("Adding records to the table *cities*.%n");
        /*
         * Use PreparedStatement as a template.
         * city_id is generated automatically.
         */
        String sql = "INSERT INTO cities(city_name, city_state) VALUES (?, ?);";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, "Atlanta");
        statement_prepared.setString(2, "GA");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Boston");
        statement_prepared.setString(2, "MA");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Chicago");
        statement_prepared.setString(2, "IL");
        statement_prepared.addBatch();
        statement_prepared.setString(1, "Los Angeles");
        statement_prepared.setString(2, "CA");
        statement_prepared.addBatch();
        /*
         * Commit once the whole batch is executed.
         */
        db_connection.setAutoCommit(false);
        statement_prepared.executeBatch();
        db_connection.commit();
    }
    /**
     * Insert a new record into the *cities* table.
     * @param db_connection
     * @param city
     * @param state
     * @throws SQLException
     */
    public void insertCity(Connection db_connection, String city, String state) throws SQLException {
        //System.out.printf("Adding a new record to the table *cities*.%n");
        String sql = "INSERT INTO cities(city_name, city_state) VALUES(?, ?)";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, city);
        statement_prepared.setString(2, state);
        statement_prepared.executeUpdate();
        statement_prepared.close();
    }
    /**
     * Add sample records to the *teams* table.
     * @param db_connection
     * @throws SQLException 
     */
    private void initTableTeams(Connection db_connection) throws SQLException {
        //System.out.printf("Adding records to the table *teams*.%n");
        /*
         * Use PreparedStatement as a template.
         */
        String sql = "INSERT INTO teams VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(2, "Atlanta Hawks");
        statement_prepared.setString(3, "East");
        statement_prepared.setString(4, "Southeast");
        statement_prepared.setString(5, "Philips Arena");
        statement_prepared.setString(6, "1");  // Atlanta
        statement_prepared.addBatch();
        statement_prepared.setString(2, "Boston Celtics");
        statement_prepared.setString(3, "East");
        statement_prepared.setString(4, "Atlantic");
        statement_prepared.setString(5, "TD Garden");
        statement_prepared.setString(6, "2");  // Boston
        statement_prepared.addBatch();
        statement_prepared.setString(2, "Los Angeles Clippers");
        statement_prepared.setString(3, "West");
        statement_prepared.setString(4, "Pacific");
        statement_prepared.setString(5, "Staples Center");
        statement_prepared.setString(6, "4");  // Los Angeles
        statement_prepared.addBatch();
        statement_prepared.setString(2, "Los Angeles Lakers");
        statement_prepared.setString(3, "West");
        statement_prepared.setString(4, "Pacific");
        statement_prepared.setString(5, "Staples Center");
        statement_prepared.setString(6, "4");  // Los Angeles
        statement_prepared.addBatch();
        /*
         * Commit once the whole batch is executed.
         */
        db_connection.setAutoCommit(false);
        statement_prepared.executeBatch();
        db_connection.setAutoCommit(true);
    }
    public void insertTeam(Connection db_connection, Team team) throws SQLException {
        Statement statement = db_connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT city_id_pk FROM cities WHERE cities.city_name = '" + team.getCity() + "' AND cities.city_state = '" + team.getState() + "';");
        int city_id = results.getInt(1);
        //System.out.printf("Adding a new record to the table *teams*.%n");
        String sql = "INSERT INTO teams(team_name, team_conference, team_division, team_site, city_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
        statement_prepared.setString(1, team.getName());
        statement_prepared.setString(2, team.getConference());
        statement_prepared.setString(3, team.getDivision());
        statement_prepared.setString(4, team.getSite());
        statement_prepared.setInt(5, city_id);
        statement_prepared.executeUpdate();
        statement_prepared.close();
    }
}

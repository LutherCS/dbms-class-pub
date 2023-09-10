package mlb;

/**
 * @author Roman Yasinovskyy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseReader {

    private Connection db_connection;
    private final String SQLITEDBPATH = "jdbc:sqlite:data/mlb/mlb.sqlite";

    public DatabaseReader() {
    }

    /**
     * Connect to a database (file)
     */
    public void connect() {
        try {
            this.db_connection = DriverManager.getConnection(SQLITEDBPATH);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Disconnect from a database (file)
     */
    public void disconnect() {
        try {
            this.db_connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populate the list of divisions
     *
     * @param divisions
     */
    public void getDivisions(ArrayList<String> divisions) {
        Statement stat;
        ResultSet results;

        this.connect();
        try {
            stat = this.db_connection.createStatement();
            // TODO: Write an SQL statement to retrieve a league (conference) and a division
            String sql = "";
            results = stat.executeQuery(sql);
            // TODO: Add all 6 combinations to the ArrayList divisions
            results.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
    }

    /**
     * Read all teams from the database
     *
     * @param confDiv
     * @param teams
     */
    public void getTeams(String confDiv, ArrayList<String> teams) {
        Statement stat;
        ResultSet results;
        String conference = confDiv.split(" | ")[0];
        String division = confDiv.split(" | ")[2];

        this.connect();
        try {
            stat = this.db_connection.createStatement();
            // TODO: Write an SQL statement to retrieve a teams from a specific division
            String sql = "";
            results = stat.executeQuery(sql);
            // TODO: Add all 5 teams to the ArrayList teams
            results.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
    }

    /**
     * @param teamName
     * @return Team info
     */
    public Team getTeamInfo(String teamName) {
        Statement stat;
        ResultSet results;
        Team team = null;
        ArrayList<Player> roster = new ArrayList<>();
        Address address = null;

        // TODO: Retrieve team info (roster, address, and logo) from the database
        return team;
    }
}

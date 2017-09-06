package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseWriterDriver {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseWriter dw = new DatabaseWriter();
        String db_filename = "mlb.sqlite";
        if (args.length != 0) {
            db_filename = args[0];
        }
        try {
            dw.createTables(db_filename);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Read teams from JSON */
        ArrayList<Team> mlb = dw.readTeamFromJson("data/mlb_teams.json");
        try {
            dw.writeTeamTable(db_filename, mlb);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Read addresses from TXT */
        ArrayList<Address> addressBook = dw.readAddressFromTxt("data/mlb_teams.txt");
        try {
            dw.writeAddressTable(db_filename, addressBook);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Read players from CSV */
        ArrayList<Player> roster = dw.readPlayerFromCsv("data/mlb_players.csv");
        try {
            dw.writePlayerTable(db_filename, roster);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

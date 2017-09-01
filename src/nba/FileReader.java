package nba;
/**
 * @author Roman Yasinovskyy
 */
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class FileReader {
    public FileReader() { }
    /**
     * @param filename (JSON file)
     * @return League of Teams
     */
    public League readFileJson(String filename) {
        League league = new League();
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        
        try {
            jp = f.createParser(new File(filename));
            jp.nextToken();
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                Team team = mapper.readValue(jp, Team.class);
                league.add(team);
            }
            jp.close();
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return league;
    }
    /**
     * @param filename (TXT file)
     * @return League of Teams
     */
    public League readFileTxt(String filename) {
        League league = new League();
        Pattern p = Pattern.compile("(\\w+\\s\\w+\\s*\\w*)\\s+(East|West)\\s+(\\w+)\\s+((\\w+[\\s&])+)\\s+(.+),\\s(.+)");
        
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                Matcher m = p.matcher(fs.nextLine());
                boolean isTeam = m.matches();
                if (isTeam) {
                    Team team = new Team(m.group(1), m.group(2), m.group(3), m.group(4), m.group(6), m.group(7));
                    league.add(team);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return league;
    }
    /**
     * @param filename (SQLITE file)
     * @return League of Teams
     */
    public League readFileSqlite(String filename) {
        League league = new League();
        Connection conn;
        Statement stat;
        ResultSet rs;
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM nba_teams, nba_cities WHERE nba_teams.cities_id = nba_cities.c_id");
            while (rs.next()) {
                Team team = new Team(rs.getString("t_name"),
                        rs.getString("t_conference"),
                        rs.getString("t_division"),
                        rs.getString("t_site"),
                        rs.getString("c_city"),
                        rs.getString("c_state"));
                league.add(team);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return league;
    }
}

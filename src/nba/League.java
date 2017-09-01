package nba;
/**
 * @author Roman Yasinovskyy
 */
import java.util.ArrayList;

public class League {
    private final ArrayList<Team> league;

    public League() {
        league = new ArrayList<>();
    }

    public ArrayList<Team> getTeamList() {
        return league;
    }
   
    public void add(Team t) {
        league.add(t);
    }
}

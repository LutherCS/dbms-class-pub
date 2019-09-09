package nba;

import java.util.ArrayList;

/**
 *
 * @author Roman Yasinovskyy
 */
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

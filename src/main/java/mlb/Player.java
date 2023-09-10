package mlb;

/**
 * @author Roman Yasinovskyy
 */
import org.apache.commons.lang3.NotImplementedException;

public class Player {

    private final String id;
    private final String name;
    private final String team;
    private final String position;

    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param team
     * @param position
     */
    public Player(String id, String name, String team, String position) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.position = position;
    }

    /**
     * @return Player id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return Player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Player team
     */
    public String getTeam() {
        return this.team;
    }

    /**
     * @return Player position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * @return Player as a String
     */
    @Override
    public String toString() {
        // TODO Implement this method
        throw new NotImplementedException();
    }
}

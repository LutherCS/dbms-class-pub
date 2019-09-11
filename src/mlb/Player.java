package mlb;
/**
 * @author Roman Yasinovskyy
 */
public class Player {
    private final String id;
    private final String name;
    private final String team;
    private final String position;
    /**
     * Class constructor
     * @param _id
     * @param _name
     * @param _team
     * @param _position 
     */
    public Player(String _id, String _name, String _team, String _position) {
        this.id = _id;
        this.name = _name;
        this.team = _team;
        this.position = _position;
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
        // TODO: Implement this method
        throw new UnsupportedOperationException();
    }
}
package aba;

/**
 * @author Roman Yasinovskyy
 * 
 *         A simple ABA team
 *         https://en.wikipedia.org/wiki/American_Basketball_Association
 */

public class Team {
    private final String name;
    private final Arena arena;

    /**
     * Class constructor
     * 
     * @param name
     * @param arena
     */
    public Team(String name, Arena arena) {
        this.name = name;
        this.arena = arena;
    }

    /**
     * @return Team's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Team's arena
     */
    public Arena getArena() {
        return arena;
    }
}

package aba;

/**
 * @author Roman Yasinovskyy
 * 
 *         A simple ABA team arena
 */

public class Arena {
    private final String name;
    private final String city;
    private final String state;

    /**
     * Class constructor
     * 
     * @param name
     * @param city
     * @param state
     */
    public Arena(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;

    }

    /**
     * @return Arena's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Arena's city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Arena's state
     */
    public String getState() {
        return state;
    }
}

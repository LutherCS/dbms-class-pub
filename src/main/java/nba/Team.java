package nba;

/**
 *
 * @author Roman Yasinovskyy
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Team {
    // @JsonProperty("team_id")
    // private final String team_id;
    // @JsonProperty("abbreviation")
    // private final String abbreviation;
    // @JsonProperty("active")
    // private final boolean active;
    // @JsonProperty("first_name")
    // private final String first_name;
    // @JsonProperty("last_name")
    // private final String last_name;
    @JsonProperty("conference")
    private final String conference;
    @JsonProperty("division")
    private final String division;
    @JsonProperty("site_name")
    private final String arena;
    @JsonProperty("city")
    private final String city;
    @JsonProperty("state")
    private final String state;
    @JsonProperty("full_name")
    private final String name;

    /**
     * Default class constructor.
     * Needed to load json properly.
     */
    public Team() {
        this.name = "";
        this.conference = "";
        this.division = "";
        this.arena = "";
        this.city = "";
        this.state = "";
    }

    /**
     * Class constructor
     * 
     * @param name
     * @param conference
     * @param division
     * @param arena
     * @param city
     * @param state
     */
    public Team(String name, String conference, String division, String arena, String city, String state) {
        this.name = name;
        this.conference = conference;
        this.division = division;
        this.arena = arena;
        this.city = city;
        this.state = state;
    }

    /**
     * @return Team's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Team's Conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * @return Team's division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return Team's arena
     */
    public String getArena() {
        return arena;
    }

    /**
     * @return Team's city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Team's state
     */
    public String getState() {
        return state;
    }
}

package nba;
/**
 * @author Roman Yasinovskyy
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties( ignoreUnknown = true )

public class Team {
    //@JsonProperty("team_id")
    //private final String team_id;
    //@JsonProperty("abbreviation")
    //private final String abbreviation;
    //@JsonProperty("active")
    //private final boolean active;
    //@JsonProperty("first_name")
    //private final String first_name;
    //@JsonProperty("last_name")
    //private final String last_name;
    @JsonProperty("conference")
    private final String conference;
    @JsonProperty("division")
    private final String division;
    @JsonProperty("site_name")
    private final String site_name;
    @JsonProperty("city")
    private final String city;
    @JsonProperty("state")
    private final String state;
    @JsonProperty("full_name")
    private final String full_name;
    
    /**
     * Default class constructor.
     * Needed to load json properly.
     */
    public Team() {
        full_name = "";
        conference = "";
        division = "";
        site_name = "";
        city = "";
        state = "";
    }
    /**
     * Class constructor
     * @param _name
     * @param _conf
     * @param _div
     * @param _site
     * @param _city
     * @param _state 
     */
    public Team(String _name, String _conf, String _div, String _site, String _city, String _state) {
        full_name = _name;
        conference = _conf;
        division = _div;
        site_name = _site;
        city = _city;
        state = _state;
    }
    /**
     * @return Team's name
     */
    public String getName() {
        return full_name;
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
     * @return Team's site (arena)
     */
    public String getSite() {
        return site_name;
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

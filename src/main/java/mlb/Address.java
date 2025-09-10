package mlb;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author Roman Yasinovskyy
 */
public class Address {

    private final String team;
    private final String stadium;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phone;
    private final String url;

    /**
     * @param team
     * @param stadium
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param phone
     * @param url
     */
    public Address(String team, String stadium, String street, String city, String state, String zip, String phone,
            String url) {
        this.team = team;
        this.stadium = stadium;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.url = url;
    }

    /**
     * @return Team name
     */
    public String getTeam() {
        return this.team;
    }

    /**
     * @return Team stadium
     */
    public String getStadium() {
        return this.stadium;
    }

    /**
     * @return Street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * @return City
     */
    public String getCity() {
        return this.city;
    }

    /**
     * @return State
     */
    public String getState() {
        return this.state;
    }

    /**
     * @return Zip
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * @return Phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @return Url
     */
    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        // TODO Implement this method
        throw new NotImplementedException();
    }
}

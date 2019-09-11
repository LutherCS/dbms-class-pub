package mlb;
/**
 * @author Roman Yasinovskyy
 */
public class Address {
    private final String team;
    private final String site;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phone;
    private final String url;
    /**
     * @param _team
     * @param _site
     * @param _street
     * @param _city
     * @param _state
     * @param _zip
     * @param _phone
     * @param _url 
     */
    public Address(String _team, String _site, String _street, String _city, String _state, String _zip, String _phone, String _url) {
        this.team = _team;
        this.site = _site;
        this.street = _street;
        this.city = _city;
        this.state = _state;
        this.zip = _zip;
        this.phone = _phone;
        this.url = _url;
    }
    /**
     * @return Team name
     */
    public String getTeam() {
        return this.team;
    }
    /**
     * @return Team site
     */
    public String getSite() {
        return this.site;
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
        // TODO: Implement this method
        throw new UnsupportedOperationException();
    }
}
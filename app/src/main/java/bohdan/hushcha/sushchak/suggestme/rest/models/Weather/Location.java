package bohdan.hushcha.sushchak.suggestme.rest.models.Weather;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("title")
    private String Title;

    @SerializedName("location_type")
    private String LocationType;

    @SerializedName("woeid")
    private String Woeid;

    @SerializedName("latt_long")
    private String LattLong;

    public Location(String title, String locationType, String woeid, String lattLong) {
        this.Title = title;
        this.LocationType = locationType;
        this.Woeid = woeid;
        this.LattLong = lattLong;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public String getWoeid() {
        return Woeid;
    }

    public void setWoeid(String woeid) {
        Woeid = woeid;
    }

    public String getLattLong() {
        return LattLong;
    }

    public void setLattLong(String lattLong) {
        LattLong = lattLong;
    }
}

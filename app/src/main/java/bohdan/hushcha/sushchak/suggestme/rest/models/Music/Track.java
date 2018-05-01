package bohdan.hushcha.sushchak.suggestme.rest.models.Music;

import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("name")
    private String Name;

    @SerializedName("duration")
    private Integer Duration;

    @SerializedName("mbid")
    private String Id;

    @SerializedName("url")
    private String Url;

    @SerializedName("artist")
    private Artist Artist;

    public Track(String name, Integer duration, String id, String url, bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist artist) {
        this.Name = name;
        this.Duration = duration;
        this.Id = id;
        this.Url = url;
        this.Artist = artist;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist getArtist() {
        return Artist;
    }

    public void setArtist(bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist artist) {
        Artist = artist;
    }
}

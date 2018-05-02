package bohdan.hushcha.sushchak.suggestme.rest.models.Music;

import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("name")
    private String Name;

    @SerializedName("playcount")
    private String PlayCount;

    @SerializedName("mbid")
    private String Id;

    @SerializedName("url")
    private String Url;

    @SerializedName("artist")
    private Artist Artist;


    public Album(String name, String playCount, String id, String url, Artist artist) {
        this.Name = name;
        this.PlayCount = playCount;
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

    public String getPlayCount() {
        return PlayCount;
    }

    public void setPlayCount(String playCount) {
        PlayCount = playCount;
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

    public Artist getArtist() {
        return Artist;
    }

    public void setArtist(Artist artist) {
        Artist = artist;
    }


}

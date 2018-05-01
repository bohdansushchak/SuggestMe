package bohdan.hushcha.sushchak.suggestme.rest.models.Music;

import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    private String Name;

    @SerializedName("mbid")
    private String Id;

    @SerializedName("url")
    private String Url;

    public Artist(String name, String id, String url) {
        this.Name = name;
        this.Id = id;
        this.Url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}

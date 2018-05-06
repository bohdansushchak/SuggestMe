package bohdan.hushcha.sushchak.suggestme.rest.models.Cinema;

import com.google.gson.annotations.SerializedName;

public class MovieCollection {

    @SerializedName("id")
    private String Id;

    @SerializedName("name")
    private String Name;

    @SerializedName("poster_path")
    private String PosterPath;

    @SerializedName("backdrop_path")
    private String BackDropPath;

    public MovieCollection(String id, String name, String posterPath, String backDropPath) {
        this.Id = id;
        this.Name = name;
        this.PosterPath = posterPath;
        this.BackDropPath = backDropPath;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getBackDropPath() {
        return BackDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        BackDropPath = backDropPath;
    }
}

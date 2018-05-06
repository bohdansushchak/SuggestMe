package bohdan.hushcha.sushchak.suggestme.rest.models.Cinema;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("id")
    private String Id;

    @SerializedName("logo_path")
    private String LogoPath;

    @SerializedName("name")
    private String Name;

    @SerializedName("origin_country")
    private String OriginalCountry;

    public Company(String id, String logoPath, String name, String originalCountry) {
        this.Id = id;
        this.LogoPath = logoPath;
        this.Name = name;
        this.OriginalCountry = originalCountry;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLogoPath() {
        return "https://image.tmdb.org/t/p/w500" + LogoPath;
    }

    public void setLogoPath(String logoPath) {
        LogoPath = logoPath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOriginalCountry() {
        return OriginalCountry;
    }

    public void setOriginalCountry(String originalCountry) {
        OriginalCountry = originalCountry;
    }
}

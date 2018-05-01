package bohdan.hushcha.sushchak.suggestme.rest.models.News;

import com.google.gson.annotations.SerializedName;

public class NewsSource {

    @SerializedName("id")
    private String Id;

    @SerializedName("name")
    private String Name;

    @SerializedName("description")
    private String Description;

    @SerializedName("url")
    private String Url;

    @SerializedName("category")
    private String Category;

    @SerializedName("language")
    private String Language;

    @SerializedName("country")
    private String Country;

    public NewsSource(String id, String name, String description,
                      String url, String category, String language, String country) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.Url = url;
        this.Category = category;
        this.Language = language;
        this.Country = country;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}

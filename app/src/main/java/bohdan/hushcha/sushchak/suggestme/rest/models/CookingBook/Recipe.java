package bohdan.hushcha.sushchak.suggestme.rest.models.CookingBook;

import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("publisher")
    private String Publisher;

    @SerializedName("f2f_url")
    private String F2f_url;

    @SerializedName("title")
    private String Title;

    @SerializedName("source_url")
    private String SourceUrl;

    @SerializedName("recipe_id")
    private String RecipeId;

    @SerializedName("image_url")
    private String ImageUrl;

    @SerializedName("social_rank")
    private Double Rank;

    @SerializedName("publisher_url")
    private String PublisherUrl;

    public Recipe(String publisher, String f2f_url,
                  String title,
                  String sourceUrl,
                  String recipeId,
                  String imageUrl,
                  Double rank,
                  String publisherUrl) {
        this.Publisher = publisher;
        this.F2f_url = f2f_url;
        this.Title = title;
        this.SourceUrl = sourceUrl;
        this.RecipeId = recipeId;
        this.ImageUrl = imageUrl;
        this.Rank = rank;
        this.PublisherUrl = publisherUrl;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getF2f_url() {
        return F2f_url;
    }

    public void setF2f_url(String f2f_url) {
        F2f_url = f2f_url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSourceUrl() {
        return SourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        SourceUrl = sourceUrl;
    }

    public String getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(String recipeId) {
        RecipeId = recipeId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Double getRank() {
        return Rank;
    }

    public void setRank(Double rank) {
        Rank = rank;
    }

    public String getPublisherUrl() {
        return PublisherUrl;
    }

    public void setPublisherUrl(String publisherUrl) {
        PublisherUrl = publisherUrl;
    }
}

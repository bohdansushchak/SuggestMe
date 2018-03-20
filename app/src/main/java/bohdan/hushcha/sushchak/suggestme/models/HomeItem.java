package bohdan.hushcha.sushchak.suggestme.models;


public class HomeItem {

    private String Title;
    private String Description;
    private String ImageUrl;
    private String Category;

    public HomeItem(String title, String description, String imageUrl, String category) {
        Title = title;
        Description = description;
        ImageUrl = imageUrl;
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getCategory() {
        return Category;
    }
}

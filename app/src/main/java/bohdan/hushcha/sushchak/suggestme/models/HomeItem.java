package bohdan.hushcha.sushchak.suggestme.models;


public class HomeItem {

    private String Title;
    private String Description;
    private String Image;
    private String Category;

    public HomeItem(String title, String description, String image, String category) {
        Title = title;
        Description = description;
        Image = image;
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public String getCategory() {
        return Category;
    }
}

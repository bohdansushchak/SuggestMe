package bohdan.hushcha.sushchak.suggestme.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String CategoryName;
    private List<String> CategoryItems;


    public Category(String categoryName, List<String> categoryItems) {
        CategoryName = categoryName;
        CategoryItems = categoryItems;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public List<String> getCategoryItems() {
        return CategoryItems;
    }

    public void setCategoryItems(ArrayList<String> categoryItems) {
        CategoryItems = categoryItems;
    }
}

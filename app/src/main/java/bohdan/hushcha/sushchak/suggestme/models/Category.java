package bohdan.hushcha.sushchak.suggestme.models;



import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String CategoryName;
    private List<String> CategoryItems;
    private List<Class<? extends Fragment>> CateoryFragments;


    public Category(String categoryName, List<String> categoryItems) {
        this.CategoryName = categoryName;
        this.CategoryItems = categoryItems;
        //this.CateoryFragments = categoryFragments;
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

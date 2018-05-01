package bohdan.hushcha.sushchak.suggestme.rest.responces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.CookingBook.Recipe;

public class CookingBookResponce {

    @SerializedName("count")
    private Integer Count;

    @SerializedName("recipes")
    private List<Recipe> Recipes;

    public CookingBookResponce(Integer count,
                               List<Recipe> recipes) {
        this.Count = count;
        this.Recipes = recipes;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public List<Recipe> getRecipes() {
        return Recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        Recipes = recipes;
    }
}

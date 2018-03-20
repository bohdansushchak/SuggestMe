package bohdan.hushcha.sushchak.suggestme.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponce {

    @SerializedName("totalResults")
    private int TotalResults;

    @SerializedName("articles")
    private List<Article> Articles;

    public int getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(int totalResults) {
        TotalResults = totalResults;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}

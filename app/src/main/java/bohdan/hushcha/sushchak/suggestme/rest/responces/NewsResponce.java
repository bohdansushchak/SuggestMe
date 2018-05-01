package bohdan.hushcha.sushchak.suggestme.rest.responces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.News.Article;

public class NewsResponce {

    @SerializedName("totalResults")
    private int TotalResults;

    @SerializedName("articles")
    private List<Article> Articles;

    public NewsResponce(int totalResults, List<Article> articles) {
        this.TotalResults = totalResults;
        this.Articles = articles;
    }

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

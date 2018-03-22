package bohdan.hushcha.sushchak.suggestme.rest.models;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("author")
    private String Author;

    @SerializedName("title")
    private String Title;

    @SerializedName("description")
    private String Decription;

    @SerializedName("url")
    private String Url;

    @SerializedName("urlToImage")
    private String UrlToImage;

    @SerializedName("publishedAt")
    private String PublishedAt;

    public Article(String author, String title, String decription, String url, String urlToImage, String publishedAt) {
        Author = author;
        Title = title;
        Decription = decription;
        Url = url;
        UrlToImage = urlToImage;
        PublishedAt = publishedAt;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String decription) {
        Decription = decription;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrlToImage() {
        return UrlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        UrlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        PublishedAt = publishedAt;
    }
}

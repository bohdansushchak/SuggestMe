package bohdan.hushcha.sushchak.suggestme.rest.models.Cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movie {

    @SerializedName("vote_count")
    private String VoteCount;

    @SerializedName("id")
    private String Id;

    @SerializedName("video")
    private Boolean IsVideo;

    @SerializedName("vote_average")
    private String VoteAverange;

    @SerializedName("title")
    private String Title;

    @SerializedName("popularity")
    private Double Popularity;

    @SerializedName("poster_path")
    private String PosterPath;

    @SerializedName("original_language")
    private String OriginalLanguage;

    @SerializedName("original_title")
    private String OriginalTitle;

    @SerializedName("backdrop_path")
    private String BackgroundPath;

    @SerializedName("adult")
    private Boolean Adult;

    @SerializedName("overview")
    private String Overview;

    @SerializedName("release_date")
    private String ReleaseDate;

    @SerializedName("genre_ids")
    private List<Integer> IdGenres;

    public Movie(String voteCount, String id, Boolean isVideo,
                 String voteAverange, String title, Double popularity,
                 String posterPath, String originalLanguage, String originalTitle,
                 String backgroundPath, Boolean adult,
                 String overview, String releaseDate, List<Integer> idGenres) {
        VoteCount = voteCount;
        Id = id;
        IsVideo = isVideo;
        VoteAverange = voteAverange;
        Title = title;
        Popularity = popularity;
        PosterPath = posterPath;
        OriginalLanguage = originalLanguage;
        OriginalTitle = originalTitle;
        BackgroundPath = backgroundPath;
        Adult = adult;
        Overview = overview;
        ReleaseDate = releaseDate;
        IdGenres = idGenres;
    }

    public Movie(String voteCount, String id, Boolean isVideo, String voteAverange, String title,
                 Double popularity, String posterPath, String originalLanguage,
                 String originalTitle, String backgroundPath, Boolean adult,
                 String overview, String releaseDate) {

        this.VoteCount = voteCount;
        this.Id = id;
        this.IsVideo = isVideo;
        this.VoteAverange = voteAverange;
        this.Title = title;
        this.Popularity = popularity;
        this.PosterPath = posterPath;
        this.OriginalLanguage = originalLanguage;
        this.OriginalTitle = originalTitle;
        this.BackgroundPath = backgroundPath;
        this.Adult = adult;
        this.Overview = overview;
        this.ReleaseDate = releaseDate;
    }

    public List<Integer> getIdGenres() {
        return IdGenres;
    }

    public void setIdGenres(List<Integer> idGenres) {
        IdGenres = idGenres;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(String voteCount) {
        VoteCount = voteCount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Boolean getVideo() {
        return IsVideo;
    }

    public void setVideo(Boolean video) {
        IsVideo = video;
    }

    public String getVoteAverange() {
        return VoteAverange;
    }

    public void setVoteAverange(String voteAverange) {
        VoteAverange = voteAverange;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Double getPopularity() {
        return Popularity;
    }

    public void setPopularity(Double popularity) {
        Popularity = popularity;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return OriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        OriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        OriginalTitle = originalTitle;
    }

    public String getBackgroundPath() {
        return BackgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        BackgroundPath = backgroundPath;
    }

    public Boolean getAdult() {
        return Adult;
    }

    public void setAdult(Boolean adult) {
        Adult = adult;
    }
}


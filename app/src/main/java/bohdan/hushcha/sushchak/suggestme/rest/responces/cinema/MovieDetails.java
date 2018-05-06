package bohdan.hushcha.sushchak.suggestme.rest.responces.cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Company;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Genre;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.MovieCollection;

public class MovieDetails extends Movie {

    @SerializedName("belongs_to_collection")
    private MovieCollection BelongsToCollection;

    @SerializedName("budget")
    private String Budget;

    @SerializedName("genres")
    private List<Genre> Genres;

    @SerializedName("homepage")
    private String HomePage;

    @SerializedName("revenue")
    private String Revenue;

    @SerializedName("runtime")
    private String Runtime;

    @SerializedName("status")
    private String Status;

    @SerializedName("tagline")
    private String TagLine;

    @SerializedName("production_companies")
    private List<Company> Companies;

    public MovieDetails(String voteCount, String id, Boolean isVideo,
                        String voteAverange, String title, Double popularity,
                        String posterPath, String originalLanguage, String originalTitle,
                        String backgroundPath, Boolean adult, String overview, String releaseDate,
                        List<Integer> idGenres, MovieCollection belongsToCollection, String budget,
                        List<Genre> genres, String homePage, String revenue, String runtime,
                        String status, String title1, String tagLine, List<Company> companies) {

        super(voteCount, id, isVideo, voteAverange, title, popularity,
                posterPath, originalLanguage, originalTitle, backgroundPath,
                adult, overview, releaseDate, idGenres);

        this.BelongsToCollection = belongsToCollection;
        this.Budget = budget;
        this.Genres = genres;
        this.HomePage = homePage;
        this.Revenue = revenue;
        this.Runtime = runtime;
        this.Status = status;
        this.TagLine = tagLine;
        this.Companies = companies;
    }

    public MovieDetails(String voteCount, String id, Boolean isVideo,
                        String voteAverange, String title, String name, Double popularity,
                        String posterPath, String originalLanguage, String originalTitle,
                        String backgroundPath, Boolean adult, String overview, String releaseDate,
                        List<Integer> idGenres, MovieCollection belongsToCollection, String budget,
                        List<Genre> genres, String homePage, String revenue, String runtime,
                        String status, String title1, String tagLine, List<Company> companies) {

        super(voteCount, id, isVideo, voteAverange, title, name, popularity, posterPath,
                originalLanguage, originalTitle, backgroundPath, adult, overview, releaseDate, idGenres);

        this.BelongsToCollection = belongsToCollection;
        this.Budget = budget;
        this.Genres = genres;
        this.HomePage = homePage;
        this.Revenue = revenue;
        this.Runtime = runtime;
        this.Status = status;
        this.TagLine = tagLine;
        this.Companies = companies;
    }

    public MovieDetails(String voteCount, String id, Boolean isVideo, String voteAverange,
                        String title, Double popularity, String posterPath, String originalLanguage,
                        String originalTitle, String backgroundPath, Boolean adult, String overview,
                        String releaseDate, MovieCollection belongsToCollection, String budget,
                        List<Genre> genres, String homePage, String revenue, String runtime,
                        String status, String title1, String tagLine, List<Company> companies) {

        super(voteCount, id, isVideo, voteAverange, title, popularity, posterPath,
                originalLanguage, originalTitle, backgroundPath, adult, overview, releaseDate);

        this.BelongsToCollection = belongsToCollection;
        this.Budget = budget;
        this.Genres = genres;
        this.HomePage = homePage;
        this.Revenue = revenue;
        this.Runtime = runtime;
        this.Status = status;
        this.TagLine = tagLine;
        this.Companies = companies;
    }

    public String getTagLine() {
        return TagLine;
    }

    public void setTagLine(String tagLine) {
        TagLine = tagLine;
    }

    public List<Genre> getGenres() {
        return Genres;
    }

    public void setGenres(List<Genre> genres) {
        Genres = genres;
    }

    public MovieCollection getBelongsToCollection() {
        return BelongsToCollection;
    }

    public void setBelongsToCollection(MovieCollection belongsToCollection) {
        BelongsToCollection = belongsToCollection;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getHomePage() {
        return HomePage;
    }

    public void setHomePage(String homePage) {
        HomePage = homePage;
    }

    public String getRevenue() {
        return Revenue;
    }

    public void setRevenue(String revenue) {
        Revenue = revenue;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Company> getCompanies() {
        return Companies;
    }

    public void setCompanies(List<Company> companies) {
        Companies = companies;
    }
}

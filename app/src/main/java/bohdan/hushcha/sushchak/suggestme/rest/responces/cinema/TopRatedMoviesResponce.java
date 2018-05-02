package bohdan.hushcha.sushchak.suggestme.rest.responces.cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;

public class TopRatedMoviesResponce {

    @SerializedName("page")
    private Integer Page;

    @SerializedName("total_results")
    private String TotalResults;

    @SerializedName("total_pages")
    private Integer TotalPages;

    @SerializedName("results")
    private List<Movie> Movies;

    public TopRatedMoviesResponce(Integer page, String totalResults, Integer totalPages, List<Movie> movies) {
        Page = page;
        TotalResults = totalResults;
        TotalPages = totalPages;
        Movies = movies;
    }

    public Integer getPage() {
        return Page;
    }

    public void setPage(Integer page) {
        Page = page;
    }

    public String getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(String totalResults) {
        TotalResults = totalResults;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        TotalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return Movies;
    }

    public void setMovies(List<Movie> movies) {
        Movies = movies;
    }
}

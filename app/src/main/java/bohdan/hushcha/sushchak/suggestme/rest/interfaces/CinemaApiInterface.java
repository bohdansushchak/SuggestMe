package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.GenreListResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.TopRatedMoviesResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CinemaApiInterface {

    @GET("discover/movie?sort_by=popularity.desc")
    Call<TopRatedMoviesResponce> GetTopRatedMovies(@Query("api_key") String ApiKey,
                                                   @Query("page") Integer Page);

    @GET("genre/movie/list?language=en-US")
    Call<GenreListResponce> GetGenreList(@Query("api_key") String ApiKey);
}

package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.GenreListResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.MovieDetails;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.TopRatedMoviesResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CinemaApiInterface {

    @GET("discover/movie?sort_by=vote_average.desc")
    Call<TopRatedMoviesResponce> GetTopRatedMovies(@Query("api_key") String ApiKey,
                                                   @Query("page") Integer Page);

    @GET("discover/tv?sort_by=vote_average.desc")
    Call<TopRatedMoviesResponce> GetTopRatedTVShows(@Query("api_key") String ApiKey,
                                                    @Query("page") Integer Page);


    @GET("discover/movie?sort_by=popularity.desc")
    Call<TopRatedMoviesResponce> GetPopularMovies(@Query("api_key") String ApiKey,
                                                  @Query("page") Integer Page);

    @GET("discover/tv?sort_by=popularity.desc")
    Call<TopRatedMoviesResponce> GetPopularTVShows(@Query("api_key") String ApiKey,
                                                   @Query("page") Integer Page);

    @GET("search/movie")
    Call<TopRatedMoviesResponce> SearchMovie(@Query("api_key") String ApiKey,
                                             @Query("query") String Query,
                                             @Query("page") Integer Page);

    @GET("movie/{movie_id}")
    Call<MovieDetails> GetDetailsById(@Path("movie_id") String MovieId,
                                      @Query("api_key") String ApiKey);


    @GET("genre/movie/list?language=en-US")
    Call<GenreListResponce> GetGenreList(@Query("api_key") String ApiKey);
}

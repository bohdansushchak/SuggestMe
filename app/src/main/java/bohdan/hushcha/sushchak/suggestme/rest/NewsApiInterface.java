package bohdan.hushcha.sushchak.suggestme.rest;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {

    @GET("top-headlines")
    Call<NewsResponce> TopHeadlines(@Query("apiKey") String apiKey, @Query("country") String Country, @Query("category") String category);
}

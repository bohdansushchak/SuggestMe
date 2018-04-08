package bohdan.hushcha.sushchak.suggestme.rest.interfaces;


import bohdan.hushcha.sushchak.suggestme.rest.responces.NewsResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.SourcesResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {

    @GET("top-headlines")
    Call<NewsResponce> TopHeadlines(@Query("apiKey") String apiKey,
                                    @Query("country") String country,
                                    @Query("category") String category);

    @GET("top-headlines")
    Call<NewsResponce> TopHeadlinesByCountry(@Query("apiKey") String apiKey,
                                             @Query("country") String country);

    @GET("top-headlines")
    Call<NewsResponce> TopHeadlinesByCategory(@Query("apiKey") String apiKey,
                                              @Query("category") String category);

    @GET("everything")
    Call<NewsResponce> GetEverything(@Query("apiKey") String apiKey, @Query("q") String query);

    @GET("everything")
    Call<NewsResponce> GetEverything(@Query("apiKey") String apiKey,
                                     @Query("from") String from,
                                     @Query("to") String to,
                                     @Query("sources") String source);

    @GET("everything")
    Call<NewsResponce> GetEverything(@Query("apiKey") String apiKey,
                                     @Query("from") String from,
                                     @Query("sources") String sources);

    @GET("everything")
    Call<NewsResponce> GetEverythingDateTo(@Query("apiKey") String apiKey,
                                     @Query("to") String to,
                                     @Query("sources") String sources);
    @GET("sources")
    Call<SourcesResponce> GetAllSources(@Query("apiKey") String apiKey);

    @GET("sources")
    Call<SourcesResponce> GetSourcesByCountry(@Query("apiKey") String apiKey,
                                     @Query("country") String country);

    @GET("sources")
    Call<SourcesResponce> GetSourcesByCriterias(@Query("apiKey") String apiKey,
                                     @Query("language") String language,
                                     @Query("country") String country);
}

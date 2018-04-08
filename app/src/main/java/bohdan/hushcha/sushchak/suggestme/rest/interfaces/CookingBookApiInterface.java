package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import bohdan.hushcha.sushchak.suggestme.rest.responces.CookingBookResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CookingBookApiInterface {

    @GET("search")
    Call<CookingBookResponce> TopRecipes(@Query("key") String apiKey,
                                         @Query("sort") String sort,
                                         @Query("page") Integer page);
}

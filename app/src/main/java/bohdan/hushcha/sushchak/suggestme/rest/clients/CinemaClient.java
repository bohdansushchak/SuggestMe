package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CinemaClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://www.themoviedb.org/documentation/api";

    public static final String API_KEY = "fff7dd669de09627d571011fd6d524fc";

    private CinemaClient(){}

    public static Retrofit getClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ClientUtils.getUnsafeOkHttpClient().build())
                    .build();
        }
        return retrofit;
    }
}

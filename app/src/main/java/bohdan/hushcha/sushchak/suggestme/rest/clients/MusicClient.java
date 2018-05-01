package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    public static final String API_KEY = "fff7dd669de09627d571011fd6d524fc";

    public static final String SHARED_SECRET = "95e629f504d028f3cd4b34e633fcf51f";

    private MusicClient(){}

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

package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CookingBookClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "http://food2fork.com/api/search/";

    public static final String API_KEY = "c8f3190172c11ac509b3e62b9f4626b6";

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

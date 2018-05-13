package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * News client for work with "news api"
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class NewsClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static final String API_KEY = "e6b3ff96de8545beb079944d729c2e5a";

    private NewsClient() {
    }

    /**
     * Method to initialise news client
     *
     * @return cinema client
     */
    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ClientUtils.getUnsafeOkHttpClient().build())
                    .build();
        }
        return retrofit;
    }
}

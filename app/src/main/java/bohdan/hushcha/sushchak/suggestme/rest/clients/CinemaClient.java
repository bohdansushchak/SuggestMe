package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Cinema client for work with "the movie db"
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class CinemaClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static final String API_KEY = "b1bb6f197a83c987d3a0029cf710607e";

    private CinemaClient() {
    }

    /**
     * Method to initialise cinema client
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

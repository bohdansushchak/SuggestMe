package bohdan.hushcha.sushchak.suggestme.rest.clients;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Crypto client for work with "coin market cap"
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class CryptoCurrencyClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://api.coinmarketcap.com/v1/";

    private CryptoCurrencyClient() {
    }

    /**
     * Method to initialise crypto currency client
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

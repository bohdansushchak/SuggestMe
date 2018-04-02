package bohdan.hushcha.sushchak.suggestme.rest.clients;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import bohdan.hushcha.sushchak.suggestme.utils.ClientUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://www.metaweather.com/api/";

    private WeatherClient() { }

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

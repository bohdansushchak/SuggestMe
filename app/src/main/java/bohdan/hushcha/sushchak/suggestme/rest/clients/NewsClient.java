package bohdan.hushcha.sushchak.suggestme.rest.clients;

import android.content.Context;

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

public class NewsClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static final String API_KEY = "e6b3ff96de8545beb079944d729c2e5a";

    private NewsClient(){}

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

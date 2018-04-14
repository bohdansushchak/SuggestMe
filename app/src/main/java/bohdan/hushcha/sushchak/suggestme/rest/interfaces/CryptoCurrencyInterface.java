package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.CryptoCurrency;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoCurrencyInterface {

    @GET("ticker/")
    Call<List<CryptoCurrency>> TopCryptoCurrency();
}

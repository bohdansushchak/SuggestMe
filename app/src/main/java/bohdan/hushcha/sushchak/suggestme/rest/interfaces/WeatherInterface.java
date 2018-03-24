package bohdan.hushcha.sushchak.suggestme.rest.interfaces;


import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.rest.responces.WeatherResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherInterface {

    @GET("location/{woeid}")
    Call<WeatherResponce> GetWeather(@Path("woeid") String Woeid);

    @GET("location/{woeid}/{date}")
    Call<List<ConsolidatedWeather>> GetWeatherByDate(@Path("woeid") String Woeid,
                                                     @Path("date") String Date);

}

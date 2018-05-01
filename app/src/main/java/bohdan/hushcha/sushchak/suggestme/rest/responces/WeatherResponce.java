package bohdan.hushcha.sushchak.suggestme.rest.responces;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.ConsolidatedWeather;

public class WeatherResponce {

    @SerializedName("consolidated_weather")
    private List<ConsolidatedWeather> consolidatedWeather;

    @SerializedName("time")
    private Date Time;

    @SerializedName("timezone_name")
    private String TimeZone;

}

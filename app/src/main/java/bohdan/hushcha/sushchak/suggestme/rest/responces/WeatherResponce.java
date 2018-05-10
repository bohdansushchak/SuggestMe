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

    public WeatherResponce(List<ConsolidatedWeather> consolidatedWeather, Date time, String timeZone) {
        this.consolidatedWeather = consolidatedWeather;
        this.Time = time;
        this.TimeZone = timeZone;
    }

    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    public void setConsolidatedWeather(List<ConsolidatedWeather> consolidatedWeather) {
        this.consolidatedWeather = consolidatedWeather;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }
}

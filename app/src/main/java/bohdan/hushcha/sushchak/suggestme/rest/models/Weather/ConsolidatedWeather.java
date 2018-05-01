package bohdan.hushcha.sushchak.suggestme.rest.models.Weather;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConsolidatedWeather {

    @SerializedName("id")
    private String Id;

    @SerializedName("weather_state_name")
    private String WeatherState;

    @SerializedName("weather_state_abbr")
    private String WeatherStateAbbr;

    @SerializedName("wind_direction_compass")
    private String WindDirectionCompass;

    @SerializedName("created")
    private Date CreatedDate;

    @SerializedName("applicable_date")
    private Date ApplicableDate;

    @SerializedName("min_temp")
    private Double MinTemp;

    @SerializedName("max_temp")
    private Double MaxTemp;

    @SerializedName("the_temp")
    private Double TheTemp;

    @SerializedName("wind_speed")
    private Double WindSpeed;

    @SerializedName("wind_direction")
    private Double WindDirection;

    @SerializedName("air_pressure")
    private Double AirPresure;

    @SerializedName("humidity")
    private Double Huminidy;

    @SerializedName("visibility")
    private Double Visibility;

    @SerializedName("predictability")
    private Double Predictability;


    public ConsolidatedWeather(String id, String weatherState, String weatherStateAbbr,
                               String windDirectionCompass, Date createdDate,
                               Date applicableDate, Double minTemp, Double maxTemp,
                               Double theTemp, Double windSpeed, Double windDirection,
                               Double airPresure, Double huminidy, Double visibility,
                               Double predictability) {
        this.Id = id;
        this.WeatherState = weatherState;
        this.WeatherStateAbbr = weatherStateAbbr;
        this.WindDirectionCompass = windDirectionCompass;
        this.CreatedDate = createdDate;
        this.ApplicableDate = applicableDate;
        this.MinTemp = minTemp;
        this.MaxTemp = maxTemp;
        this.TheTemp = theTemp;
        this.WindSpeed = windSpeed;
        this.WindDirection = windDirection;
        this.AirPresure = airPresure;
        this.Huminidy = huminidy;
        this.Visibility = visibility;
        this.Predictability = predictability;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getWeatherState() {
        return WeatherState;
    }

    public void setWeatherState(String weatherState) {
        WeatherState = weatherState;
    }

    public String getWeatherStateAbbr() {
        return WeatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        WeatherStateAbbr = weatherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return WindDirectionCompass;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        WindDirectionCompass = windDirectionCompass;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getApplicableDate() {
        return ApplicableDate;
    }

    public void setApplicableDate(Date applicableDate) {
        ApplicableDate = applicableDate;
    }

    public Double getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(Double minTemp) {
        MinTemp = minTemp;
    }

    public Double getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        MaxTemp = maxTemp;
    }

    public Double getTheTemp() {
        return TheTemp;
    }

    public void setTheTemp(Double theTemp) {
        TheTemp = theTemp;
    }

    public Double getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        WindSpeed = windSpeed;
    }

    public Double getWindDirection() {
        return WindDirection;
    }

    public void setWindDirection(Double windDirection) {
        WindDirection = windDirection;
    }

    public Double getAirPresure() {
        return AirPresure;
    }

    public void setAirPresure(Double airPresure) {
        AirPresure = airPresure;
    }

    public Double getHuminidy() {
        return Huminidy;
    }

    public void setHuminidy(Double huminidy) {
        Huminidy = huminidy;
    }

    public Double getVisibility() {
        return Visibility;
    }

    public void setVisibility(Double visibility) {
        Visibility = visibility;
    }

    public Double getPredictability() {
        return Predictability;
    }

    public void setPredictability(Double predictability) {
        Predictability = predictability;
    }
}

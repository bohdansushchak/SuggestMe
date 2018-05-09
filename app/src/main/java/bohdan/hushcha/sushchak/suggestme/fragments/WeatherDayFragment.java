package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.clients.WeatherClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.WeatherApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.Location;
import bohdan.hushcha.sushchak.suggestme.utils.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDayFragment extends BaseFragment {

    public final String TAG = "WeatherDayFragment";

    private final String KEY_CITY = "weather_city";
    private final String KEY_WOEID = "weather_woeid";

    private SharedPreferences sharedPreferences;

    @BindView(R.id.edLocation)
    EditText edLocation;

    @BindView(R.id.ivIconWeather)
    ImageView ivIconWeather;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvTemp)
    TextView tvTemp;

    @BindView(R.id.tvMaxTemp)
    TextView tvMaxTemp;

    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;

    @BindView(R.id.tvWindSpeed)
    TextView tvWindSpeed;

    @BindView(R.id.tvAirPressure)
    TextView tvAirPressure;

    @BindView(R.id.tvHumidity)
    TextView tvHumidity;

    @BindView(R.id.tvPredictability)
    TextView tvPredictability;

    @BindView(R.id.tvVisibility)
    TextView tvVisibility;

    private static Date WeatherDate;
    private String Woeid;
    private String Location;

    private WeatherApiInterface weatherClient;

    private List<ConsolidatedWeather> weatherList;

    public WeatherDayFragment() {
        this.weatherList = new ArrayList<>();
        this.weatherClient = WeatherClient.getClient().create(WeatherApiInterface.class);

    }

    public static WeatherDayFragment getInstance() {
        WeatherDayFragment fragment = new WeatherDayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Function to get city and search in api then
     * get cityId to get available weather by date
     *
     * @param view clicked item
     */
    @OnClick(R.id.ivSearchLocation)
    public void SearchCity(View view) {
        String query = edLocation.getText().toString().trim();

        Call<List<Location>> call = weatherClient.GetLocation(query);

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {

                if (response.body() != null && response.body().size() > 0)
                    ActionChooseCity(response.body());
                else
                    DialogAlert(R.string.dialog_title_not_found_city, R.string.dialog_message_not_found_city);
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });
    }

    /**
     * Method to choose city
     */
    private void ActionChooseCity(List<Location> locationList) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.dialog_title_choose_city));

        final List<String> cityNames = new ArrayList<>();
        final List<String> cityIds = new ArrayList<>();

        for (Location location : locationList) {
            cityNames.add(location.getTitle());
            cityIds.add(location.getWoeid());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(cityNames);

        dialog.setNegativeButton(getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Woeid = cityIds.get(i);
                Location = cityNames.get(i);
                edLocation.setText(Location);
                GetWeather();
            }
        });
        dialog.show();
    }

    /**
     * Method to get weather from api
     */
    private void GetWeather() {

        if (WeatherDate == null)
            WeatherDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String date = formatter.format(WeatherDate);

        weatherClient.GetWeatherByDate(Woeid, date).enqueue(new Callback<List<ConsolidatedWeather>>() {
            @Override
            public void onResponse(Call<List<ConsolidatedWeather>> call, Response<List<ConsolidatedWeather>> response) {

                if (response.body() != null) {

                    weatherList = response.body();

                    ConsolidatedWeather weather = weatherList.get(0);
                    InitIcons(weather);

                    sharedPreferences.edit().putString(KEY_CITY, Location).apply();
                    sharedPreferences.edit().putString(KEY_WOEID, Woeid).apply();
                }
            }

            @Override
            public void onFailure(Call<List<ConsolidatedWeather>> call, Throwable t) {

            }
        });
    }

    /**
     * Method to view weather date on screen
     *
     * @param weather
     */
    private void InitIcons(ConsolidatedWeather weather) {

        Long avgTemp = Math.round(weather.getTheTemp());
        Long maxTemp = Math.round(weather.getMaxTemp());
        Long minTemp = Math.round(weather.getMinTemp());

        tvTemp.setText(Html.fromHtml(avgTemp.toString() + "&#8451;"));
        tvMaxTemp.setText(Html.fromHtml(maxTemp.toString() + "&#8451;"));
        tvMinTemp.setText(Html.fromHtml(minTemp.toString() + "&#8451;"));
        tvVisibility.setText(String.valueOf(Math.round(weather.getVisibility())));
        tvWindSpeed.setText(String.valueOf(weather.getWindSpeed()));
        tvPredictability.setText(String.valueOf(weather.getPredictability()));
        tvAirPressure.setText(String.valueOf(weather.getAirPresure()));
        tvHumidity.setText(String.valueOf(weather.getHuminidy()));

        tvDate.setText(new SimpleDateFormat("MM.dd.yyyy").format(WeatherDate));

        int iconId = ImageUtils.getWeatherImageId(weather.getWeatherStateAbbr());
        Drawable drawable = getActivity().getDrawable(iconId);

        ivIconWeather.setImageDrawable(drawable);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_day, container, false);

        ButterKnife.bind(this, view);

        this.sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (sharedPreferences.contains(KEY_CITY) && sharedPreferences.contains(KEY_WOEID)) {
            Woeid = sharedPreferences.getString(KEY_WOEID, null);
            Location = sharedPreferences.getString(KEY_CITY, null);

            edLocation.setText(Location);

            GetWeather();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

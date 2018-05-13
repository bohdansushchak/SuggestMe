package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.DailyWeatherAdapter;
import bohdan.hushcha.sushchak.suggestme.rest.clients.WeatherClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.WeatherApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.Location;
import bohdan.hushcha.sushchak.suggestme.rest.responces.WeatherResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment to view daily weather
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class WeatherDailyFragment extends BaseFragment {

    final String TAG = " WeatherDailyFragment";

    private final String KEY_CITY = "weather_city_for_week";
    private final String KEY_WOEID = "weather_woeid_for_week";

    @BindView(R.id.rvDailyWeatherList)
    RecyclerView recyclerView;

    @BindView(R.id.edLocation)
    EditText edLocation;

    private SharedPreferences sharedPreferences;
    private ArrayList<ConsolidatedWeather> items;
    private WeatherApiInterface weatherClient;

    private String Woeid;
    private String Location;

    public WeatherDailyFragment() {
        weatherClient = WeatherClient.getClient().create(WeatherApiInterface.class);
    }

    public static WeatherDailyFragment getInstance() {
        WeatherDailyFragment fragment = new WeatherDailyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_week, container, false);
        ButterKnife.bind(this, view);

        this.sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (sharedPreferences.contains(KEY_CITY) && sharedPreferences.contains(KEY_WOEID)) {
            Woeid = sharedPreferences.getString(KEY_WOEID, null);
            Location = sharedPreferences.getString(KEY_CITY, null);

            edLocation.setText(Location);

            Init();
        }

        return view;
    }

    /**
     * Method to initialize all views on fragment
     */
    private void Init() {
        items = new ArrayList<>();

        final DailyWeatherAdapter adapter = new DailyWeatherAdapter(getContext(), items);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Call<WeatherResponce> call = weatherClient.GetWeather(Woeid);

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<WeatherResponce>() {
            @Override
            public void onResponse(Call<WeatherResponce> call, Response<WeatherResponce> response) {
                if (response.body() != null) {

                    List<ConsolidatedWeather> list = response.body().getConsolidatedWeather();

                    items.addAll(list);
                    adapter.notifyDataSetChanged();

                    sharedPreferences.edit().putString(KEY_CITY, Location).apply();
                    sharedPreferences.edit().putString(KEY_WOEID, Woeid).apply();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponce> call, Throwable t) {

            }
        });
    }

    /**
     * Method call when user clicked button.
     * If user click search button, start call to api and view result
     *
     * @param view clicked items in fragment
     */
    @OnClick(R.id.ivSearchLocation)
    void onClick(View view) {
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
                Init();
            }
        });
        dialog.show();
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

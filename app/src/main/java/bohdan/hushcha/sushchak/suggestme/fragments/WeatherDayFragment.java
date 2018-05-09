package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.rest.clients.WeatherClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.WeatherApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.utils.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDayFragment extends Fragment {

    public final String TAG = "WeatherDayFragment";

    private static final String DATE_PARAM = "date";

    private InteractionListener mListener;

    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.ivIconWeather)
    ImageView ivIconWeather;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTemp)
    TextView tvTemp;

    private static Date WeatherDate;

    private WeatherApiInterface weatherClient;

    private static List<ConsolidatedWeather> weatherList;

    public WeatherDayFragment() {
        this.weatherList = new ArrayList<>();
        weatherClient = WeatherClient.getClient().create(WeatherApiInterface.class);
    }

    public static WeatherDayFragment getInstance() {
        WeatherDayFragment fragment = new WeatherDayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (WeatherDate == null)
            WeatherDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String date = formatter.format(WeatherDate);

        weatherClient.GetWeatherByDate("523920", date).enqueue(new Callback<List<ConsolidatedWeather>>() {
            @Override
            public void onResponse(Call<List<ConsolidatedWeather>> call, Response<List<ConsolidatedWeather>> response) {
                weatherList = response.body();

                ConsolidatedWeather weather = weatherList.get(0);
                InitIcons(weather);
            }

            @Override
            public void onFailure(Call<List<ConsolidatedWeather>> call, Throwable t) {

            }
        });
    }

    private void InitIcons(ConsolidatedWeather weather) {

        Long temp = Math.round(weather.getTheTemp());

        int iconId = ImageUtils.getWeatherImageId(weather.getWeatherStateAbbr());

        Integer id = iconId;

        Log.d(TAG, weather.getTheTemp().toString());
        Log.d(TAG, weather.getMinTemp().toString());
        Log.d(TAG, weather.getMaxTemp().toString());
        Log.d(TAG, id.toString());

        Spanned tempStr = Html.fromHtml(temp.toString() + "&#8451;");

        tvTemp.setText(tempStr);

        //ivIconWeather.setImageResource(getWeatherIconId(weather.getWeatherStateAbbr(), false));

        Drawable drawable = getActivity().getDrawable(iconId);

        ivIconWeather.setImageDrawable(drawable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_day, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

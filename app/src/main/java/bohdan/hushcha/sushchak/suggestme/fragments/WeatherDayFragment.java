package bohdan.hushcha.sushchak.suggestme.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import bohdan.hushcha.sushchak.suggestme.rest.clients.WeatherClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.WeatherInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.ConsolidatedWeather;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDayFragment extends Fragment {

    public final String TAG = "WeatherDayFragment";

    private static final String DATE_PARAM = "date";

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.ivIconWeather) ImageView ivIconWeather;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.tvTemp) TextView tvTemp;

    private static Date WeatherDate;

    private WeatherInterface weatherClient;

    private static List<ConsolidatedWeather> weatherList;

    public WeatherDayFragment() {
        this.weatherList = new ArrayList<>();

        weatherClient = WeatherClient.getClient().create(WeatherInterface.class);
    }

    public static WeatherDayFragment newInstance(Date date) {
        WeatherDayFragment fragment = new WeatherDayFragment();
        //weatherList = weathers;

        WeatherDate = date;
        /*
        Bundle args = new Bundle();
        args.putString(DATE_PARAM, date.toString());
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (WeatherDate != null)
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

    @SuppressLint("SetTextI18n")
    private void InitIcons(ConsolidatedWeather weather) {

         Long temp = Math.round(weather.getTheTemp());

        int iconid = getIconId(weather.getWeatherStateAbbr(), false);

        Integer id = iconid;

        Log.d(TAG, weather.getTheTemp().toString());
        Log.d(TAG, weather.getMinTemp().toString());
        Log.d(TAG, weather.getMaxTemp().toString());
        Log.d(TAG, id.toString());


        tvTemp.setText(temp.toString());

        //ivIconWeather.setImageResource(getIconId(weather.getWeatherStateAbbr(), false));

        Drawable drawable =  getActivity().getDrawable(iconid);

        ivIconWeather.setImageDrawable(drawable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_day, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public int getIconId(String abbreviation, boolean isPreview) {
        if (!isPreview)
            switch (abbreviation) {
                case "sn":
                    return R.drawable.weather_sn;
                case "sl":
                    return R.drawable.weather_sl;
                case "h":
                    return R.drawable.weather_h;
                case "t":
                    return R.drawable.weather_t;
                case "hr":
                    return R.drawable.weather_hr;
                case "lr":
                    return R.drawable.weather_lr;
                case "s":
                    return R.drawable.weather_s;
                case "hc":
                    return R.drawable.weather_hc;
                case "lc":
                    return R.drawable.weather_lc;
                case "c":
                    return R.drawable.weather_c;
                default:
                    return android.R.drawable.alert_dark_frame;
            }
        else
            switch (abbreviation) {
                case "sn":
                    return R.drawable.weather_sn_64;
                case "sl":
                    return R.drawable.weather_sl_64;
                case "h":
                    return R.drawable.weather_h_64;
                case "t":
                    return R.drawable.weather_t_64;
                case "hr":
                    return R.drawable.weather_hr_64;
                case "lr":
                    return R.drawable.weather_lr_64;
                case "s":
                    return R.drawable.weather_s_64;
                case "hc":
                    return R.drawable.weather_hc_64;
                case "lc":
                    return R.drawable.weather_lc_64;
                case "c":
                    return R.drawable.weather_c_64;
                default:
                    return android.R.drawable.alert_dark_frame;
            }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

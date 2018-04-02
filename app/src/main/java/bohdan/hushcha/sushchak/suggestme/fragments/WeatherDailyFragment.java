package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.DailyWeatherAdapter;
import bohdan.hushcha.sushchak.suggestme.rest.clients.WeatherClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.WeatherInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.ConsolidatedWeather;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherDailyFragment extends BaseMyFragment {

    final String TAG = " WeatherDailyFragment";

    @BindView(R.id.rvDailyWeatherList) RecyclerView recyclerView;

    ArrayList<ConsolidatedWeather> items;

    //private OnFragmentInteractionListener mListener;

    public WeatherDailyFragment() {
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
        Init();
        return view;
    }


    private void Init(){
        items = new ArrayList<>();

        final DailyWeatherAdapter adapter = new DailyWeatherAdapter(getContext(), items);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        WeatherInterface weatherClient = WeatherClient.getClient().create(WeatherInterface.class);

        Call<List<ConsolidatedWeather>> call = weatherClient.GetWeatherByDate("523920", "2018/03/25");
        call.enqueue(new Callback<List<ConsolidatedWeather>>() {
            @Override
            public void onResponse(Call<List<ConsolidatedWeather>> call, Response<List<ConsolidatedWeather>> response) {
                List<ConsolidatedWeather> list = response.body();

                for (ConsolidatedWeather weather : list){
                    items.add(weather);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ConsolidatedWeather>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}

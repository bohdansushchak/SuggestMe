package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.models.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.utils.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>  {

    private List<ConsolidatedWeather> weatherList;
    private Context context;

    public DailyWeatherAdapter(Context context, List<ConsolidatedWeather> weatherList) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @Override
    public DailyWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_weather_week, parent, false);
       return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(DailyWeatherAdapter.ViewHolder holder, int position) {
        ConsolidatedWeather weather = weatherList.get(position);

        holder.ivIcon.setImageResource(ImageUtils.getWeatherIconId(weather.getWeatherStateAbbr()));

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        //LocalDate date = LocalDate.parse(weather.getApplicableDate(), formatter);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");

        holder.tvDay.setText(weather.getCreatedDate());

        Long temp = Math.round(weather.getTheTemp());
        holder.tvTemp.setText(temp.toString());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItemBackground) ImageView ivBackground;
        @BindView(R.id.tvDay) TextView tvDay;
        @BindView(R.id.tvTemp) TextView tvTemp;
        @BindView(R.id.ivWeatherIcon) ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

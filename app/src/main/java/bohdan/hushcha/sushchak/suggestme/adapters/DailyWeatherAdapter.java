package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.models.Weather.ConsolidatedWeather;
import bohdan.hushcha.sushchak.suggestme.utils.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to view weather list in recycler view.
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {

    private List<ConsolidatedWeather> weatherList;
    private Context context;

    /**
     * @param context     context to access to base functions activity
     * @param weatherList list weather
     */
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

    @Override
    public void onBindViewHolder(DailyWeatherAdapter.ViewHolder holder, int position) {
        ConsolidatedWeather weather = weatherList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");

        Long temp = Math.round(weather.getTheTemp());

        holder.tvDay.setText(formatter.format(weather.getApplicableDate()));
        holder.tvTemp.setText(Html.fromHtml(temp.toString() + context.getString(R.string.SymbolDegreesAndCelsius)));
        holder.ivIcon.setImageResource(ImageUtils.getWeatherIconId(weather.getWeatherStateAbbr()));
        holder.tvDate.setText(new SimpleDateFormat("MM.dd.yyyy").format(weather.getApplicableDate()));
        holder.tvAirPressure.setText(String.format("%.2f", weather.getAirPresure()));
        holder.tvWindSpeed.setText(String.format("%.2f", weather.getWindSpeed()));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    /**
     * Class for hold views ids
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItemBackground)
        ImageView ivBackground;

        @BindView(R.id.tvDay)
        TextView tvDay;

        @BindView(R.id.tvTemp)
        TextView tvTemp;

        @BindView(R.id.ivWeatherIcon)
        ImageView ivIcon;

        @BindView(R.id.tvDate)
        TextView tvDate;

        @BindView(R.id.tvWindSpeed)
        TextView tvWindSpeed;

        @BindView(R.id.tvAirPressure)
        TextView tvAirPressure;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

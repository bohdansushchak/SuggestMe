package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

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

    @Override
    public void onBindViewHolder(DailyWeatherAdapter.ViewHolder holder, int position) {
        ConsolidatedWeather weather = weatherList.get(position);
/*
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        Date date = new Date();
        try {

            date =  formatter.parse(weather.getCreatedDate().split("[.]")[0]);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        DateFormatSymbols dfs = new DateFormatSymbols();
*/

        SimpleDateFormat formatter = new SimpleDateFormat("EEEEE");

        Long temp = Math.round(weather.getTheTemp());
        Spanned tempStr = Html.fromHtml(temp.toString() + context.getString(R.string.SymbolDegreesAndCelsius));

        holder.tvDay.setText(formatter.format(weather.getCreatedDate()));
        holder.tvTemp.setText(tempStr);
        holder.ivIcon.setImageResource(ImageUtils.getWeatherIconId(weather.getWeatherStateAbbr()));
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

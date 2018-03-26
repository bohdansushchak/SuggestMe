package bohdan.hushcha.sushchak.suggestme.utils;

import bohdan.hushcha.sushchak.suggestme.R;

public class ImageUtils {

    public static int getWeatherImageId(String abbreviation) {
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
    }

    public static int getWeatherIconId(String abbreviation) {
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

}

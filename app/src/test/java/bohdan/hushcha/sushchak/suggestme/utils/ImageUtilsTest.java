package bohdan.hushcha.sushchak.suggestme.utils;

import org.junit.Test;

import bohdan.hushcha.sushchak.suggestme.R;

import static org.junit.Assert.*;

public class ImageUtilsTest {

    @Test
    public void getWeatherImageIdTest(){
        int expected = R.drawable.weather_sn;
        int actual = ImageUtils.getWeatherIconId("sn");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_sl;
        actual = ImageUtils.getWeatherIconId("sl");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_h;
        actual = ImageUtils.getWeatherIconId("h");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_t;
        actual = ImageUtils.getWeatherIconId("t");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_hr;
        actual = ImageUtils.getWeatherIconId("hr");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_lr;
        actual = ImageUtils.getWeatherIconId("lr");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_s;
        actual = ImageUtils.getWeatherIconId("s");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_hc;
        actual = ImageUtils.getWeatherIconId("hc");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_lc;
        actual = ImageUtils.getWeatherIconId("lc");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_c;
        actual = ImageUtils.getWeatherIconId("c");
        assertEquals(expected, actual,1 );

        expected = android.R.drawable.alert_dark_frame;
        actual = ImageUtils.getWeatherIconId("erf");
        assertEquals(expected, actual,1 );

    }

    @Test
    public void getWeatherIconIdTest(){
        int expected = R.drawable.weather_sn_64;
        int actual = ImageUtils.getWeatherIconId("sn");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_sl_64;
        actual = ImageUtils.getWeatherIconId("sl");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_h_64;
        actual = ImageUtils.getWeatherIconId("h");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_t_64;
        actual = ImageUtils.getWeatherIconId("t");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_hr_64;
        actual = ImageUtils.getWeatherIconId("hr");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_lr_64;
        actual = ImageUtils.getWeatherIconId("lr");
        assertEquals(expected, actual, 1);

        expected = R.drawable.weather_s_64;
        actual = ImageUtils.getWeatherIconId("s");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_hc_64;
        actual = ImageUtils.getWeatherIconId("hc");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_lc_64;
        actual = ImageUtils.getWeatherIconId("lc");
        assertEquals(expected, actual,1 );

        expected = R.drawable.weather_c_64;
        actual = ImageUtils.getWeatherIconId("c");
        assertEquals(expected, actual,1 );

        expected = android.R.drawable.alert_dark_frame;
        actual = ImageUtils.getWeatherIconId("erf");
        assertEquals(expected, actual,1 );

    }

}
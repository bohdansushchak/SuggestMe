package bohdan.hushcha.sushchak.suggestme.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import bohdan.hushcha.sushchak.suggestme.fragments.SearchNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDailyFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDayFragment;

public class SwitchFragmentUtils {

    public static Fragment GetFragment(int group, int child) {
        Fragment fragment = null;

        switch (group) {
            case 0:
                switch (child) {
                    case 0:
                        //todo: top rated movies
                        break;
                    case 1:
                        //todo: top rated tv shows
                        break;
                    case 2:
                        //todo: popular movies
                        break;
                    case 3:
                        //todo: popular tc shows
                        break;
                    case 4:
                        //todo: moview description
                        break;
                    case 5:
                        //todo tv shows description
                        break;
                }
                break;
            case 1:
                switch (child) {
                    case 0:
                        //todo: popular artist
                        break;
                    case 1:
                        //todo: popular tracks;
                        break;
                    case 2:
                        //todo: top albums by artist
                        break;
                }
                break;
            case 2:
                switch (child) {
                    case 0:
                        fragment = WeatherDayFragment.getInstance();
                        break;
                    case 1:
                        fragment = WeatherDailyFragment.getInstance();
                        break;
                }
                break;
            case 3:
                switch (child) {
                    case 0:
                        fragment = TopNewsFragment.getInstance();
                        break;
                    case 1:
                        fragment = SearchNewsFragment.getInstance();
                        break;
                }
                break;
            case 4:
                switch (child) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
                break;
            case 5:
                switch (child) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
        }

        return fragment;
    }
/*
    public static String GetFragmentTitle(int group, int child) {
        ArrayList<ArrayList<String>> titles = new ArrayList<>();
        titles.add(Arrays.asList(getResources().getStringArray(R.array.cinema_sub_items)))


        String title = "Home Fragment";
    }
*/

}

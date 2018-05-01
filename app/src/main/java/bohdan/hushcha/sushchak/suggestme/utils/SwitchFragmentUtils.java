package bohdan.hushcha.sushchak.suggestme.utils;

import android.support.v4.app.Fragment;

import bohdan.hushcha.sushchak.suggestme.fragments.CryptoCurrencyFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.MovieDescriptionFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularArtistsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularMoviesFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularRecipesFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularTracksFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.RecipesByCriteriasFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.SearchNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopAlbumsByArtistFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopRatedMoviesFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDailyFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDayFragment;

public class SwitchFragmentUtils {

    public static Fragment GetFragment(int group, int child) {
        Fragment fragment = null;

        switch (group) {
            case 0:
                switch (child) {
                    case 0:
                        fragment = TopRatedMoviesFragment.getInstance();
                        break;
                    case 1:
                        fragment = TopRatedMoviesFragment.getInstance();
                        break;
                    case 2:
                        //todo: popular movies
                        fragment = PopularMoviesFragment.newInstance();

                        break;
                    case 3:
                        //todo: popular tc shows
                        fragment = PopularMoviesFragment.newInstance();
                        break;
                    case 4:
                        //todo: moview description
                        fragment = MovieDescriptionFragment.newInstance();
                        break;
                    case 5:
                        //todo tv shows description
                        fragment = MovieDescriptionFragment.newInstance();
                        break;
                }
                break;
            case 1:
                switch (child) {
                    case 0:
                        //todo: popular artist

                        fragment = PopularArtistsFragment.getInstance();
                        break;
                    case 1:
                        //todo: popular tracks;
                        fragment = PopularTracksFragment.getInstance();
                        break;
                    case 2:
                        //todo: top albums by artist
                        fragment = TopAlbumsByArtistFragment.newInstance();
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
                        fragment = CryptoCurrencyFragment.getInstance();
                        break;
                }
                break;
            case 5:
                switch (child) {
                    case 0:
                        fragment = RecipesByCriteriasFragment.getInstance();
                        break;
                    case 1:
                        fragment = PopularRecipesFragment.getInstance();
                        break;
                }
        }

        return fragment;
    }
}

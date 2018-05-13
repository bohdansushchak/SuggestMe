package bohdan.hushcha.sushchak.suggestme.utils;

import android.support.v4.app.Fragment;

import org.junit.Test;

import bohdan.hushcha.sushchak.suggestme.fragments.CinemaFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.CryptoCurrencyFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularArtistsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularRecipesFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.PopularTracksFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.RecipesByCriteriasFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.SearchMoviesFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.SearchNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopAlbumsByArtistFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.TopNewsFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDailyFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDayFragment;

import static org.junit.Assert.*;

public class SwitchFragmentUtilsTest {

    @Test
    public void GetCinemaFragmentTest() {

        Fragment expected = CinemaFragment.getInstance(MovieFragmentEnum.TopRatedMovie);
        Fragment actual = SwitchFragmentUtils.GetFragment(0, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = CinemaFragment.getInstance(MovieFragmentEnum.TopRatedTV);
        actual = SwitchFragmentUtils.GetFragment(0, 1);
        assertEquals(expected.getId(), actual.getId());

        expected = CinemaFragment.getInstance(MovieFragmentEnum.PopularMovie);
        actual = SwitchFragmentUtils.GetFragment(0, 2);
        assertEquals(expected.getId(), actual.getId());

        expected = CinemaFragment.getInstance(MovieFragmentEnum.PopularTV);
        actual = SwitchFragmentUtils.GetFragment(0, 3);
        assertEquals(expected.getId(), actual.getId());

        expected = SearchMoviesFragment.newInstance();
        actual = SwitchFragmentUtils.GetFragment(0, 4);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(0, 5);
        assertEquals(expected, actual);

    }

    @Test
    public void GetMusicFragmentTest() {

        Fragment expected = PopularArtistsFragment.getInstance();
        Fragment actual = SwitchFragmentUtils.GetFragment(1, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = PopularTracksFragment.getInstance();
        actual = SwitchFragmentUtils.GetFragment(1, 1);
        assertEquals(expected.getId(), actual.getId());

        expected = TopAlbumsByArtistFragment.newInstance();
        actual = SwitchFragmentUtils.GetFragment(1, 2);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(1, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void GetWeatherFragmentTest() {

        Fragment expected = WeatherDayFragment.getInstance();
        Fragment actual = SwitchFragmentUtils.GetFragment(2, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = WeatherDailyFragment.getInstance();
        actual = SwitchFragmentUtils.GetFragment(2, 1);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void GetNewsFragmentTest() {

        Fragment expected = TopNewsFragment.getInstance();
        Fragment actual = SwitchFragmentUtils.GetFragment(3, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = SearchNewsFragment.getInstance();
        actual = SwitchFragmentUtils.GetFragment(3, 1);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(3, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void GetCryptoCurrencyFragmentTest() {

        Fragment expected = CryptoCurrencyFragment.getInstance();
        Fragment actual = SwitchFragmentUtils.GetFragment(4, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(4, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void GetRecipesFragmentTest() {

        Fragment expected = RecipesByCriteriasFragment.getInstance();
        Fragment actual = SwitchFragmentUtils.GetFragment(5, 0);
        assertEquals(expected.getId(), actual.getId());

        expected = PopularRecipesFragment.getInstance();
        actual = SwitchFragmentUtils.GetFragment(5, 1);
        assertEquals(expected.getId(), actual.getId());

        expected = null;
        actual = SwitchFragmentUtils.GetFragment(5, 2);
        assertEquals(expected, actual);
    }
}
package bohdan.hushcha.sushchak.suggestme.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.MovieAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CinemaClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CinemaApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.TopRatedMoviesResponce;
import bohdan.hushcha.sushchak.suggestme.utils.MovieFragmentEnum;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaFragment extends Fragment implements LoadNextItems {

    private final String TAG = "TopRatedMovies";
    private InteractionListener mListener;

    private CinemaApiInterface cinemaApi;
    private List<Movie> movies;
    private Integer CurrentPage;
    private MovieAdapter adapter;
    private MovieFragmentEnum fragmentEnum;

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    public CinemaFragment() {
        this.movies = new ArrayList<>();
        this.cinemaApi = CinemaClient.getClient().create(CinemaApiInterface.class);
        this.CurrentPage = 1;
        fragmentEnum = MovieFragmentEnum.PopularMovie;
    }

    @SuppressLint("ValidFragment")
    public CinemaFragment(MovieFragmentEnum fragmentEnum) {
        this.movies = new ArrayList<>();
        this.cinemaApi = CinemaClient.getClient().create(CinemaApiInterface.class);
        this.CurrentPage = 1;
        this.fragmentEnum = fragmentEnum;
    }

    public static CinemaFragment getInstance(MovieFragmentEnum fragmentEnum) {
        CinemaFragment fragment = new CinemaFragment(fragmentEnum);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    private void Init() {

        Map<Integer, String> genres = new HashMap<>();

        genres.put(28, "Action");
        genres.put(12, "Adventure");
        genres.put(16, "Animation");
        genres.put(35, "Comedy");
        genres.put(80, "Crime");
        genres.put(99, "Documentary");
        genres.put(18, "Drama");
        genres.put(10751, "Family");
        genres.put(14, "Fantasy");
        genres.put(36, "History");
        genres.put(27, "Horror");
        genres.put(10402, "Music");
        genres.put(9648, "Mystery");
        genres.put(10749, "Romance");
        genres.put(878, "Science Fiction");
        genres.put(10770, "TV Movie");
        genres.put(53, "Thriller");
        genres.put(10752, "War");
        genres.put(10759, "Action & Adventure");
        genres.put(10762, "Kids");
        genres.put(10763, "News");
        genres.put(10764, "Reality");
        genres.put(10765, "ci-Fi & Fantasy");
        genres.put(37, "Western");
        genres.put(10766, "Soap");
        genres.put(10767, "Talk");
        genres.put(10768, "War & Politics");

        adapter = new MovieAdapter(getContext(), movies, this, mListener, genres);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(adapter);
        rvMovies.setHasFixedSize(false);
        LoadItems();
    }

    private void LoadItems() {
        Call<TopRatedMoviesResponce> call;

        switch (fragmentEnum) {
            case TopRatedMovie:
                call = cinemaApi.GetTopRatedMovies(CinemaClient.API_KEY, CurrentPage);
                break;
            case TopRatedTV:
                call = cinemaApi.GetTopRatedTVShows(CinemaClient.API_KEY, CurrentPage);
                break;
            case PopularMovie:
                call = cinemaApi.GetPopularMovies(CinemaClient.API_KEY, CurrentPage);
                break;
            case PopularTV:
                call = cinemaApi.GetPopularTVShows(CinemaClient.API_KEY, CurrentPage);
                break;
            default:
                call = cinemaApi.GetTopRatedMovies(CinemaClient.API_KEY, CurrentPage);
                break;
        }

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<TopRatedMoviesResponce>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponce> call, Response<TopRatedMoviesResponce> response) {
                List<Movie> movieList = response.body().getMovies();

                if (movieList != null) {
                    movies.addAll(movieList);
                    adapter.notifyDataSetChanged();
                    ++CurrentPage;
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponce> call, Throwable t) {

            }
        });
/*
        Call<GenreListResponce> call2 = cinemaApi.GetGenreList(CinemaClient.API_KEY);

        Log.d(TAG, call2.request().url().toString());

        call2.enqueue(new Callback<GenreListResponce>() {
            @Override
            public void onResponse(Call<GenreListResponce> call, Response<GenreListResponce> response) {
                //List<Map<Integer, String>> mapList = response.body().getGenres();

                Map<Integer, String> newMap = response.body().getGenres();//new HashMap<>();

                for (Map<Integer, String> map : mapList)
                    newMap.putAll(map);

                Log.d(TAG, newMap.get(12));
                Log.e(TAG, newMap.get(37));

            }

            @Override
            public void onFailure(Call<GenreListResponce> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void LoadNextItems() {
        LoadItems();
    }
}

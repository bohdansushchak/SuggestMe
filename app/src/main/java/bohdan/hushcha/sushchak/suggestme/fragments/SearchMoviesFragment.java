package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.PickMovieAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.ViewMovieDescription;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CinemaClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CinemaApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.TopRatedMoviesResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviesFragment extends BaseFragment implements LoadNextItems {

    public final String TAG = "SearchMoviesFragment";

    private ViewMovieDescription mListener;

    private List<Movie> movieList;
    private CinemaApiInterface cinemaApi;
    private Integer CurrentPage;
    private String MovieName;

    private PickMovieAdapter adapter;

    @BindView(R.id.edMovieName)
    EditText edMovieName;

    @BindView(R.id.rvMovieSearch)
    RecyclerView rvMovieSearch;

    public SearchMoviesFragment() {
        cinemaApi = CinemaClient.getClient().create(CinemaApiInterface.class);
        CurrentPage = 1;
        movieList = new ArrayList<>();
    }

    public static SearchMoviesFragment newInstance() {
        SearchMoviesFragment fragment = new SearchMoviesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_movies, container, false);
        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    private void Init() {

        adapter = new PickMovieAdapter(movieList, mListener, this, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvMovieSearch.setLayoutManager(layoutManager);
        rvMovieSearch.setAdapter(adapter);

        edMovieName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {

                    MovieName = charSequence.toString();
                    CurrentPage = 1;
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                    loadItems(MovieName);
                }
                else {
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void loadItems(final String movieName) {

        Call<TopRatedMoviesResponce> call = cinemaApi.SearchMovie(CinemaClient.API_KEY, movieName, CurrentPage);

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<TopRatedMoviesResponce>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponce> call, Response<TopRatedMoviesResponce> response) {
                if (response.body() != null) {
                    if (response.body().getMovies() != null) {

                        movieList.addAll(response.body().getMovies());
                        adapter.notifyDataSetChanged();
                        CurrentPage++;
                    }
                    else {
                        movieList.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
                else {
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponce> call, Throwable t) {
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (ViewMovieDescription) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void LoadNextItems() {
        loadItems(MovieName);
    }
}

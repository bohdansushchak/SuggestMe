package bohdan.hushcha.sushchak.suggestme.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.CompanyAdapter;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CinemaClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CinemaApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Genre;
import bohdan.hushcha.sushchak.suggestme.rest.responces.cinema.MovieDetails;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class MovieDescriptionFragment extends BaseFragment {

    public final String TAG = "MovieDescription";

    private CinemaApiInterface cinemaApi;

    private String movieId;

    private MovieDetails movieDetails;

    @BindView(R.id.rvCompany)
    RecyclerView rvCompany;

    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;

    @BindView(R.id.ivBackground)
    ImageView ivMovieBackground;

    @BindView(R.id.tvBudget)
    TextView tvBundget;

    @BindView(R.id.tvOrigLanguage)
    TextView tvOriginLanguage;

    @BindView(R.id.tvGenre)
    TextView tvGenre;

    @BindView(R.id.tvPopularity)
    TextView tvPopularity;

    @BindView(R.id.tvOverview)
    TextView tvOverview;

    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @BindView(R.id.tvVote)
    TextView tvVotes;

    @BindView(R.id.tvVoteAverage)
    TextView tvVoteAvarange;

    @BindView(R.id.tvTagLine)
    TextView tvTagLine;

    @BindView(R.id.tvRevenue)
    TextView tvRevenue;

    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;

    @SuppressLint("ValidFragment")
    public MovieDescriptionFragment(String movieId){
        cinemaApi = CinemaClient.getClient().create(CinemaApiInterface.class);
        this.movieId = movieId;
    }

    public static MovieDescriptionFragment newInstance(String movieID) {
        MovieDescriptionFragment fragment = new MovieDescriptionFragment(movieID);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_description, container, false);

        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    private void Init()
    {
        Call<MovieDetails> call = cinemaApi.GetDetailsById(movieId, CinemaClient.API_KEY);

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                movieDetails = response.body();
                if(movieDetails != null)
                    ViewMovie();
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }

    /**
     * Set response data from server to views
     */
    public void ViewMovie(){

        tvBundget.setText(movieDetails.getBudget());
        tvMovieTitle.setText( movieDetails.getTitle());
        tvOriginLanguage.setText(movieDetails.getOriginalLanguage());
        tvOverview.setText(movieDetails.getOverview());
        tvPopularity.setText(movieDetails.getPopularity().toString());
        tvStatus.setText(movieDetails.getStatus());
        tvRevenue.setText(movieDetails.getRevenue());
        tvVotes.setText(movieDetails.getVoteCount());
        tvVoteAvarange.setText(movieDetails.getVoteAverange());
        tvTagLine.setText(movieDetails.getTagLine());
        tvReleaseDate.setText(movieDetails.getReleaseDate());

        String genres = "";

        for (Genre genre : movieDetails.getGenres()) {
            if (genres == "" || genres == null)
                genres = genre.getName();
            else
                genres = genres + ", " + genre.getName();
        }

        tvGenre.setText(genres);

        RequestOptions options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.movie_image_default)
                .error(R.drawable.image_error);

        Glide.with(getContext()).load(movieDetails.getPosterPath())
                .apply(options)
                .into(ivMovieBackground);

        CompanyAdapter adapter = new CompanyAdapter(movieDetails.getCompanies(), getContext());

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rvCompany.setLayoutManager(layoutManager);
        rvCompany.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

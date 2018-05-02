package bohdan.hushcha.sushchak.suggestme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private LoadNextItems loadNextItems;
    private InteractionListener interactionListener;
    private Map<Integer, String> genresMap;

    public MovieAdapter(List<Movie> movies, LoadNextItems loadNextItems,
                        InteractionListener interactionListener, Map<Integer, String> genres) {
        this.movies = movies;
        this.loadNextItems = loadNextItems;
        this.interactionListener = interactionListener;
        this.genresMap = genres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(movie.getReleaseDate());
        holder.tvOverview.setText(movie.getOverview());
        holder.tvPopularity.setText(movie.getPopularity().toString());
        holder.tvVoteAverange.setText(movie.getVoteAverange());

        String genres = "";

        for (Integer genreId : movie.getIdGenres()) {
            if (genres == "" || genres == null)
                genres = genresMap.get(genreId);
            else
                genres = genres + ", " + genresMap.get(genreId);
        }

        holder.tvGenre.setText(genres);

        if (position == movies.size() - 1) {
            loadNextItems.LoadNextItems();
        }

        holder.ibMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = holder.llMoviewInfo.getVisibility();
                if (visibility == View.GONE) {
                    holder.llMoviewInfo.setVisibility(View.VISIBLE);
                    holder.ibMoreInfo.setRotation(180);
                } else {
                    holder.llMoviewInfo.setVisibility(View.GONE);
                    holder.ibMoreInfo.setRotation(-180);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (interactionListener != null) {
                    interactionListener.OnClick(movie.get);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivMovieBackground)
        ImageView ivBackground;

        @BindView(R.id.ibMoreInfo)
        ImageButton ibMoreInfo;

        @BindView(R.id.tvVoteAverage)
        TextView tvVoteAverange;

        @BindView(R.id.tvMovieTitle)
        TextView tvMovieTitle;

        @BindView(R.id.tvOverview)
        TextView tvOverview;

        @BindView(R.id.tvReleaseDate)
        TextView tvReleaseDate;

        @BindView(R.id.tvPopularity)
        TextView tvPopularity;

        @BindView(R.id.llMovieInfo)
        LinearLayout llMoviewInfo;

        @BindView(R.id.tvGenre)
        TextView tvGenre;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

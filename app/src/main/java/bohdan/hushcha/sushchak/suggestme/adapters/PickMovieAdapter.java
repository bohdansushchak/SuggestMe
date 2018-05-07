package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.ViewMovieDescription;
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PickMovieAdapter extends RecyclerView.Adapter<PickMovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private ViewMovieDescription mListener;
    private LoadNextItems mLoadNext;
    private Context context;

    public PickMovieAdapter(List<Movie> movieList, ViewMovieDescription mListener, LoadNextItems mLoadNext, Context context) {
        this.movieList = movieList;
        this.mListener = mListener;
        this.mLoadNext = mLoadNext;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_pick, parent, false);
        return new PickMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvMovieTitle.setText(movieList.get(position).getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.movie_image_default)
                .error(R.drawable.image_error);

        Glide.with(context).load(movieList.get(position).getBackgroundPath())
                .apply(options)
                .into(holder.ivBackground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ViewMovieDetails(movieList.get(position).getId());
            }
        });

        if (position == movieList.size() - 1) {
            mLoadNext.LoadNextItems();
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvMovieTitle)
        TextView tvMovieTitle;

        @BindView(R.id.ivBackground)
        ImageView ivBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

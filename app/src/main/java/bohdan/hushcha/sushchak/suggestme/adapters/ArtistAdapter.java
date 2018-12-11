package bohdan.hushcha.sushchak.suggestme.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to view artist list in recycler view.
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<Artist> artists;
    private LoadNextItems loadNextItems;
    private InteractionListener<String> interactionListener;

    /**
     * @param artists             list artist
     * @param loadNextItems       interface for load next elements
     * @param interactionListener interface for get click by item
     */
    public ArtistAdapter(List<Artist> artists, LoadNextItems loadNextItems, InteractionListener<String> interactionListener) {
        this.artists = artists;
        this.loadNextItems = loadNextItems;
        this.interactionListener = interactionListener;
    }

    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artist, parent, false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tvArtist.setText(artists.get(position).getName());

        if (position == artists.size() - 1) {
            loadNextItems.LoadNextItems();
        }

        holder.btnGoToArtistPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactionListener.OnClick(artists.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    /**
     * Class for hold views ids
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvArtistName)
        TextView tvArtist;

        @BindView(R.id.btnGoToArtistPage)
        Button btnGoToArtistPage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package bohdan.hushcha.sushchak.suggestme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Track;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class adapter to view tracks list in recycler view
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private List<Track> tracks;
    private LoadNextItems loadNextItems;

    /**
     * @param tracks        list tracks
     * @param loadNextItems interface for load next elements
     */
    public TrackAdapter(List<Track> tracks, LoadNextItems loadNextItems) {
        this.tracks = tracks;
        this.loadNextItems = loadNextItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvArtist.setText(tracks.get(position).getArtist().getName());

        holder.tvTrack.setText(tracks.get(position).getName());

        if (position == tracks.size() - 1) {
            loadNextItems.LoadNextItems();
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    /**
     * Class for hold views ids
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvArtistName)
        TextView tvArtist;

        @BindView(R.id.tvTrackName)
        TextView tvTrack;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

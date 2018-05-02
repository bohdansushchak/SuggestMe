package bohdan.hushcha.sushchak.suggestme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<Artist> artists;
    private LoadNextItems loadNextItems;

    public ArtistAdapter(List<Artist> artists, LoadNextItems loadNextItems) {
        this.artists = artists;
        this.loadNextItems = loadNextItems;
    }

    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistAdapter.ViewHolder holder, int position) {

        holder.tvArtist.setText(artists.get(position).getName());

        if (position == artists.size() - 1){
            loadNextItems.LoadNextItems();
        }
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.tvArtistName)
        TextView tvArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

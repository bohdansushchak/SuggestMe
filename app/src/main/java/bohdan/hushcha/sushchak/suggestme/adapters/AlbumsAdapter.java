package bohdan.hushcha.sushchak.suggestme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Album;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to view album list in recycler view.
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private List<Album> albums;
    private LoadNextItems loadNextItems;
    private InteractionListener mListener;

    /**
     * Class constructor
     *
     * @param albums        list albums
     * @param loadNextItems interface for load next elements
     * @param mListener     interface for get click by item
     */
    public AlbumsAdapter(List<Album> albums, LoadNextItems loadNextItems, InteractionListener mListener) {
        this.albums = albums;
        this.loadNextItems = loadNextItems;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvAlbum.setText(albums.get(position).getName());
        holder.tvPlayCount.setText(albums.get(position).getPlayCount());
        holder.tvArtist.setText(albums.get(position).getArtist().getName());

        if (position == albums.size() - 1) {
            loadNextItems.LoadNextItems();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.OnClick(albums.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    /**
     * Class for hold views ids
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvAlbum)
        TextView tvAlbum;

        @BindView(R.id.tvPlayCount)
        TextView tvPlayCount;

        @BindView(R.id.tvArtist)
        TextView tvArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.News.Article;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> items;
    private Context context;
    private InteractionListener mListener;
    private LoadNextItems loadNextItems;

    public NewsAdapter(Context context, List<Article> items, InteractionListener mListener,
                       LoadNextItems loadNextItems) {
        this.items = items;
        this.context = context;
        this.mListener = mListener;
        this.loadNextItems = loadNextItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvDecription.setText(items.get(position).getDecription());
        holder.tvAuthor.setText(items.get(position).getAuthor());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(context).load(items.get(position).getUrlToImage())
                .apply(options)
                .into(holder.ivBackground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.OnClick(items.get(position).getUrl());
                }
            }
        });

        if (position == items.size() - 1) {
            loadNextItems.LoadNextItems();
        }

        holder.ivNewsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.llArticleInfo.getVisibility() == View.VISIBLE)
                    holder.llArticleInfo.setVisibility(View.GONE);
                else
                    holder.llArticleInfo.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivBackground)
        ImageView ivBackground;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvAuthor)
        TextView tvAuthor;

        @BindView(R.id.tvDescription)
        TextView tvDecription;

        @BindView(R.id.llNewsInfo)
        LinearLayout llArticleInfo;

        @BindView(R.id.ivNewInfo)
        ImageButton ivNewsInfo;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

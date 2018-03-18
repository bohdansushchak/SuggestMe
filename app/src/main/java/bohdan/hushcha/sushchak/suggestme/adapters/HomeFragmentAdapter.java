package bohdan.hushcha.sushchak.suggestme.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.models.HomeItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

    private List<HomeItem> items;
    private Context context;

    public HomeFragmentAdapter(Context context, List<HomeItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvDecription.setText(items.get(position).getDescription());
        holder.tvCategory.setText(items.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivBackground) ImageView ivBackground;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvCategory) TextView tvCategory;
        @BindView(R.id.tvDescription) TextView tvDecription;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

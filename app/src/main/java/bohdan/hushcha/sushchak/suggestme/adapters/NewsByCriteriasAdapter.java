package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.models.Article;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsByCriteriasAdapter extends RecyclerView.Adapter<NewsByCriteriasAdapter.ViewHolder>{

    private List<Article> items;
    private Context context;



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivBackground) ImageView ivBackground;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvCategory) TextView tvCategory;
        @BindView(R.id.tvDescription) TextView tvDecription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

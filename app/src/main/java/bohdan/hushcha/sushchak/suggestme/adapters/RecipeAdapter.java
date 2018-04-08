package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Recipe;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private Context context;
    private InteractionListener mListener;
    private LoadNextItems loadNextItems;

    public RecipeAdapter(List<Recipe> recipeList, Context context,
                         InteractionListener mListener, LoadNextItems loadNextItems) {
        this.recipeList = recipeList;
        this.context = context;
        this.mListener = mListener;
        this.loadNextItems = loadNextItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Recipe recipe = recipeList.get(position);

        holder.tvPublisher.setText(recipe.getPublisher());
        holder.tvTitle.setText(recipe.getTitle());
        holder.tvSocialRate.setText(String.format("%.0f", recipe.getRank()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(context).load(recipe.getImageUrl())
                .apply(options)
                .into(holder.ivRecipeImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnClick(recipe.getSourceUrl());
            }
        });

        Log.d("Adapter", "Position: " + position );

        if (position == recipeList.size() - 1){
            loadNextItems.LoadNextItems();
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_publisher)
        TextView tvPublisher;

        @BindView(R.id.tv_socialRate)
        TextView tvSocialRate;

        @BindView(R.id.tvRecipeTitle)
        TextView tvTitle;

        @BindView(R.id.ivRecipe)
        ImageView ivRecipeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

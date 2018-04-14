package bohdan.hushcha.sushchak.suggestme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.models.CryptoCurrency;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> {

    private List<CryptoCurrency> items;

    public CryptoAdapter(List<CryptoCurrency> items) {
        this.items = items;
    }

    @Override
    public CryptoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crypto_currency, parent, false);
        return new CryptoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CryptoCurrency item = items.get(position);

        holder.tvName.setText(item.getName());
        holder.tvChange.setText(item.getPercentChange24h());
        holder.tvPrice.setText(item.getPriceUSD());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        @BindView(R.id.tvChange)
        TextView tvChange;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

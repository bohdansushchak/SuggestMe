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
import bohdan.hushcha.sushchak.suggestme.rest.models.Cinema.Company;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to view company list in recycler view.
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<Company> companies;
    private Context context;

    /**
     * @param companies list companies
     * @param context   context to access to base functions activity
     */
    public CompanyAdapter(List<Company> companies, Context context) {
        this.companies = companies;
        this.context = context;
    }

    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_company, parent, false);
        return new CompanyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvCompanyName.setText(companies.get(position).getName());
        holder.tvCompanyCountry.setText(companies.get(position).getOriginalCountry());

        RequestOptions options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.company_logo)
                .error(R.drawable.company_logo);

        Glide.with(context).load(companies.get(position).getLogoPath())
                .apply(options)
                .into(holder.civCompany);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    /**
     * Class for hold views ids
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civCompany)
        ImageView civCompany;

        @BindView(R.id.tvCompanyTitle)
        TextView tvCompanyName;

        @BindView(R.id.tvCompanyCountry)
        TextView tvCompanyCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

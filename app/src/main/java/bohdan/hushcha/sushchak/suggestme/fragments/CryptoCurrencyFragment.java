package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.CryptoAdapter;
import bohdan.hushcha.sushchak.suggestme.adapters.SimpleDividerItem;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CryptoCurrencyClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CryptoCurrencyInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.CryptoCurrency.CryptoCurrency;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoCurrencyFragment extends BaseFragment {

    @BindView(R.id.rvCrypto)
    RecyclerView recyclerView;

    private CryptoAdapter adapter;
    private List<CryptoCurrency> items;
    private CryptoCurrencyInterface apiService;

    public CryptoCurrencyFragment() {
        items = new ArrayList<>();
        apiService = CryptoCurrencyClient.getClient().create(CryptoCurrencyInterface.class);
    }

    public static CryptoCurrencyFragment getInstance() {
        CryptoCurrencyFragment fragment = new CryptoCurrencyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_currency, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    /**
     * Initialise adapter and recycler view
     */
    private void init(){
        adapter = new CryptoAdapter(items, getContext());

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SimpleDividerItem(getContext()));

        Call<List<CryptoCurrency>> call = apiService.TopCryptoCurrency();

        call.enqueue(new Callback<List<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response)
            {
                if(response.body() != null){
                    items.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

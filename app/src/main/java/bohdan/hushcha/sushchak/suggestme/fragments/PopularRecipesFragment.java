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

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.RecipeAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CookingBookClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CookingBookApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.CookingBook.Recipe;
import bohdan.hushcha.sushchak.suggestme.rest.responces.CookingBookResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularRecipesFragment extends Fragment implements LoadNextItems {

    final String TAG = "PopularRecipesFragment";

    private ArrayList<Recipe> items;
    private InteractionListener mListener;

    private Integer CurrentPage;

    private CookingBookApiInterface apiService;
    private RecipeAdapter adapter;

    @BindView(R.id.rvRecipeList)
    RecyclerView recyclerView;

    public PopularRecipesFragment() {
        items = new ArrayList<>();
        CurrentPage = 1;
        apiService = CookingBookClient.getClient().create(CookingBookApiInterface.class);
    }

    public static PopularRecipesFragment getInstance() {
        PopularRecipesFragment fragment = new PopularRecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init() {

        adapter = new RecipeAdapter(items, getContext(), mListener, this);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Call<CookingBookResponce> call = apiService.TopRecipes(CookingBookClient.API_KEY, "r", CurrentPage);

        call.enqueue(new Callback<CookingBookResponce>() {
            @Override
            public void onResponse(Call<CookingBookResponce> call, Response<CookingBookResponce> response) {
                items.addAll(response.body().getRecipes());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CookingBookResponce> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_recipes, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void LoadNextItems() {
        ++CurrentPage;

        Call<CookingBookResponce> call = apiService.TopRecipes(CookingBookClient.API_KEY, "t", CurrentPage);

        call.enqueue(new Callback<CookingBookResponce>() {
            @Override
            public void onResponse(Call<CookingBookResponce> call, Response<CookingBookResponce> response) {
                items.addAll(response.body().getRecipes());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CookingBookResponce> call, Throwable t) {

            }
        });
    }
}

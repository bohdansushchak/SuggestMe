package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.RecipeAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.CookingBookClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.CookingBookApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.CookingBook.Recipe;
import bohdan.hushcha.sushchak.suggestme.rest.responces.CookingBookResponce;
import bohdan.hushcha.sushchak.suggestme.utils.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesByCriteriasFragment extends Fragment implements LoadNextItems {

    private final String TAG = "RecipesByCriterias";

    private InteractionListener mListener;
    private ArrayList<Recipe> items;
    private RecipeAdapter adapter;
    private Integer CurrentPage;

    private String Ingredients;
    private String TempIngredients;

    private CookingBookApiInterface apiService;

    @BindView(R.id.edIngredients)
    EditText edIngredients;

    @BindView(R.id.rvRecipesSearch)
    RecyclerView recyclerView;

    public RecipesByCriteriasFragment() {
        CurrentPage = 1;
        Ingredients = "";
        TempIngredients = "";
    }

    public static RecipesByCriteriasFragment getInstance() {
        RecipesByCriteriasFragment fragment = new RecipesByCriteriasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void Init() {
        items = new ArrayList<>();

        adapter = new RecipeAdapter(items, getContext(), mListener, this);

        apiService = CookingBookClient.getClient().create(CookingBookApiInterface.class);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_by_criterias, container, false);
        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    @OnClick(R.id.ivSearch)
    public void Search(View view) {
        Log.d(TAG, "iTempIngredients: " + TempIngredients);
        Log.d(TAG, "edIngredients.getText(): " + edIngredients.getText());

        if (!TempIngredients.equals(edIngredients.getText().toString()) || TempIngredients.isEmpty()) {
            Log.d(TAG, "ivSearch enter if");
            CurrentPage = 1;
            TempIngredients = edIngredients.getText().toString();
            Ingredients = StringUtils.FormatIngredients(TempIngredients);

            Log.d(TAG, "Ingredients: " + Ingredients);

            Call<CookingBookResponce> call = apiService.SearchRecipes(CookingBookClient.API_KEY, "r", Ingredients ,CurrentPage);

            call.enqueue(new Callback<CookingBookResponce>() {
                @Override
                public void onResponse(Call<CookingBookResponce> call, Response<CookingBookResponce> response) {
                    items.clear();
                    items.addAll(response.body().getRecipes());

                    Log.d(TAG,"Item.size: " + items.size());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<CookingBookResponce> call, Throwable t) {

                }
            });
        }
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

        Call<CookingBookResponce> call = apiService.SearchRecipes(CookingBookClient.API_KEY, "r", Ingredients ,CurrentPage);

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

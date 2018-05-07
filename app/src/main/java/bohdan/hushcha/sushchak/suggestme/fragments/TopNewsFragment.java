package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.NewsAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.News.Article;
import bohdan.hushcha.sushchak.suggestme.rest.clients.NewsClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.NewsApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.responces.NewsResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopNewsFragment extends Fragment implements LoadNextItems {

    final String TAG = "TopNewsFragment";

    private ArrayList<Article> items;
    private InteractionListener mListener;
    private NewsAdapter adapter;
    private Integer currentPage;
    private NewsApiInterface apiService;

    @BindView(R.id.rvMainList)
    RecyclerView recyclerView;

    public TopNewsFragment() {
        currentPage = 1;
        apiService = NewsClient.getClient().create(NewsApiInterface.class);
        items = new ArrayList<>();
    }

    public static TopNewsFragment getInstance() {
        TopNewsFragment topNewsFragment = new TopNewsFragment();

        return topNewsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_news, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

        adapter = new NewsAdapter(getContext(), items, mListener, this);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        LoadItems();
    }

    private void LoadItems() {

        Call<NewsResponce> call = apiService.TopHeadlines(NewsClient.API_KEY, "us", currentPage);
        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<NewsResponce>() {
            @Override
            public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {
                if (response.body().getTotalResults() > items.size()) {
                    List<Article> articles = response.body().getArticles();

                    if (articles != null) {
                        items.addAll(articles);
                        adapter.notifyDataSetChanged();
                        ++currentPage;
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponce> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
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
        LoadItems();
    }
}

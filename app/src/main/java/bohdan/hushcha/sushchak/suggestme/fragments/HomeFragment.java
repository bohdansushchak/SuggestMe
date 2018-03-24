package bohdan.hushcha.sushchak.suggestme.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.HomeFragmentAdapter;
import bohdan.hushcha.sushchak.suggestme.models.HomeItem;
import bohdan.hushcha.sushchak.suggestme.rest.models.Article;
import bohdan.hushcha.sushchak.suggestme.rest.clients.NewsClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.NewsApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.responces.NewsResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View view;

    private ArrayList<HomeItem> items;

    @BindView(R.id.rvMainList) RecyclerView recyclerView;

    public HomeFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

        items = new ArrayList<>();
/*
        items.add(new HomeItem("IT new smartphone", "rththr", "", "News"));
        items.add(new HomeItem("New dish", "dfbbdg", "", "Cooking"));
        items.add(new HomeItem("Today storm", "df", "", "Weather"));
        items.add(new HomeItem("Bitcoin fall", "dfgdfg", "", "Crypto info"));
        items.add(new HomeItem("New trek", "trbh", "", "Music"));
        items.add(new HomeItem("Infinity war new trail", "cdffdfdfddf", "", "Films"));
*/
        final HomeFragmentAdapter adapter = new HomeFragmentAdapter(getContext(), items);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        NewsApiInterface apiService = NewsClient.getClient().create(NewsApiInterface.class);

        Call<NewsResponce> call = apiService.TopHeadlines(NewsClient.API_KEY, "us", "business");
        call.enqueue(new Callback<NewsResponce>() {
            @Override
            public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {
                List<Article> articles = response.body().getArticles();

                for (Article article : articles) {
                    HomeItem item = new HomeItem(article.getTitle(), article.getDecription(), article.getUrlToImage(),article.getPublishedAt());
                    items.add(item);
                }
                adapter.notifyDataSetChanged();

                //Log.d(TAG, "results: " + response.body().getTotalResults());
            }

            @Override
            public void onFailure(Call<NewsResponce> call, Throwable t) {
                //Log.e(TAG, t.toString());
            }
        });






    }
}

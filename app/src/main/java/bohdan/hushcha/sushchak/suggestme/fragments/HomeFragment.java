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

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.HomeFragmentAdapter;
import bohdan.hushcha.sushchak.suggestme.models.HomeItem;
import butterknife.BindView;
import butterknife.ButterKnife;

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

        items.add(new HomeItem("IT new smartphone","rththr","","News"));
        items.add(new HomeItem("New dish","dfbbdg","","Cooking"));
        items.add(new HomeItem("Today storm","df","","Weather"));
        items.add(new HomeItem("Bitcoin fall","dfgdfg","","Crypto info"));
        items.add(new HomeItem("New trek","trbh","","Music"));
        items.add(new HomeItem("Infinity war new trail","cdffdfdfddf","","Films"));

        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getContext(), items);

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

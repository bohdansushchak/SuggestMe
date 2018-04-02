package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bohdan.hushcha.sushchak.suggestme.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchNewsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rvNewsSearch) RecyclerView recyclerViewNews;
    @BindView(R.id.btnSearch) Button btnSearch;

    @BindView(R.id.tvChooseSource) TextView tvChooseSource;
    @BindView(R.id.tvChooseCountry) TextView tvChooseCountry;
    @BindView(R.id.tvDateAt) TextView tvDateAt;
    @BindView(R.id.tvDateTo) TextView tvDateTo;


    public SearchNewsFragment() {
        // Required empty public constructor
    }

    public static SearchNewsFragment newInstance(String param1, String param2) {
        SearchNewsFragment fragment = new SearchNewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }

        /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick({R.id.btnSearch, R.id.tvChooseCountry, R.id.tvChooseSource, R.id.tvDateTo, R.id.tvDateAt})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btnSearch:

                break;
            case R.id.tvChooseCountry:

                break;
            case R.id.tvChooseSource:

                break;
            case R.id.tvDateAt:

                break;
            case R.id.tvDateTo:

                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

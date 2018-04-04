package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.rest.clients.NewsClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.NewsApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.NewsSource;
import bohdan.hushcha.sushchak.suggestme.rest.responces.NewsResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.SourcesResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNewsFragment extends BaseMyFragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rvNewsSearch)
    RecyclerView recyclerViewNews;
    @BindView(R.id.btnSearch)
    Button btnSearch;

    @BindView(R.id.tvChooseSource)
    TextView tvChooseSource;
    @BindView(R.id.tvChooseCountry)
    TextView tvChooseCountry;
    @BindView(R.id.tvDateAt)
    TextView tvDateAt;
    @BindView(R.id.tvDateTo)
    TextView tvDateTo;

    private NewsApiInterface newsApiInterface;

    private String CoutryArg;
    private String SourceId;
    private Date DateAt;
    private Date DateTo;

    public SearchNewsFragment() {

        newsApiInterface = NewsClient.getClient().create(NewsApiInterface.class);
    }

    public static SearchNewsFragment getInstance() {
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * OnClick method
     *
     * @param view clicked item
     */
    @OnClick({R.id.btnSearch, R.id.tvChooseCountry, R.id.tvChooseSource, R.id.tvDateTo, R.id.tvDateAt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                Call<NewsResponce> callNews = null;

                if (SourceId != null && CoutryArg != null) {

                } else if (SourceId != null) {

                } else if (CoutryArg != null) {

                } else {
                    DialogAlert(R.string.dialog_title_no_criterias, R.string.dialog_message_no_criterias);

                    break;
                }

                callNews.enqueue(new Callback<NewsResponce>() {
                    @Override
                    public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {

                    }

                    @Override
                    public void onFailure(Call<NewsResponce> call, Throwable t) {

                    }
                });
                break;
            case R.id.tvChooseCountry:
                ActionChooseCountry();

                break;
            case R.id.tvChooseSource:

                Call<SourcesResponce> call;

                if (CoutryArg != null)
                    call = newsApiInterface.GetSourcesByCountry(NewsClient.API_KEY, CoutryArg);
                else
                    call = newsApiInterface.GetAllSources(NewsClient.API_KEY);

                call.enqueue(new Callback<SourcesResponce>() {
                    @Override
                    public void onResponse(Call<SourcesResponce> call, Response<SourcesResponce> response) {
                        List<NewsSource> listSources = response.body().getSources();
                        if (listSources.size() == 0) {

                            DialogAlert(R.string.dialog_title_nothing_to_show, R.string.dialog_message);

                        } else {

                            ActionChooseSource(listSources);
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponce> call, Throwable t) {

                    }
                });

                break;
            case R.id.tvDateAt:

                break;
            case R.id.tvDateTo:

                break;
        }
    }

    /**
     * Method to view dialog and choose country for search
     */
    private void ActionChooseCountry() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.dialog_title_choose_country));

        final List<String> countryNames = Arrays.asList(getResources().getStringArray(R.array.countries_names));
        final List<String> countryArgs = Arrays.asList(getResources().getStringArray(R.array.countries_arg));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(countryNames);

        dialog.setNegativeButton(getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CoutryArg = countryArgs.get(i);

                tvChooseCountry.setText(countryNames.get(i));
            }
        });
        dialog.show();
    }

    /**
     * Method to view dialog for choose news source
     *
     * @param sourcesList news sources list
     */
    private void ActionChooseSource(final List<NewsSource> sourcesList) {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.dialog_title_choose_country));

        final List<String> SourcesNames = new ArrayList<>();
        final List<String> SourcesId = new ArrayList<>();

        for (NewsSource source : sourcesList) {
            SourcesNames.add(source.getName());
            SourcesId.add(source.getId());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(SourcesNames);

        dialog.setNegativeButton(getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SourceId = SourcesId.get(i);

                tvChooseSource.setText(SourcesNames.get(i));
            }
        });

        dialog.show();
    }

    /**
     * @param StringResorceTitle
     * @param StringResourceMessage
     */
    private void DialogAlert(int StringResorceTitle, int StringResourceMessage) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(StringResorceTitle);
        dialog.setMessage(StringResourceMessage);
        dialog.setNegativeButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

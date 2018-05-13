package bohdan.hushcha.sushchak.suggestme.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Arrays;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.NewsAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.NewsClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.NewsApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.News.Article;
import bohdan.hushcha.sushchak.suggestme.rest.models.News.NewsSource;
import bohdan.hushcha.sushchak.suggestme.rest.responces.NewsResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.SourcesResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment to search news articles in api
 *
 * @author Bohdan
 * @since 1.0
 * @version 1.0
 */
public class SearchNewsFragment extends BaseFragment implements LoadNextItems {

    private final String TAG = "SearchNewsFragment";

    private InteractionListener mListener;

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
    private Integer currentPage;

    private String CoutryArg;
    private String SourceId;
    private String DateAt;
    private String DateTo;

    private List<Article> articleList;
    private NewsAdapter newsAdapter;

    /**
     * Class constructor
     */
    public SearchNewsFragment() {

        newsApiInterface = NewsClient.getClient().create(NewsApiInterface.class);
        articleList = new ArrayList<>();
        currentPage = 1;
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
        init();
        return view;
    }

    /**
     * Private method to initialize all view in fragment
     */
    private void init() {
        articleList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), articleList, mListener, this);

        recyclerViewNews.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setAdapter(newsAdapter);
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

    /**
     * OnClick method
     *
     * @param view clicked item
     */
    @OnClick({R.id.btnSearch, R.id.tvChooseCountry, R.id.tvChooseSource, R.id.tvDateTo, R.id.tvDateAt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:

                LoadItems();

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

                DateDialogPicker(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        DateAt = year + "-" + month + "-" + day;
                        tvDateAt.setText(DateAt);
                    }
                });

                break;
            case R.id.tvDateTo:

                DateDialogPicker(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        DateTo = year + "-" + month + "-" + day;
                        tvDateTo.setText(DateTo);
                    }
                });

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
                if (SourceId != null) {
                    SourceId = null;
                    tvChooseSource.setText(R.string.tv_pick_sourse);
                    currentPage = 1;
                }
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
                currentPage = 1;
                tvChooseSource.setText(SourcesNames.get(i));
            }
        });

        dialog.show();
    }

    /**
     * Method to pick date as a dialog
     */
    private void DateDialogPicker(DatePickerDialog.OnDateSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                listener, year, month, day);

        datePickerDialog.show();
    }

    /**
     * Function to load articles from newsApi and view in list
     */
    private void LoadItems() {

        Call<NewsResponce> callNews = null;

        if (SourceId != null && DateTo != null && DateAt != null) {
            callNews = newsApiInterface.GetEverything(NewsClient.API_KEY, DateAt, DateTo, currentPage, SourceId);

        } else if (SourceId != null && DateAt != null) {
            callNews = newsApiInterface.GetEverything(NewsClient.API_KEY, DateAt, currentPage, SourceId);

        } else if (SourceId != null && DateTo != null) {
            callNews = newsApiInterface.GetEverythingDateTo(NewsClient.API_KEY, DateTo, currentPage, SourceId);

        } else {
            DialogAlert(R.string.dialog_title_no_criterias, R.string.dialog_message_no_criterias);
        }

        if (callNews != null)
            callNews.enqueue(new Callback<NewsResponce>() {
                @Override
                public void onResponse(Call<NewsResponce> call, final Response<NewsResponce> response) {
                    if (response.body() != null) {
                        if (response.body().getTotalResults() > articleList.size()) {
                            articleList.addAll(response.body().getArticles());
                            newsAdapter.notifyDataSetChanged();
                            ++currentPage;
                        }
                    } else
                        articleList.clear();

                    Log.d(TAG, articleList.size() + "");
                }

                @Override
                public void onFailure(Call<NewsResponce> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
    }

    @Override
    public void LoadNextItems() {
        LoadItems();
    }
}

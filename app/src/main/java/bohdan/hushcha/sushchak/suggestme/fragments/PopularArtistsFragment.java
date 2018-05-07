package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.ArtistAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.MusicClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.MusicApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.MusicTag;
import bohdan.hushcha.sushchak.suggestme.rest.responces.music.MusicResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularArtistsFragment extends Fragment implements LoadNextItems {

    private final String TAG = "PopularArtistsFragment";

    @BindView(R.id.rvArtistList)
    RecyclerView rvArtist;

    @BindView(R.id.tvArtistTag)
    TextView tvArtistTag;


    private MusicApiInterface musicApi;

    private ArtistAdapter adapter;
    private List<Artist> artists;

    private String TagName;
    private Integer CurrentPage;

    private InteractionListener mListener;

    public PopularArtistsFragment() {
        CurrentPage = 1;
        musicApi = MusicClient.getClient().create(MusicApiInterface.class);

    }

    public static PopularArtistsFragment getInstance() {
        PopularArtistsFragment fragment = new PopularArtistsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_artists, container, false);
        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    private void Init() {
        artists = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new ArtistAdapter(artists, this, mListener);

        rvArtist.setLayoutManager(layoutManager);
        rvArtist.setAdapter(adapter);
    }

    @OnClick(R.id.tvArtistTag)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tvArtistTag:

                Call<MusicResponce> call = musicApi.GetTopMusicTags(MusicClient.API_KEY, "json");

                Log.d(TAG, call.request().url().toString());

                call.enqueue(new Callback<MusicResponce>() {
                    @Override
                    public void onResponse(Call<MusicResponce> call, Response<MusicResponce> response) {
                        ActionChooseArtistTag(response.body().getTopTagsResponce().getTags());
                    }

                    @Override
                    public void onFailure(Call<MusicResponce> call, Throwable t) {

                    }
                });

                break;
        }
    }

    private void ActionChooseArtistTag(final List<MusicTag> tagList) {
        if (tagList == null)
            return;

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.dialog_title_choose_country));

        final List<String> TagNames = new ArrayList<>();


        for (MusicTag source : tagList) {
            TagNames.add(source.getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(TagNames);

        dialog.setNegativeButton(getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (TagName == null) {
                    TagName = TagNames.get(i);
                    tvArtistTag.setText(TagName);
                    CurrentPage = 1;
                    ActionViewArtist(false);
                } else if (!TagName.equals(TagNames.get(i))) {
                    TagName = TagNames.get(i);
                    tvArtistTag.setText(TagName);
                    CurrentPage = 1;
                    ActionViewArtist(false);
                }
            }
        });

        dialog.show();
    }

    private void ActionViewArtist(boolean isLoadNext) {
        if (TagName == null)
            return;

        if (!isLoadNext) {
            artists.clear();
            adapter.notifyDataSetChanged();
        }

        Call<MusicResponce> call = musicApi.GetTopArtist(MusicClient.API_KEY, TagName, CurrentPage, "json");

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<MusicResponce>() {
            @Override
            public void onResponse(Call<MusicResponce> call, Response<MusicResponce> response) {
                if (response.body() != null) {
                    if (response.body().getTopArtistResponce() != null) {
                        artists.addAll(response.body().getTopArtistResponce().getArtists());
                        ++CurrentPage;
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        artists.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
                else {
                    artists.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MusicResponce> call, Throwable t) {

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
        ActionViewArtist(true);
    }
}

package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import bohdan.hushcha.sushchak.suggestme.adapters.SimpleDividerItem;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.MusicTag;
import bohdan.hushcha.sushchak.suggestme.rest.responces.music.MusicResponce;
import retrofit2.Call;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.TrackAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.rest.clients.MusicClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.MusicApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Track;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;


public class PopularTracksFragment extends Fragment implements LoadNextItems {

    public final String TAG = "PopularTracksFragment";
    private InteractionListener mListener;

    private MusicApiInterface musicApi;

    @BindView(R.id.rvTrackList)
    RecyclerView rvTracks;

    @BindView(R.id.tvTag)
    TextView tvTag;

    private TrackAdapter adapter;
    private List<Track> tracks;

    private String TagName;
    private Integer CurrentPage;

    public PopularTracksFragment() {

        musicApi = MusicClient.getClient().create(MusicApiInterface.class);
        tracks = new ArrayList<>();
        CurrentPage = 1;
    }

    public static PopularTracksFragment getInstance() {
        PopularTracksFragment fragment = new PopularTracksFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_tracks, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        adapter = new TrackAdapter(tracks, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvTracks.setLayoutManager(layoutManager);
        rvTracks.setAdapter(adapter);
        rvTracks.addItemDecoration(new SimpleDividerItem(getContext()));
    }

    @OnClick(R.id.tvTag)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tvTag:

                Call<MusicResponce> call = musicApi.GetTopMusicTags(MusicClient.API_KEY, "json");

                Log.d(TAG, call.request().url().toString());

                call.enqueue(new Callback<MusicResponce>() {
                    @Override
                    public void onResponse(Call<MusicResponce> call, Response<MusicResponce> response) {
                        ActionChooseTag(response.body().getTopTagsResponce().getTags());
                    }

                    @Override
                    public void onFailure(Call<MusicResponce> call, Throwable t) {

                    }
                });

                break;
        }
    }

    /**
     * View dialogs with list of music tags
     * @param tagList list of music tags
     */
    private void ActionChooseTag(final List<MusicTag> tagList) {
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

                if (TagName == null){
                    TagName = TagNames.get(i);
                    tvTag.setText(TagName);
                    CurrentPage = 1;
                    ActionViewTracks(false);
                }
                else if(!TagName.equals(TagNames.get(i))){
                    TagName = TagNames.get(i);
                    tvTag.setText(TagName);
                    CurrentPage = 1;
                    ActionViewTracks(false);
                }
            }
        });

        dialog.show();
    }


    /**
     * get data from server by tags and view list of tracks
     * @param isLoadNext if is true load next items else clear list and load items
     */
    private void ActionViewTracks(boolean isLoadNext){
        if(TagName == null)
            return;

        if(!isLoadNext){
            tracks.clear();
            adapter.notifyDataSetChanged();
        }

        Call<MusicResponce> call = musicApi.GetTopTracks(MusicClient.API_KEY, TagName, CurrentPage, "json");

        call.enqueue(new Callback<MusicResponce>() {
            @Override
            public void onResponse(Call<MusicResponce> call, Response<MusicResponce> response) {

                tracks.addAll(response.body().getTracksResponce().getTracks());
                ++CurrentPage;
                adapter.notifyDataSetChanged();
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
        ActionViewTracks(true);
    }
}

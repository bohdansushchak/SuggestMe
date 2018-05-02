package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.adapters.AlbumsAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.LoadNextItems;
import bohdan.hushcha.sushchak.suggestme.rest.clients.MusicClient;
import bohdan.hushcha.sushchak.suggestme.rest.interfaces.MusicApiInterface;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Album;
import bohdan.hushcha.sushchak.suggestme.rest.responces.music.MusicResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopAlbumsByArtistFragment extends Fragment implements LoadNextItems {

    public final String TAG = "TopAlbumsBy";

    private InteractionListener mListener;
    private MusicApiInterface musicApi;

    private List<Album> albums;
    private AlbumsAdapter adapter;
    private Integer CurrentPage;
    private String ArtistName;

    @BindView(R.id.rvAlbums)
    RecyclerView rvAlbums;

    @BindView(R.id.edArtist)
    EditText edArtist;

    public TopAlbumsByArtistFragment() {
        albums = new ArrayList<>();
        musicApi = MusicClient.getClient().create(MusicApiInterface.class);
        CurrentPage = 1;
    }

    public static TopAlbumsByArtistFragment newInstance() {
        TopAlbumsByArtistFragment fragment = new TopAlbumsByArtistFragment();
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
        View view = inflater.inflate(R.layout.fragment_top_albums_by_artist, container, false);

        ButterKnife.bind(this, view);
        Init();
        return view;
    }

    private void Init() {

        adapter = new AlbumsAdapter(albums, this, mListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvAlbums.setAdapter(adapter);
        rvAlbums.setLayoutManager(layoutManager);

        edArtist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArtistName = charSequence.toString();
                ActionViewAlbums(ArtistName, false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private void ActionViewAlbums(String artistName, boolean isLoadNext) {

        if (artistName == null)
            return;

        if (!isLoadNext) {
            albums.clear();
            adapter.notifyDataSetChanged();
        }

        Call<MusicResponce> call = musicApi.GetTopAlbumsByArtist(MusicClient.API_KEY, artistName, CurrentPage, "json");

        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<MusicResponce>() {
            @Override
            public void onResponse(Call<MusicResponce> call, Response<MusicResponce> response) {
                if (response.body().getTopAlbumsResponce() != null) {
                    List<Album> newAlbums = response.body().getTopAlbumsResponce().getAlbums();

                    if (newAlbums != null) {
                        albums.addAll(newAlbums);
                        adapter.notifyDataSetChanged();
                        ++CurrentPage;
                    }
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
        ActionViewAlbums(ArtistName, true);
    }
}

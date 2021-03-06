package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import bohdan.hushcha.sushchak.suggestme.rest.responces.music.MusicResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicApiInterface {

    @GET("?method=tag.getTopTags")
    Call<MusicResponce> GetTopMusicTags(@Query("api_key") String ApiKey,
                                        @Query("format") String Format);

    @GET("?method=tag.gettoptracks")
    Call<MusicResponce> GetTopTracks(@Query("api_key") String ApiKey,
                                     @Query("tag") String Tag,
                                     @Query("page") Integer page,
                                     @Query("format") String Format);

    @GET("?method=tag.gettopartists")
    Call<MusicResponce> GetTopArtist(@Query("api_key") String ApiKey,
                                     @Query("tag") String Tag,
                                     @Query("page") Integer page,
                                     @Query("format") String Format);

    @GET("?method=artist.gettopalbums")
    Call<MusicResponce> GetTopAlbumsByArtist(@Query("api_key") String ApiKey,
                                     @Query("artist") String Artist,
                                     @Query("page") Integer page,
                                     @Query("format") String Format);
}

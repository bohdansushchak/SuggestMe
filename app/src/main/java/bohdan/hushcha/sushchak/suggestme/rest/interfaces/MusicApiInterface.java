package bohdan.hushcha.sushchak.suggestme.rest.interfaces;

import bohdan.hushcha.sushchak.suggestme.rest.responces.Music.MusicResponce;
import bohdan.hushcha.sushchak.suggestme.rest.responces.Music.TopTagsResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MusicApiInterface {

    @GET("?method=tag.getTopTags")
    Call<MusicResponce> GetTopMusicTags(@Query("api_key") String ApiKey,
                                        @Query("format") String Format);

    @GET("?method=tag.gettoptracks")
    Call<MusicResponce> GetTopTracks(@Query("api_key") String ApiKey,
                                       @Query("tag") String Tag,
                                       @Query("format") String Format);
}

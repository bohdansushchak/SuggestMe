package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

public class MusicResponce {

    @SerializedName("toptags")
    private TopTagsResponce topTagsResponce;

    @SerializedName("tracks")
    private TracksResponce tracksResponce;

    @SerializedName("topartists")
    private TopArtistResponce topArtistResponce;

    @SerializedName("topalbums")
    private TopAlbumsResponce topAlbumsResponce;

    public MusicResponce(TopAlbumsResponce topAlbumsResponce) {
        this.topAlbumsResponce = topAlbumsResponce;
    }

    public TopArtistResponce getTopArtistResponce() {
        return topArtistResponce;
    }

    public void setTopArtistResponce(TopArtistResponce topArtistResponce) {
        this.topArtistResponce = topArtistResponce;
    }

    public MusicResponce(TopArtistResponce topArtistResponce) {

        this.topArtistResponce = topArtistResponce;
    }

    public MusicResponce(TracksResponce tracksResponce) {
        this.tracksResponce = tracksResponce;
    }

    public MusicResponce(TopTagsResponce topTagsResponce, TracksResponce tracksResponce) {

        this.topTagsResponce = topTagsResponce;
        this.tracksResponce = tracksResponce;
    }

    public MusicResponce(TopTagsResponce topTagsResponce) {
        this.topTagsResponce = topTagsResponce;
    }

    public TopTagsResponce getTopTagsResponce() {
        return topTagsResponce;
    }

    public void setTopTagsResponce(TopTagsResponce topTagsResponce) {
        this.topTagsResponce = topTagsResponce;
    }

    public TracksResponce getTracksResponce() {
        return tracksResponce;
    }

    public void setTracksResponce(TracksResponce tracksResponce) {
        this.tracksResponce = tracksResponce;
    }

    public TopAlbumsResponce getTopAlbumsResponce() {
        return topAlbumsResponce;
    }

    public void setTopAlbumsResponce(TopAlbumsResponce topAlbumsResponce) {
        this.topAlbumsResponce = topAlbumsResponce;
    }
}

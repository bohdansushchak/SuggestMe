package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Track;

public class TracksResponce {

    @SerializedName("track")
    public List<Track> Tracks;

    @SerializedName("@attr")
    public Attr Attr;

    public TracksResponce(List<Track> tracks, bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr attr) {
        this.Tracks = tracks;
        this.Attr = attr;
    }

    public List<Track> getTracks() {
        return Tracks;
    }

    public void setTracks(List<Track> tracks) {
        Tracks = tracks;
    }

    public bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr getAttr() {
        return Attr;
    }

    public void setAttr(bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr attr) {
        Attr = attr;
    }
}

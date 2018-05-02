package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Artist;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr;

public class TopArtistResponce {

    @SerializedName("artist")
    private List<Artist> artists;

    @SerializedName("@attr")
    private Attr attr;

    public TopArtistResponce(List<Artist> artists, Attr attr) {
        this.artists = artists;
        this.attr = attr;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}

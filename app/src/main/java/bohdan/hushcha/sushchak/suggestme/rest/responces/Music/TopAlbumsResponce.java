package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Album;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr;

public class TopAlbumsResponce {

    @SerializedName("album")
    private List<Album> Albums;

    @SerializedName("@attr")
    private Attr Attr;

    public TopAlbumsResponce(List<Album> albums, Attr attr) {
        this.Albums = albums;
        this.Attr = attr;
    }

    public List<Album> getAlbums() {
        return Albums;
    }

    public void setAlbum(List<Album> album) {
        Albums = album;
    }

    public Attr getAttr() {
        return Attr;
    }

    public void setAttr(Attr attr) {
        Attr = attr;
    }
}

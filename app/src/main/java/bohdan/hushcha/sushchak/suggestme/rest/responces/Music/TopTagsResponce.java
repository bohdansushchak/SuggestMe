package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.Music.Attr;
import bohdan.hushcha.sushchak.suggestme.rest.models.Music.MusicTag;

public class TopTagsResponce {

    @SerializedName("@attr")
    private Attr attr;

    @SerializedName("tag")
    private List<MusicTag> Tags;


    public TopTagsResponce(Attr attr, List<MusicTag> tags) {
        this.attr = attr;
        Tags = tags;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public List<MusicTag> getTags() {
        return Tags;
    }

    public void setTags(List<MusicTag> tags) {
        Tags = tags;
    }

}

package bohdan.hushcha.sushchak.suggestme.rest.responces.Music;

import com.google.gson.annotations.SerializedName;

public class MusicResponce {

    @SerializedName("toptags")
    private TopTagsResponce topTagsResponce;

    public MusicResponce(TopTagsResponce topTagsResponce) {
        this.topTagsResponce = topTagsResponce;
    }

    public TopTagsResponce getTopTagsResponce() {
        return topTagsResponce;
    }

    public void setTopTagsResponce(TopTagsResponce topTagsResponce) {
        this.topTagsResponce = topTagsResponce;
    }
}

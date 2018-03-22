package bohdan.hushcha.sushchak.suggestme.rest.responces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bohdan.hushcha.sushchak.suggestme.rest.models.NewsSource;


public class SourcesResponce {

    @SerializedName("status")
    private String Status;

    @SerializedName("sources")
    private List<NewsSource> Sources;

    public SourcesResponce(String status, List<NewsSource> sources) {
        Status = status;
        Sources = sources;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<NewsSource> getSources() {
        return Sources;
    }

    public void setSources(List<NewsSource> sources) {
        Sources = sources;
    }
}



package bohdan.hushcha.sushchak.suggestme.rest.models.Music;

import com.google.gson.annotations.SerializedName;

public class MusicTag {

    @SerializedName("name")
    private String Name;

    @SerializedName("count")
    private String Count;

    @SerializedName("reach")
    private String Reach;

    public MusicTag(String name, String count, String reach) {
        Name = name;
        Count = count;
        Reach = reach;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getReach() {
        return Reach;
    }

    public void setReach(String reach) {
        Reach = reach;
    }
}

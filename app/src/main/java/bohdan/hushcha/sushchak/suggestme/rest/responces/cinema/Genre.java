package bohdan.hushcha.sushchak.suggestme.rest.responces.cinema;

import com.google.gson.annotations.SerializedName;

class Genre {

    @SerializedName("id")
    private Integer Id;

    @SerializedName("name")
    private String Name;

    public Genre(Integer id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

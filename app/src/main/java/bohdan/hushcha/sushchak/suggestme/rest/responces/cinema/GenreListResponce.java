package bohdan.hushcha.sushchak.suggestme.rest.responces.cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GenreListResponce {

    @SerializedName("genres")
    Map<Integer, String> Genres;

    public GenreListResponce(Map<Integer, String> genres) {
        Genres = genres;
    }

    public Map<Integer, String> getGenres() {
        return Genres;
    }

    public void setGenres(Map<Integer, String> genres) {
        Genres = genres;
    }
}

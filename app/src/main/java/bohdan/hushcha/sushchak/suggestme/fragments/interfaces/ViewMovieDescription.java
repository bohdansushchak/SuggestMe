package bohdan.hushcha.sushchak.suggestme.fragments.interfaces;

/**
 * Interface for get click by movie item and view more info about movie
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public interface ViewMovieDescription {

    /**
     * Method to view more info about movie
     *
     * @param movieId id movie in api database
     */
    void ViewMovieDetails(String movieId);
}

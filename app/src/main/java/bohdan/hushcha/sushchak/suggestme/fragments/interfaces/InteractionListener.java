package bohdan.hushcha.sushchak.suggestme.fragments.interfaces;

/**
 * Interface for get clicks by item in for recycler view
 *
 * @param <T> type data
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public interface InteractionListener<T> {

    /**
     * Click method
     *
     * @param parameter data from item
     */
    void OnClick(T parameter);

    /**
     * Long click method
     *
     * @param parameter data from item
     */
    void OnLongClick(T parameter);
}

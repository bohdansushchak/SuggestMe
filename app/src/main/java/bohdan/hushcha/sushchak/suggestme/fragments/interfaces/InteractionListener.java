package bohdan.hushcha.sushchak.suggestme.fragments.interfaces;

public interface InteractionListener<T> {

    void OnClick(T parameter);

    void OnLongClick(T parameter);
}

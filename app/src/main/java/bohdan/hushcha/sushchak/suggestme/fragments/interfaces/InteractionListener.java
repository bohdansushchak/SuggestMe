package bohdan.hushcha.sushchak.suggestme.fragments.interfaces;

import butterknife.OnClick;

public interface InteractionListener<T> {

    void OnClick(T parameter);

    void OnLongClick(T parametr);
}

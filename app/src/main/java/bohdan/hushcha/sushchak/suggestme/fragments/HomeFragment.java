package bohdan.hushcha.sushchak.suggestme.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bohdan.hushcha.sushchak.suggestme.R;

public class HomeFragment extends Fragment {

    View view;

    public HomeFragment() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {

        //str_name = getArguments().getString("name");
        //str_disname = getArguments().getString("dish");
        //str_des = getArguments().getString("des");
        //str_imagename = getArguments().getString("image");
        //tv_dishname = (TextView) view.findViewById(R.id.tv_dishname);
        //tv_description = (TextView) view.findViewById(R.id.tv_description);
        //iv_image = (ImageView) view.findViewById(R.id.iv_image);


        //int resourceImage = getActivity().getResources().getIdentifier(str_imagename, "drawable", getActivity().getPackageName());
        //iv_image.setImageResource(resourceImage);
    }
}

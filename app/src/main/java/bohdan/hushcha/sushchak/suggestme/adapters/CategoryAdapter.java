package bohdan.hushcha.sushchak.suggestme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.activities.MainActivity;
import bohdan.hushcha.sushchak.suggestme.models.Category;

public class CategoryAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return categories.get(i).getCategoryItems().size();
    }

    @Override
    public Object getGroup(int i) {
        return categories.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return categories.get(i).getCategoryItems().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter_header, null);
        }
        TextView tvChild = (TextView) view.findViewById(R.id.tvTitle);
        tvChild.setText(categories.get(i).getCategoryName());
        return view;
    }

    @Override
    public View getChildView(final int group, final int child, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter_childview, null);
        }

        TextView tvChild = (TextView) view.findViewById(R.id.tvTitle);

        tvChild.setText(categories.get(group).getCategoryItems().get(child));
        tvChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).clickItemNavigationDrawer(group, child);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

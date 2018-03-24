package bohdan.hushcha.sushchak.suggestme.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.Services.AuthUtils;
import bohdan.hushcha.sushchak.suggestme.adapters.CategoryAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.HomeFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherDayFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.WeatherWeekFragment;
import bohdan.hushcha.sushchak.suggestme.models.Category;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements WeatherDayFragment.OnFragmentInteractionListener {

    final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private CategoryAdapter categoryAdapter;
    private HomeFragment homeFragment;
    private ArrayList<Category> categories;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.ev_menu) ExpandableListView evList;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvEmail) TextView tvUserEmail;
    @BindView(R.id.rl_menu) RelativeLayout rlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        this.mAuth = FirebaseAuth.getInstance();

        categories = new ArrayList<>();
        InitNavigationItems();

        init();
    }

    private void init() {

        categoryAdapter = new CategoryAdapter(MainActivity.this, categories);
        evList.setAdapter(categoryAdapter);
        evList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });

        setExpandableListViewHeightBasedOnChildren(evList);

        homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", categories.get(0).getCategoryName());
        tvTitle.setText(categories.get(0).getCategoryName());

        homeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment, "HomeFragment")
                .addToBackStack("null").commit();

        rlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
       /* if (height < 10)
            height = 200;*/
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static void setExpandableListViewHeightBasedOnChildren(ExpandableListView expandableListView) {
        CategoryAdapter adapter = (CategoryAdapter) expandableListView.getExpandableListAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = expandableListView.getPaddingTop() + expandableListView.getPaddingBottom();
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            View groupItem = adapter.getGroupView(i, false, null, expandableListView);
            groupItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (expandableListView.isGroupExpanded(i)) {
                for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                    View listItem = adapter.getChildView(i, j, false, null, expandableListView);
                    listItem.setLayoutParams(new ViewGroup.LayoutParams(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED));
                    listItem.measure(View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                            .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
        int height = totalHeight + expandableListView.getDividerHeight() * (adapter.getGroupCount() - 1);

        if (height < 10)
            height = 100;
        params.height = height;
        expandableListView.setLayoutParams(params);
        expandableListView.requestLayout();
    }

    public void clickItemNavigationDrawer(int group, int child) {

        WeatherDayFragment fragment = WeatherDayFragment.newInstance(new Date());



        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "HomeFragment")
                .addToBackStack("null").commit();
        drawerLayout.closeDrawer(Gravity.LEFT);

        tvTitle.setText(categories.get(group).getCategoryName());



        /*
        homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", categories.get(group).getCategoryName());
        homeFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment, "HomeFragment")
                .addToBackStack("null").commit();
        drawerLayout.closeDrawer(Gravity.LEFT);

        tvTitle.setText(categories.get(group).getCategoryName());
        */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }

    private void InitNavigationItems() {

        categories.add(new Category(getString(R.string.category_cinema),
                Arrays.asList(getResources().getStringArray(R.array.cinema_sub_items))));

        categories.add(new Category(getString(R.string.category_music),
                Arrays.asList(getResources().getStringArray(R.array.music_sub_items))));

        categories.add(new Category(getString(R.string.category_weather),
                Arrays.asList(getResources().getStringArray(R.array.weather_sub_items))));

        categories.add(new Category(getString(R.string.category_news),
                Arrays.asList(getResources().getStringArray(R.array.news_sub_items))));

        categories.add(new Category(getString(R.string.category_crypto),
                Arrays.asList(getResources().getStringArray(R.array.weather_sub_items))));

        categories.add(new Category(getString(R.string.category_cooking),
                Arrays.asList(getResources().getStringArray(R.array.cooking_sub_items))));
    }

    @OnClick(R.id.btnSignOut)
    public void SignOut(View view){

        new AuthUtils(MainActivity.this).SignOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        MainActivity.this.finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            String email = mAuth.getCurrentUser().getUid();

            tvUserEmail.setText(email);
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            MainActivity.this.finish();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

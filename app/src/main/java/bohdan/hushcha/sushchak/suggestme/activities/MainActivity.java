package bohdan.hushcha.sushchak.suggestme.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.fragments.MovieDescriptionFragment;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.ViewMovieDescription;
import bohdan.hushcha.sushchak.suggestme.services.AuthService;
import bohdan.hushcha.sushchak.suggestme.adapters.CategoryAdapter;
import bohdan.hushcha.sushchak.suggestme.fragments.interfaces.InteractionListener;
import bohdan.hushcha.sushchak.suggestme.models.Category;
import bohdan.hushcha.sushchak.suggestme.utils.SwitchFragmentUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements InteractionListener<String>, ViewMovieDescription {

    final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.ev_menu)
    ExpandableListView evList;

    @BindView(R.id.tvHeaderTitle)
    TextView tvHeaderTitle;

    @BindView(R.id.tvEmail)
    TextView tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        this.mAuth = FirebaseAuth.getInstance();
        this.categories = new ArrayList<>();

        InitNavigationItems();

        init();
    }

    /**
     * Function to initialize first fragment in main activity
     */
    private void init() {

        this.categoryAdapter = new CategoryAdapter(MainActivity.this, categories);
        this.evList.setAdapter(categoryAdapter);
        this.evList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });

        setExpandableListViewHeightBasedOnChildren(evList);

        clickItemNavigationDrawer(3, 0);
    }

    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
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

    /**
     * Method to initialize expandable list view
     *
     * @param expandableListView list view with categories
     */
    public void setExpandableListViewHeightBasedOnChildren(ExpandableListView expandableListView) {
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

    /**
     * Function change fragment in main screen
     *
     * @param group category id
     * @param child item id in catagory
     */
    @SuppressLint("RtlHardcoded")
    public void clickItemNavigationDrawer(int group, int child) {
        Fragment fragment = SwitchFragmentUtils.GetFragment(group, child);

        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack("null").commit();

            tvHeaderTitle.setText(categories.get(group).getCategoryItems().get(child));
        }

        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    /**
     * Function called when user click back button
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, getSupportFragmentManager().getFragments().size() + "");

        super.onBackPressed();

        if (getSupportFragmentManager().getFragments().size() <= 1)
            finish();
    }

    /**
     * Function to initialize items in navigation drawer menu
     */
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
                Arrays.asList(getResources().getStringArray(R.array.crypto_sub_items))));

        categories.add(new Category(getString(R.string.category_cooking),
                Arrays.asList(getResources().getStringArray(R.array.cooking_sub_items))));
    }

    /**
     * Function to get all clicked buttons
     *
     * @param view item which did click
     */
    @OnClick({R.id.btnSignOut, R.id.rlMenu})
    public void SignOut(View view) {

        switch (view.getId()) {
            case R.id.rlMenu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.btnSignOut:
                new AuthService(MainActivity.this).SignOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();
                break;
        }
    }

    /**
     * Method to check if user logined if not
     * back to login screen
     */
    private void CheckIfUserLogined() {
        if (mAuth.getCurrentUser() != null) {
            String email = mAuth.getCurrentUser().getEmail();

            this.tvUserEmail.setText(email);
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            MainActivity.this.finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckIfUserLogined();
    }

    /**
     * Method call when user click on item in news fragment
     * and view the article in web browser
     *
     * @param url url to source
     */
    @Override
    public void OnClick(String url) {
        Intent viewArticleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(viewArticleIntent);
    }

    @Override
    public void OnLongClick(String parameter) {

    }

    /**
     * View movie details fragment
     *
     * @param movieId id movie in database
     */
    @Override
    public void ViewMovieDetails(String movieId) {
        Fragment fragment = MovieDescriptionFragment.newInstance(movieId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack("null").commit();

        tvHeaderTitle.setText("Movie description");
    }
}

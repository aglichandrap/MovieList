package org.iblitzc0de.movielist;

import android.content.Intent;
import android.os.Bundle;

import org.iblitzc0de.movielist.MovieListFragment.Callbacks;
import org.iblitzc0de.movielist.apis.daos.MovieDao;
import org.iblitzc0de.popularlist.R;

public class MovieListActivity extends BaseActivity implements Callbacks {
    public static float ITEM_WIDTH = 0.0f;
    private static final int MAX_WIDTH_ONEPANE_COL_DP = 180;
    private static final int MAX_WIDTH_TWOPANE_COL_DP = 80;
    private boolean mTwoPane;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.tv_title) != null) {
            this.mTwoPane = true;
        }
        ITEM_WIDTH = ((float) (this.mTwoPane ? MAX_WIDTH_TWOPANE_COL_DP : MAX_WIDTH_ONEPANE_COL_DP)) * getResources().getDisplayMetrics().density;
    }

    public void onItemSelected(MovieDao movieDao) {
        if (this.mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.ARG_ITEM, movieDao);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.tv_title, fragment).commit();
            return;
        }
        Intent detailIntent = new Intent(this, MovieDetailActivity.class);
        detailIntent.putExtra(MovieDetailFragment.ARG_ITEM, movieDao);
        startActivity(detailIntent);
    }
}

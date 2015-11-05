package org.iblitzc0de.popularlist;

import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import butterknife.Bind;

import com.google.gson.Gson;

import org.iblitzc0de.popularlist.adapters.MovieListAdapter;
import org.iblitzc0de.popularlist.apis.RetrofitHelper;
import org.iblitzc0de.popularlist.apis.daos.DiscoverMovieApiDao;
import org.iblitzc0de.popularlist.apis.daos.ErrorApiDao;
import org.iblitzc0de.popularlist.apis.daos.MovieDao;
import org.iblitzc0de.popularlist.utils.Constant;
import org.iblitzc0de.popularlist.utils.MyProgressView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends BaseActivity {
    private static final int MAX_WIDTH_COL_DP = 185;
    private static final String STATE_SORT = "stateSort";
    private MovieListAdapter mAdapter;
    private List<MovieDao> mDataset = new ArrayList();
    private StaggeredGridLayoutManager mLayoutManager;
    @Bind({2131492967})
    MyProgressView mProgressView;
    @Bind({2131492966})
    RecyclerView mRecyclerView;
    private String mSort = Constant.SORT_POPULAR;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mRecyclerView.setVisibility(8);
        if (savedInstanceState != null) {
            this.mSort = savedInstanceState.getString(STATE_SORT);
        }
        this.mLayoutManager = new StaggeredGridLayoutManager(2, 1);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new MovieListAdapter(this.mDataset);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (VERSION.SDK_INT < 16) {
                    MainActivity.this.mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    MainActivity.this.mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                float cardViewWidth = 185.0f * MainActivity.this.getResources().getDisplayMetrics().density;
                MainActivity.this.mLayoutManager.setSpanCount(Math.max(2, (int) Math.floor((double) (((float) MainActivity.this.mRecyclerView.getMeasuredWidth()) / cardViewWidth))));
                MainActivity.this.mLayoutManager.requestLayout();
            }
        });
        this.mProgressView.setRetryClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.callApi();
            }
        });
        callApi();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_sort) {
            return super.onOptionsItemSelected(item);
        }
        showSortDialog();
        return true;
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_SORT, this.mSort);
    }

    private void showSortDialog() {
        int i = 0;
        Builder builder = new Builder(this);
        Builder title = builder.setTitle(R.string.main_sort_by);
        CharSequence[] charSequenceArr = new String[]{getString(R.string.main_sort_most_popular), getString(R.string.main_sort_highest_rated)};
        if (!this.mSort.equals(Constant.SORT_POPULAR)) {
            i = 1;
        }
        title.setSingleChoiceItems(charSequenceArr, i, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.mSort = which == 0 ? Constant.SORT_POPULAR : Constant.SORT_HIGHEST_RATED;
                MainActivity.this.callApi();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void callApi() {
        RetrofitHelper.getInstance().getService().discoverMovie(Constant.MOVIEDB_APIKEY, this.mSort).enqueue(new Callback<DiscoverMovieApiDao>() {
            public void onResponse(Response<DiscoverMovieApiDao> response) {
                if (response.body() != null) {
                    MainActivity.this.mDataset.clear();
                    MainActivity.this.mDataset.addAll(((DiscoverMovieApiDao) response.body()).getResults());
                    MainActivity.this.mAdapter.notifyDataSetChanged();
                    MainActivity.this.mRecyclerView.setVisibility(0);
                    MainActivity.this.mProgressView.stopAndGone();
                    return;
                }
                try {
                    MainActivity.this.mProgressView.stopAndError(((ErrorApiDao) new Gson().fromJson(response.errorBody().string().toString(), ErrorApiDao.class)).getStatus_message(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                    MainActivity.this.mProgressView.stopAndError(e.getMessage(), true);
                }
            }

            public void onFailure(Throwable t) {
                MainActivity.this.mProgressView.stopAndError(t.getMessage(), true);
            }
        });
    }
}

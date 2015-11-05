package org.iblitzc0de.movielist;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;

import org.iblitzc0de.movielist.adapters.MovieListAdapter;
import org.iblitzc0de.movielist.apis.RetrofitHelper;
import org.iblitzc0de.movielist.apis.daos.BaseListApiDao;
import org.iblitzc0de.movielist.apis.daos.ErrorApiDao;
import org.iblitzc0de.movielist.apis.daos.MovieDao;
import org.iblitzc0de.movielist.provider.movie.MovieCursor;
import org.iblitzc0de.movielist.provider.movie.MovieSelection;
import org.iblitzc0de.movielist.utils.MyProgressView;
import org.iblitzc0de.popularlist.R;
import org.iblitzc0de.popularlist.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;

public class MovieListFragment extends Fragment {
    private static final String STATE_SORT = "stateSort";
    private static Callbacks sDummyCallbacks = new Callbacks() {
        public void onItemSelected(MovieDao movieDao) {
        }
    };
    private MovieListAdapter mAdapter;
    private String[] mArrSort;
    private Callbacks mCallbacks = sDummyCallbacks;
    private List<MovieDao> mDataset = new ArrayList();
    private StaggeredGridLayoutManager mLayoutManager;
    @Bind({2131492985})
    MyProgressView mProgressView;
    @Bind({2131492984})
    RecyclerView mRecyclerView;
    private int mSort = 0;

    public interface Callbacks {
        void onItemSelected(MovieDao movieDao);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mArrSort = new String[]{getString(R.string.main_sort_most_popular), getString(R.string.main_sort_highest_rated), getString(R.string.main_sort_favorites)};
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_sort) {
            return super.onOptionsItemSelected(item);
        }
        showSortDialog();
        return true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notification_media_action, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_SORT)) {
            this.mSort = savedInstanceState.getInt(STATE_SORT);
        }
        this.mRecyclerView.setVisibility(8);
        this.mLayoutManager = new StaggeredGridLayoutManager(2, 1);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new MovieListAdapter(this.mDataset, this.mCallbacks);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mProgressView.setRetryClickListener(new OnClickListener() {
            public void onClick(View v) {
                MovieListFragment.this.callApi();
            }
        });
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MovieListFragment.this.mRecyclerView.post(new Runnable() {
                    public void run() {
                        MovieListFragment.this.mLayoutManager.setSpanCount(Math.max(2, (int) Math.floor((double) (((float) MovieListFragment.this.mRecyclerView.getMeasuredWidth()) / MovieListActivity.ITEM_WIDTH))));
                        MovieListFragment.this.mLayoutManager.requestLayout();
                    }
                });
            }
        });
        callApi();
    }

    public void onResume() {
        super.onResume();
        if (this.mSort == 2) {
            queryFavoritesData();
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Callbacks) {
            this.mCallbacks = (Callbacks) activity;
            return;
        }
        throw new IllegalStateException("Activity must implement fragment's callbacks.");
    }

    public void onDetach() {
        super.onDetach();
        this.mCallbacks = sDummyCallbacks;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SORT, this.mSort);
    }

    private void showSortDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(R.string.main_sort_by).setSingleChoiceItems(this.mArrSort, this.mSort, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MovieListFragment.this.mSort = which;
                if (which == 2) {
                    MovieListFragment.this.queryFavoritesData();
                } else {
                    MovieListFragment.this.callApi();
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void queryFavoritesData() {
        this.mDataset.clear();
        MovieCursor c = new MovieSelection().query(getActivity().getContentResolver());
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                this.mDataset.add(new MovieDao(c));
            }
            c.close();
            this.mAdapter.notifyDataSetChanged();
            this.mRecyclerView.setVisibility(0);
            this.mProgressView.stopAndGone();
            return;
        }
        this.mRecyclerView.setVisibility(8);
        this.mProgressView.stopAndError(getString(R.string.main_no_favorites), false);
    }

    private void callApi() {
        String sortMode = null;
        if (this.mArrSort[this.mSort].equals(getString(R.string.main_sort_most_popular))) {
            sortMode = Constant.SORT_POPULAR;
        } else if (this.mArrSort[this.mSort].equals(getString(R.string.main_sort_highest_rated))) {
            sortMode = Constant.SORT_HIGHEST_RATED;
        }
        this.mProgressView.startProgress();
        RetrofitHelper.getInstance().getService().discoverMovie(Constant.MOVIEDB_APIKEY, sortMode).enqueue(new Callback<BaseListApiDao<MovieDao>>() {
            public void onResponse(Response<BaseListApiDao<MovieDao>> response) {
                if (response.body() != null) {
                    MovieListFragment.this.mDataset.clear();
                    MovieListFragment.this.mDataset.addAll(((BaseListApiDao) response.body()).getResults());
                    MovieListFragment.this.mAdapter.notifyDataSetChanged();
                    MovieListFragment.this.mRecyclerView.setVisibility(0);
                    MovieListFragment.this.mProgressView.stopAndGone();
                    return;
                }
                try {
                    MovieListFragment.this.mProgressView.stopAndError(((ErrorApiDao) new Gson().fromJson(response.errorBody().string().toString(), ErrorApiDao.class)).getStatus_message(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                    MovieListFragment.this.mProgressView.stopAndError(e.getMessage(), true);
                }
            }

            public void onFailure(Throwable t) {
                MovieListFragment.this.mProgressView.stopAndError(t.getMessage(), true);
            }
        });
    }
}

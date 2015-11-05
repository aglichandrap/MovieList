package org.iblitzc0de.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.Bind;

import com.squareup.picasso.Picasso;

import org.iblitzc0de.movielist.apis.daos.MovieDao;
import org.iblitzc0de.popularlist.R;
import org.iblitzc0de.popularlist.utils.Constant;

public class MovieDetailActivity extends BaseActivity {
    @Bind({2131492969})
    ImageView mIvBackdrop;

    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(1280);
     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MovieDao movieDao = (MovieDao) getIntent().getParcelableExtra(MovieDetailFragment.ARG_ITEM);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.ARG_ITEM, movieDao);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.tv_title, fragment).commit();
        }
        Picasso.with(this).load(Constant.ROOT_BACKDROP_IMAGE_URL + movieDao.getBackdrop_path()).error((int) R.color.colorPrimary).placeholder((int) R.color.grey).into(this.mIvBackdrop);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot()) {
            TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
        return true;
    }
}

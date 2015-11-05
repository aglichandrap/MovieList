package org.iblitzc0de.popularlist;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;

import com.squareup.picasso.Picasso;

import org.iblitzc0de.popularlist.apis.daos.MovieDao;
import org.iblitzc0de.popularlist.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MovieDetailActivity extends BaseActivity {
    private static final String EXTRA_ITEM = "item";
    @Bind({2131492968})
    ImageView mIvBackdrop;
    @Bind({2131492969})
    ImageView mIvPoster;
    private MovieDao mMovieDao;
    @Bind({2131492971})
    TextView mTvReleaseDate;
    @Bind({2131492973})
    TextView mTvSynopsis;
    @Bind({2131492970})
    TextView mTvTitle;
    @Bind({2131492972})
    TextView mTvVoteAvg;

    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(1280);
      
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mMovieDao = (MovieDao) getIntent().getParcelableExtra(EXTRA_ITEM);
        setTitle(this.mMovieDao.getTitle());
        initLayout();
    }

    private void initLayout() {
        String formattedDate;
        this.mTvTitle.setText(this.mMovieDao.getTitle());
        try {
            formattedDate = DateFormat.format("dd MMM yyyy", new SimpleDateFormat("yyyy-MM-dd").parse(this.mMovieDao.getRelease_date())).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            formattedDate = this.mMovieDao.getRelease_date();
        }
        this.mTvReleaseDate.setText(formattedDate);
        this.mTvVoteAvg.setText(this.mMovieDao.getVote_average() + "/" + "10");
        this.mTvSynopsis.setText(this.mMovieDao.getOverview());
        Picasso.with(this).load(Constant.ROOT_POSTER_IMAGE_URL + this.mMovieDao.getPoster_path()).error((int) R.color.grey).placeholder((int) R.color.grey).into(this.mIvPoster);
        Picasso.with(this).load(Constant.ROOT_BACKDROP_IMAGE_URL + this.mMovieDao.getBackdrop_path()).error((int) R.color.colorPrimary).placeholder((int) R.color.grey).into(this.mIvBackdrop);
    }

    public static void startThisActivity(Context context, MovieDao movieDao) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, movieDao);
        context.startActivity(intent);
    }
}

package org.iblitzc0de.movielist;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.squareup.picasso.Picasso;

import org.iblitzc0de.movielist.apis.RetrofitHelper;
import org.iblitzc0de.movielist.apis.daos.BaseListApiDao;
import org.iblitzc0de.movielist.apis.daos.MovieDao;
import org.iblitzc0de.movielist.apis.daos.ReviewDao;
import org.iblitzc0de.movielist.apis.daos.VideoDao;
import org.iblitzc0de.movielist.provider.movie.MovieContentValues;
import org.iblitzc0de.movielist.provider.movie.MovieSelection;
import org.iblitzc0de.movielist.provider.review.ReviewContentValues;
import org.iblitzc0de.movielist.provider.review.ReviewCursor;
import org.iblitzc0de.movielist.provider.review.ReviewSelection;
import org.iblitzc0de.movielist.provider.video.VideoContentValues;
import org.iblitzc0de.movielist.provider.video.VideoCursor;
import org.iblitzc0de.movielist.provider.video.VideoSelection;
import org.iblitzc0de.popularlist.R;
import org.iblitzc0de.popularlist.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;

public class MovieDetailFragment extends Fragment {
    public static final String ARG_ITEM = "item";
    private boolean isFavorited = false;
    @Bind({2131492978})
    Button mBtnAddFav;
    private String mFirstVideoUrl;
    @Bind({2131492974})
    ImageView mIvPoster;
    @Bind({2131492982})
    LinearLayout mLayoutReviews;
    @Bind({2131492980})
    LinearLayout mLayoutVideos;
    private MovieDao mMovieDao;
    private List<ReviewDao> mReviewsDao = new ArrayList();
    @Bind({2131492976})
    TextView mTvReleaseDate;
    @Bind({2131492983})
    TextView mTvReviewLabel;
    @Bind({2131492979})
    TextView mTvSynopsis;
    @Bind({2131492975})
    TextView mTvTitle;
    @Bind({2131492981})
    TextView mTvVideoLabel;
    @Bind({2131492977})
    TextView mTvVoteAvg;
    private List<VideoDao> mVideosDao = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments().containsKey(ARG_ITEM)) {
            this.mMovieDao = (MovieDao) getArguments().getParcelable(ARG_ITEM);
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.iv_backdrop);
            if (appBarLayout != null) {
                appBarLayout.setTitle(this.mMovieDao.getTitle());
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_share) {
            return super.onOptionsItemSelected(item);
        }
        if (!TextUtils.isEmpty(this.mFirstVideoUrl)) {
            Intent share = new Intent("android.intent.action.SEND");
            share.setType("text/plain");
            share.putExtra("android.intent.extra.SUBJECT", this.mMovieDao.getTitle());
            share.putExtra("android.intent.extra.TEXT", this.mFirstVideoUrl);
            startActivity(Intent.createChooser(share, "Share video!"));
        }
        return true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = true;
        View rootView = inflater.inflate(R.layout.item_grid, container, false);
        ButterKnife.bind(this, rootView);
        if (this.mMovieDao != null) {
            initLayout();
            callApis(this.mMovieDao.getId());
        }
        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieId(Long.valueOf(this.mMovieDao.getId()));
        if (movieSelection.query(getActivity().getContentResolver()).getCount() <= 0) {
            z = false;
        }
        toggleButtonFav(z);
        return rootView;
    }

    private void callApis(long id) {
        callReviewsApi(id);
        callVideosApi(id);
    }

    private void callReviewsApi(long id) {
        ReviewSelection reviewSelection = new ReviewSelection();
        reviewSelection.movieMovieId(Long.valueOf(this.mMovieDao.getId()));
        ReviewCursor c = reviewSelection.query(getActivity().getContentResolver());
        if (c.getCount() > 0) {
            List<ReviewDao> reviews = new ArrayList();
            while (c.moveToNext()) {
                reviews.add(new ReviewDao(c));
            }
            initReviewsLayout(reviews);
            c.close();
            return;
        }
        RetrofitHelper.getInstance().getService().movieReviews(id, Constant.MOVIEDB_APIKEY).enqueue(new Callback<BaseListApiDao<ReviewDao>>() {
            public void onResponse(Response<BaseListApiDao<ReviewDao>> response) {
                if (response.body() != null) {
                    MovieDetailFragment.this.mReviewsDao.addAll(((BaseListApiDao) response.body()).getResults());
                    if (MovieDetailFragment.this.mReviewsDao.size() > 0) {
                        MovieDetailFragment.this.initReviewsLayout(MovieDetailFragment.this.mReviewsDao);
                    } else {
                        MovieDetailFragment.this.mTvReviewLabel.setText(R.string.main_sort_highest_rated);
                    }
                }
            }

            public void onFailure(Throwable t) {
            }
        });
    }

    private void callVideosApi(long id) {
        VideoSelection videoSelection = new VideoSelection();
        videoSelection.movieMovieId(Long.valueOf(this.mMovieDao.getId()));
        VideoCursor videoCursor = videoSelection.query(getActivity().getContentResolver());
        if (videoCursor.getCount() > 0) {
            List<VideoDao> videos = new ArrayList();
            while (videoCursor.moveToNext()) {
                videos.add(new VideoDao(videoCursor));
            }
            initVideosLayout(videos);
            videoCursor.close();
            return;
        }
        RetrofitHelper.getInstance().getService().movieVideos(id, Constant.MOVIEDB_APIKEY).enqueue(new Callback<BaseListApiDao<VideoDao>>() {
            public void onResponse(Response<BaseListApiDao<VideoDao>> response) {
                if (response.body() != null) {
                    MovieDetailFragment.this.mVideosDao.addAll(((BaseListApiDao) response.body()).getResults());
                    if (MovieDetailFragment.this.mVideosDao.size() > 0) {
                        MovieDetailFragment.this.mFirstVideoUrl = MovieDetailFragment.this.getYoutubeUrl(((VideoDao) MovieDetailFragment.this.mVideosDao.get(0)).getKey());
                        MovieDetailFragment.this.initVideosLayout(MovieDetailFragment.this.mVideosDao);
                        return;
                    }
                    MovieDetailFragment.this.mTvVideoLabel.setText(R.string.main_sort_most_popular);
                }
            }

            public void onFailure(Throwable t) {
            }
        });
    }

    private String getYoutubeUrl(String key) {
        return "https://www.youtube.com/watch?v=" + key;
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
        if (TextUtils.isEmpty(this.mMovieDao.getOverview())) {
            this.mTvSynopsis.setText("No description");
        } else {
            this.mTvSynopsis.setText(Html.fromHtml(this.mMovieDao.getOverview()));
        }
        Picasso.with(getActivity()).load(Constant.ROOT_POSTER_IMAGE_URL + this.mMovieDao.getPoster_path()).error((int) R.color.grey).placeholder((int) R.color.grey).into(this.mIvPoster);
    }

    private void initVideosLayout(List<VideoDao> videos) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < videos.size(); i++) {
            final VideoDao video = (VideoDao) videos.get(i);
            View v = inflater.inflate(R.layout.notification_template_big_media_narrow, this.mLayoutVideos, false);
            ImageView ivThumb = (ImageView) v.findViewById(R.id.end_padder);
            ((TextView) v.findViewById(R.id.progress)).setText(video.getName());
            Picasso.with(getActivity()).load("http://img.youtube.com/vi/" + video.getKey() + "/0.jpg").error((int) R.color.colorPrimary).placeholder((int) R.color.grey).into(ivThumb);
            v.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    MovieDetailFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(MovieDetailFragment.this.getYoutubeUrl(video.getKey()))));
                }
            });
            this.mLayoutVideos.addView(v);
        }
    }

    private void initReviewsLayout(List<ReviewDao> reviews) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < reviews.size(); i++) {
            ReviewDao review = (ReviewDao) reviews.get(i);
            View v = inflater.inflate(R.layout.notification_template_big_media, this.mLayoutReviews, false);
            TextView tvAuthor = (TextView) v.findViewById(R.id.info);
            ((TextView) v.findViewById(R.id.text)).setText("\"" + review.getContent() + "\"");
            tvAuthor.setText(review.getAuthor());
            this.mLayoutReviews.addView(v);
        }
    }

    @OnClick({2131492978})
    public void onClickAddFav(View v) {
        if (this.isFavorited) {
            MovieSelection movieSelection = new MovieSelection();
            movieSelection.movieId(Long.valueOf(this.mMovieDao.getId()));
            movieSelection.delete(getActivity().getContentResolver());
            VideoSelection videoSelection = new VideoSelection();
            videoSelection.movieId(this.mMovieDao.getId());
            videoSelection.delete(getActivity().getContentResolver());
            ReviewSelection reviewSelection = new ReviewSelection();
            reviewSelection.movieId(this.mMovieDao.getId());
            reviewSelection.delete(getActivity().getContentResolver());
            toggleButtonFav(false);
            return;
        }
        int i;
        MovieContentValues values = new MovieContentValues();
        values.putBackdropPath(this.mMovieDao.getBackdrop_path());
        values.putMovieId(Long.valueOf(this.mMovieDao.getId()));
        values.putOverview(this.mMovieDao.getOverview());
        values.putPosterPath(this.mMovieDao.getPoster_path());
        values.putReleaseDate(this.mMovieDao.getRelease_date());
        values.putTitle(this.mMovieDao.getTitle());
        values.putVoteAverage(Double.valueOf(this.mMovieDao.getVote_average()));
        Uri uri = values.insert(getActivity().getContentResolver());
        for (i = 0; i < this.mVideosDao.size(); i++) {
            VideoDao dao = (VideoDao) this.mVideosDao.get(i);
            VideoContentValues cv = new VideoContentValues();
            cv.putKey(dao.getKey());
            cv.putVideoId(dao.getId());
            cv.putName(dao.getName());
            cv.putSize(Integer.valueOf(dao.getSize()));
            cv.putType(dao.getType());
            cv.putMovieId(ContentUris.parseId(uri));
            cv.insert(getActivity().getContentResolver());
        }
        for (i = 0; i < this.mReviewsDao.size(); i++) {
            ReviewDao dao2 = (ReviewDao) this.mReviewsDao.get(i);
            ReviewContentValues cv2 = new ReviewContentValues();
            cv2.putMovieId(ContentUris.parseId(uri));
            cv2.putAuthor(dao2.getAuthor());
            cv2.putContent(dao2.getContent());
            cv2.putReviewId(dao2.getId());
            cv2.insert(getActivity().getContentResolver());
        }
        toggleButtonFav(true);
    }

    private void toggleButtonFav(boolean setToAdded) {
        if (setToAdded) {
            this.isFavorited = true;
            this.mBtnAddFav.setText(R.string.main_remove_from_fav);
            return;
        }
        this.isFavorited = false;
        this.mBtnAddFav.setText(R.string.main_add_to_fav);
    }
}

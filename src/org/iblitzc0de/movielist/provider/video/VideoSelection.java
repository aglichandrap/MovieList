package org.iblitzc0de.movielist.provider.video;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.iblitzc0de.movielist.provider.base.AbstractSelection;
import org.iblitzc0de.movielist.provider.movie.MovieColumns;

public class VideoSelection extends AbstractSelection<VideoSelection> {
    protected Uri baseUri() {
        return VideoColumns.CONTENT_URI;
    }

    public VideoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new VideoCursor(cursor);
    }

    public VideoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public VideoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new VideoCursor(cursor);
    }

    public VideoCursor query(Context context) {
        return query(context, null);
    }

    public VideoSelection id(long... value) {
        addEquals(VideoColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public VideoSelection idNot(long... value) {
        addNotEquals(VideoColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public VideoSelection orderById(boolean desc) {
        orderBy(VideoColumns.DEFAULT_ORDER, desc);
        return this;
    }

    public VideoSelection orderById() {
        return orderById(false);
    }

    public VideoSelection videoId(String... value) {
        addEquals(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection videoIdNot(String... value) {
        addNotEquals(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection videoIdLike(String... value) {
        addLike(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection videoIdContains(String... value) {
        addContains(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection videoIdStartsWith(String... value) {
        addStartsWith(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection videoIdEndsWith(String... value) {
        addEndsWith(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoSelection orderByVideoId(boolean desc) {
        orderBy(VideoColumns.VIDEO_ID, desc);
        return this;
    }

    public VideoSelection orderByVideoId() {
        orderBy(VideoColumns.VIDEO_ID, false);
        return this;
    }

    public VideoSelection name(String... value) {
        addEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameNot(String... value) {
        addNotEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameLike(String... value) {
        addLike(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameContains(String... value) {
        addContains(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameStartsWith(String... value) {
        addStartsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameEndsWith(String... value) {
        addEndsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection orderByName(boolean desc) {
        orderBy(VideoColumns.NAME, desc);
        return this;
    }

    public VideoSelection orderByName() {
        orderBy(VideoColumns.NAME, false);
        return this;
    }

    public VideoSelection key(String... value) {
        addEquals(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyNot(String... value) {
        addNotEquals(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyLike(String... value) {
        addLike(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyContains(String... value) {
        addContains(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyStartsWith(String... value) {
        addStartsWith(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyEndsWith(String... value) {
        addEndsWith(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection orderByKey(boolean desc) {
        orderBy(VideoColumns.KEY, desc);
        return this;
    }

    public VideoSelection orderByKey() {
        orderBy(VideoColumns.KEY, false);
        return this;
    }

    public VideoSelection size(Integer... value) {
        addEquals(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection sizeNot(Integer... value) {
        addNotEquals(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection sizeGt(int value) {
        addGreaterThan(VideoColumns.SIZE, Integer.valueOf(value));
        return this;
    }

    public VideoSelection sizeGtEq(int value) {
        addGreaterThanOrEquals(VideoColumns.SIZE, Integer.valueOf(value));
        return this;
    }

    public VideoSelection sizeLt(int value) {
        addLessThan(VideoColumns.SIZE, Integer.valueOf(value));
        return this;
    }

    public VideoSelection sizeLtEq(int value) {
        addLessThanOrEquals(VideoColumns.SIZE, Integer.valueOf(value));
        return this;
    }

    public VideoSelection orderBySize(boolean desc) {
        orderBy(VideoColumns.SIZE, desc);
        return this;
    }

    public VideoSelection orderBySize() {
        orderBy(VideoColumns.SIZE, false);
        return this;
    }

    public VideoSelection type(String... value) {
        addEquals(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeNot(String... value) {
        addNotEquals(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeLike(String... value) {
        addLike(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeContains(String... value) {
        addContains(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeStartsWith(String... value) {
        addStartsWith(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeEndsWith(String... value) {
        addEndsWith(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection orderByType(boolean desc) {
        orderBy(VideoColumns.TYPE, desc);
        return this;
    }

    public VideoSelection orderByType() {
        orderBy(VideoColumns.TYPE, false);
        return this;
    }

    public VideoSelection movieId(long... value) {
        addEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdNot(long... value) {
        addNotEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdGt(long value) {
        addGreaterThan(VideoColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(VideoColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieIdLt(long value) {
        addLessThan(VideoColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieIdLtEq(long value) {
        addLessThanOrEquals(VideoColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection orderByMovieId(boolean desc) {
        orderBy(VideoColumns.MOVIE_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieId() {
        orderBy(VideoColumns.MOVIE_ID, false);
        return this;
    }

    public VideoSelection movieMovieId(Long... value) {
        addEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieMovieIdNot(Long... value) {
        addNotEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieMovieIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieMovieIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieMovieIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection movieMovieIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public VideoSelection orderByMovieMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public VideoSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public VideoSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public VideoSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public VideoSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public VideoSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public VideoSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public VideoSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public VideoSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public VideoSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public VideoSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public VideoSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public VideoSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public VideoSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public VideoSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public VideoSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public VideoSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }
}

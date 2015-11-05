package org.iblitzc0de.movielist.provider.movie;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.iblitzc0de.movielist.provider.base.AbstractSelection;

public class MovieSelection extends AbstractSelection<MovieSelection> {
    protected Uri baseUri() {
        return MovieColumns.CONTENT_URI;
    }

    public MovieCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new MovieCursor(cursor);
    }

    public MovieCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public MovieCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new MovieCursor(cursor);
    }

    public MovieCursor query(Context context) {
        return query(context, null);
    }

    public MovieSelection id(long... value) {
        addEquals(MovieColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public MovieSelection idNot(long... value) {
        addNotEquals(MovieColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public MovieSelection orderById(boolean desc) {
        orderBy(MovieColumns.DEFAULT_ORDER, desc);
        return this;
    }

    public MovieSelection orderById() {
        return orderById(false);
    }

    public MovieSelection movieId(Long... value) {
        addEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection movieIdNot(Long... value) {
        addNotEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieSelection movieIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public MovieSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public MovieSelection movieIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public MovieSelection movieIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public MovieSelection orderByMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieSelection orderByMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public MovieSelection backdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection orderByBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public MovieSelection orderByBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public MovieSelection overview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection orderByOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public MovieSelection orderByOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public MovieSelection releaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection orderByReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public MovieSelection orderByReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public MovieSelection posterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection orderByPosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public MovieSelection orderByPosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public MovieSelection title(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection orderByTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public MovieSelection orderByTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public MovieSelection voteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public MovieSelection voteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public MovieSelection voteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public MovieSelection voteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public MovieSelection orderByVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieSelection orderByVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }
}

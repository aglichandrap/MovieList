package org.iblitzc0de.movielist.provider.movie;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractContentValues;

public class MovieContentValues extends AbstractContentValues {
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        String[] strArr = null;
        Uri uri = uri();
        ContentValues values = values();
        String sel = where == null ? null : where.sel();
        if (where != null) {
            strArr = where.args();
        }
        return contentResolver.update(uri, values, sel, strArr);
    }

    public int update(Context context, @Nullable MovieSelection where) {
        String[] strArr = null;
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = uri();
        ContentValues values = values();
        String sel = where == null ? null : where.sel();
        if (where != null) {
            strArr = where.args();
        }
        return contentResolver.update(uri, values, sel, strArr);
    }

    public MovieContentValues putMovieId(@Nullable Long value) {
        this.mContentValues.put(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public MovieContentValues putMovieIdNull() {
        this.mContentValues.putNull(MovieColumns.MOVIE_ID);
        return this;
    }

    public MovieContentValues putBackdropPath(@Nullable String value) {
        this.mContentValues.put(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieContentValues putBackdropPathNull() {
        this.mContentValues.putNull(MovieColumns.BACKDROP_PATH);
        return this;
    }

    public MovieContentValues putOverview(@Nullable String value) {
        this.mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieContentValues putOverviewNull() {
        this.mContentValues.putNull(MovieColumns.OVERVIEW);
        return this;
    }

    public MovieContentValues putReleaseDate(@Nullable String value) {
        this.mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieContentValues putReleaseDateNull() {
        this.mContentValues.putNull(MovieColumns.RELEASE_DATE);
        return this;
    }

    public MovieContentValues putPosterPath(@Nullable String value) {
        this.mContentValues.put(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieContentValues putPosterPathNull() {
        this.mContentValues.putNull(MovieColumns.POSTER_PATH);
        return this;
    }

    public MovieContentValues putTitle(@Nullable String value) {
        this.mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }

    public MovieContentValues putTitleNull() {
        this.mContentValues.putNull(MovieColumns.TITLE);
        return this;
    }

    public MovieContentValues putVoteAverage(@Nullable Double value) {
        this.mContentValues.put(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieContentValues putVoteAverageNull() {
        this.mContentValues.putNull(MovieColumns.VOTE_AVERAGE);
        return this;
    }
}

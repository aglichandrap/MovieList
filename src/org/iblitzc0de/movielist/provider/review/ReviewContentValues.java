package org.iblitzc0de.movielist.provider.review;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractContentValues;

public class ReviewContentValues extends AbstractContentValues {
    public Uri uri() {
        return ReviewColumns.CONTENT_URI;
    }

    public int update(ContentResolver contentResolver, @Nullable ReviewSelection where) {
        String[] strArr = null;
        Uri uri = uri();
        ContentValues values = values();
        String sel = where == null ? null : where.sel();
        if (where != null) {
            strArr = where.args();
        }
        return contentResolver.update(uri, values, sel, strArr);
    }

    public int update(Context context, @Nullable ReviewSelection where) {
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

    public ReviewContentValues putReviewId(@Nullable String value) {
        this.mContentValues.put(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewContentValues putReviewIdNull() {
        this.mContentValues.putNull(ReviewColumns.REVIEW_ID);
        return this;
    }

    public ReviewContentValues putAuthor(@Nullable String value) {
        this.mContentValues.put(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewContentValues putAuthorNull() {
        this.mContentValues.putNull(ReviewColumns.AUTHOR);
        return this;
    }

    public ReviewContentValues putContent(@Nullable String value) {
        this.mContentValues.put(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewContentValues putContentNull() {
        this.mContentValues.putNull(ReviewColumns.CONTENT);
        return this;
    }

    public ReviewContentValues putUrl(@Nullable String value) {
        this.mContentValues.put(ReviewColumns.URL, value);
        return this;
    }

    public ReviewContentValues putUrlNull() {
        this.mContentValues.putNull(ReviewColumns.URL);
        return this;
    }

    public ReviewContentValues putMovieId(long value) {
        this.mContentValues.put(ReviewColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }
}

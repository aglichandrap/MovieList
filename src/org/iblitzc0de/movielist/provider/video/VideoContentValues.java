package org.iblitzc0de.movielist.provider.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractContentValues;

public class VideoContentValues extends AbstractContentValues {
    public Uri uri() {
        return VideoColumns.CONTENT_URI;
    }

    public int update(ContentResolver contentResolver, @Nullable VideoSelection where) {
        String[] strArr = null;
        Uri uri = uri();
        ContentValues values = values();
        String sel = where == null ? null : where.sel();
        if (where != null) {
            strArr = where.args();
        }
        return contentResolver.update(uri, values, sel, strArr);
    }

    public int update(Context context, @Nullable VideoSelection where) {
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

    public VideoContentValues putVideoId(@Nullable String value) {
        this.mContentValues.put(VideoColumns.VIDEO_ID, value);
        return this;
    }

    public VideoContentValues putVideoIdNull() {
        this.mContentValues.putNull(VideoColumns.VIDEO_ID);
        return this;
    }

    public VideoContentValues putName(@Nullable String value) {
        this.mContentValues.put(VideoColumns.NAME, value);
        return this;
    }

    public VideoContentValues putNameNull() {
        this.mContentValues.putNull(VideoColumns.NAME);
        return this;
    }

    public VideoContentValues putKey(@Nullable String value) {
        this.mContentValues.put(VideoColumns.KEY, value);
        return this;
    }

    public VideoContentValues putKeyNull() {
        this.mContentValues.putNull(VideoColumns.KEY);
        return this;
    }

    public VideoContentValues putSize(@Nullable Integer value) {
        this.mContentValues.put(VideoColumns.SIZE, value);
        return this;
    }

    public VideoContentValues putSizeNull() {
        this.mContentValues.putNull(VideoColumns.SIZE);
        return this;
    }

    public VideoContentValues putType(@Nullable String value) {
        this.mContentValues.put(VideoColumns.TYPE, value);
        return this;
    }

    public VideoContentValues putTypeNull() {
        this.mContentValues.putNull(VideoColumns.TYPE);
        return this;
    }

    public VideoContentValues putMovieId(long value) {
        this.mContentValues.put(VideoColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }
}

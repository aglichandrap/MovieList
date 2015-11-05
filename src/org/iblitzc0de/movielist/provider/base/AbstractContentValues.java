package org.iblitzc0de.movielist.provider.base;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public abstract class AbstractContentValues {
    protected final ContentValues mContentValues = new ContentValues();

    public abstract Uri uri();

    public ContentValues values() {
        return this.mContentValues;
    }

    public Uri insert(ContentResolver contentResolver) {
        return contentResolver.insert(uri(), values());
    }

    public Uri insert(Context context) {
        return context.getContentResolver().insert(uri(), values());
    }
}

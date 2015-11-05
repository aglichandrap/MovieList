package org.iblitzc0de.movielist.provider.base;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import org.iblitzc0de.movielist.provider.video.VideoColumns;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class BaseContentProvider extends ContentProvider {
    public static final String QUERY_GROUP_BY = "QUERY_GROUP_BY";
    public static final String QUERY_HAVING = "QUERY_HAVING";
    public static final String QUERY_LIMIT = "QUERY_LIMIT";
    public static final String QUERY_NOTIFY = "QUERY_NOTIFY";
    protected SQLiteOpenHelper mSqLiteOpenHelper;

    public static class QueryParams {
        public String idColumn;
        public String orderBy;
        public String selection;
        public String table;
        public String tablesWithJoins;
    }

    protected abstract SQLiteOpenHelper createSqLiteOpenHelper();

    protected abstract QueryParams getQueryParams(Uri uri, String str, String[] strArr);

    protected abstract boolean hasDebug();

    public final boolean onCreate() {
        if (hasDebug()) {
            try {
                Field field = Class.forName("android.database.sqlite.SQLiteDebug").getDeclaredField("DEBUG_SQL_STATEMENTS");
                field.setAccessible(true);
                field.set(null, Boolean.valueOf(true));
            } catch (Throwable t) {
                if (hasDebug()) {
                    Log.w(getClass().getSimpleName(), "Could not enable SQLiteDebug logging", t);
                }
            }
        }
        this.mSqLiteOpenHelper = createSqLiteOpenHelper();
        return false;
    }

    public Uri insert(Uri uri, ContentValues values) {
        long rowId = this.mSqLiteOpenHelper.getWritableDatabase().insertOrThrow(uri.getLastPathSegment(), null, values);
        if (rowId == -1) {
            return null;
        }
        String notify = uri.getQueryParameter(QUERY_NOTIFY);
        if (notify == null || "true".equals(notify)) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
    }

    public int bulkInsert(Uri uri, ContentValues[] values) {
        String table = uri.getLastPathSegment();
        SQLiteDatabase db = this.mSqLiteOpenHelper.getWritableDatabase();
        int res = 0;
        db.beginTransaction();
        try {
            for (ContentValues v : values) {
                long id = db.insert(table, null, v);
                db.yieldIfContendedSafely();
                if (id != -1) {
                    res++;
                }
            }
            db.setTransactionSuccessful();
            if (res != 0) {
                String notify = uri.getQueryParameter(QUERY_NOTIFY);
                if (notify == null || "true".equals(notify)) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }
            return res;
        } finally {
            db.endTransaction();
        }
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        QueryParams queryParams = getQueryParams(uri, selection, null);
        int res = this.mSqLiteOpenHelper.getWritableDatabase().update(queryParams.table, values, queryParams.selection, selectionArgs);
        if (res != 0) {
            String notify = uri.getQueryParameter(QUERY_NOTIFY);
            if (notify == null || "true".equals(notify)) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return res;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        QueryParams queryParams = getQueryParams(uri, selection, null);
        int res = this.mSqLiteOpenHelper.getWritableDatabase().delete(queryParams.table, queryParams.selection, selectionArgs);
        if (res != 0) {
            String notify = uri.getQueryParameter(QUERY_NOTIFY);
            if (notify == null || "true".equals(notify)) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return res;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String str;
        String groupBy = uri.getQueryParameter(QUERY_GROUP_BY);
        String having = uri.getQueryParameter(QUERY_HAVING);
        String limit = uri.getQueryParameter(QUERY_LIMIT);
        QueryParams queryParams = getQueryParams(uri, selection, projection);
        projection = ensureIdIsFullyQualified(projection, queryParams.table, queryParams.idColumn);
        SQLiteDatabase readableDatabase = this.mSqLiteOpenHelper.getReadableDatabase();
        String str2 = queryParams.tablesWithJoins;
        String str3 = queryParams.selection;
        if (sortOrder == null) {
            str = queryParams.orderBy;
        } else {
            str = sortOrder;
        }
        Cursor res = readableDatabase.query(str2, projection, str3, selectionArgs, groupBy, having, str, limit);
        res.setNotificationUri(getContext().getContentResolver(), uri);
        return res;
    }

    private String[] ensureIdIsFullyQualified(String[] projection, String tableName, String idColumn) {
        if (projection == null) {
            return null;
        }
        String[] res = new String[projection.length];
        for (int i = 0; i < projection.length; i++) {
            if (projection[i].equals(idColumn)) {
                res[i] = tableName + "." + idColumn + " AS " + VideoColumns._ID;
            } else {
                res[i] = projection[i];
            }
        }
        return res;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        HashSet<Uri> urisToNotify = new HashSet(operations.size());
        Iterator i$ = operations.iterator();
        while (i$.hasNext()) {
            urisToNotify.add(((ContentProviderOperation) i$.next()).getUri());
        }
        SQLiteDatabase db = this.mSqLiteOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentProviderResult[] results = new ContentProviderResult[operations.size()];
            int i = 0;
            i$ = operations.iterator();
            while (i$.hasNext()) {
                ContentProviderOperation operation = (ContentProviderOperation) i$.next();
                results[i] = operation.apply(this, results, i);
                if (operation.isYieldAllowed()) {
                    db.yieldIfContendedSafely();
                }
                i++;
            }
            db.setTransactionSuccessful();
            i$ = urisToNotify.iterator();
            while (i$.hasNext()) {
                getContext().getContentResolver().notifyChange((Uri) i$.next(), null);
            }
            return results;
        } finally {
            db.endTransaction();
        }
    }

    public static Uri notify(Uri uri, boolean notify) {
        return uri.buildUpon().appendQueryParameter(QUERY_NOTIFY, String.valueOf(notify)).build();
    }

    public static Uri groupBy(Uri uri, String groupBy) {
        return uri.buildUpon().appendQueryParameter(QUERY_GROUP_BY, groupBy).build();
    }

    public static Uri having(Uri uri, String having) {
        return uri.buildUpon().appendQueryParameter(QUERY_HAVING, having).build();
    }

    public static Uri limit(Uri uri, String limit) {
        return uri.buildUpon().appendQueryParameter(QUERY_LIMIT, limit).build();
    }
}

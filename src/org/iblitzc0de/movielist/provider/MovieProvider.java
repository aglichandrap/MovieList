package org.iblitzc0de.movielist.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import org.iblitzc0de.movieapp2.BuildConfig;
import org.iblitzc0de.movielist.provider.base.BaseContentProvider;
import org.iblitzc0de.movielist.provider.base.BaseContentProvider.QueryParams;
import org.iblitzc0de.movielist.provider.movie.MovieColumns;
import org.iblitzc0de.movielist.provider.review.ReviewColumns;
import org.iblitzc0de.movielist.provider.video.VideoColumns;

import java.util.Arrays;

public class MovieProvider extends BaseContentProvider {
    public static final String AUTHORITY = "org.iblitzc0de.movieapp2.provider";
    public static final String CONTENT_URI_BASE = "content://org.iblitzc0de.movieapp2.provider";
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = MovieProvider.class.getSimpleName();
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private static final int URI_TYPE_MOVIE = 0;
    private static final int URI_TYPE_MOVIE_ID = 1;
    private static final int URI_TYPE_REVIEW = 2;
    private static final int URI_TYPE_REVIEW_ID = 3;
    private static final int URI_TYPE_VIDEO = 4;
    private static final int URI_TYPE_VIDEO_ID = 5;

    static {
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME, URI_TYPE_MOVIE);
        URI_MATCHER.addURI(AUTHORITY, "movie/#", URI_TYPE_MOVIE_ID);
        URI_MATCHER.addURI(AUTHORITY, ReviewColumns.TABLE_NAME, URI_TYPE_REVIEW);
        URI_MATCHER.addURI(AUTHORITY, "review/#", URI_TYPE_REVIEW_ID);
        URI_MATCHER.addURI(AUTHORITY, VideoColumns.TABLE_NAME, URI_TYPE_VIDEO);
        URI_MATCHER.addURI(AUTHORITY, "video/#", URI_TYPE_VIDEO_ID);
    }

    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return MySQLiteOpenHelper.getInstance(getContext());
    }

    protected boolean hasDebug() {
        return DEBUG;
    }

    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case URI_TYPE_MOVIE /*0*/:
                return "vnd.android.cursor.dir/movie";
            case URI_TYPE_MOVIE_ID /*1*/:
                return "vnd.android.cursor.item/movie";
            case URI_TYPE_REVIEW /*2*/:
                return "vnd.android.cursor.dir/review";
            case URI_TYPE_REVIEW_ID /*3*/:
                return "vnd.android.cursor.item/review";
            case URI_TYPE_VIDEO /*4*/:
                return "vnd.android.cursor.dir/video";
            case URI_TYPE_VIDEO_ID /*5*/:
                return "vnd.android.cursor.item/video";
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) {
            Log.d(TAG, "insert uri=" + uri + " values=" + values);
        }
        return super.insert(uri, values);
    }

    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) {
            Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        }
        return super.bulkInsert(uri, values);
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) {
            Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        }
        return super.update(uri, values, selection, selectionArgs);
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) {
            Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        }
        return super.delete(uri, selection, selectionArgs);
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG) {
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder + " groupBy=" + uri.getQueryParameter(BaseContentProvider.QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(BaseContentProvider.QUERY_HAVING) + " limit=" + uri.getQueryParameter(BaseContentProvider.QUERY_LIMIT));
        }
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_MOVIE /*0*/:
            case URI_TYPE_MOVIE_ID /*1*/:
                res.table = MovieColumns.TABLE_NAME;
                res.idColumn = VideoColumns._ID;
                res.tablesWithJoins = MovieColumns.TABLE_NAME;
                res.orderBy = MovieColumns.DEFAULT_ORDER;
                break;
            case URI_TYPE_REVIEW /*2*/:
            case URI_TYPE_REVIEW_ID /*3*/:
                res.table = ReviewColumns.TABLE_NAME;
                res.idColumn = VideoColumns._ID;
                res.tablesWithJoins = ReviewColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN movie AS review__movie ON review.review__movie_id=review__movie._id";
                }
                res.orderBy = ReviewColumns.DEFAULT_ORDER;
                break;
            case URI_TYPE_VIDEO /*4*/:
            case URI_TYPE_VIDEO_ID /*5*/:
                res.table = VideoColumns.TABLE_NAME;
                res.idColumn = VideoColumns._ID;
                res.tablesWithJoins = VideoColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN movie AS video__movie ON video.video__movie_id=video__movie._id";
                }
                res.orderBy = VideoColumns.DEFAULT_ORDER;
                break;
            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }
        switch (matchedId) {
            case URI_TYPE_MOVIE_ID /*1*/:
            case URI_TYPE_REVIEW_ID /*3*/:
            case URI_TYPE_VIDEO_ID /*5*/:
                id = uri.getLastPathSegment();
                break;
        }
        if (id == null) {
            res.selection = selection;
        } else if (selection != null) {
            res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
        } else {
            res.selection = res.table + "." + res.idColumn + "=" + id;
        }
        return res;
    }
}

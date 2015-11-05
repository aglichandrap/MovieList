package org.iblitzc0de.movielist.provider.review;

import android.net.Uri;
import android.provider.BaseColumns;

public class ReviewColumns implements BaseColumns {
    
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";
    public static final Uri CONTENT_URI = Uri.parse("content://org.iblitzc0de.movieapp2.provider/review");
    public static final String DEFAULT_ORDER = "review._id";
    public static final String MOVIE_ID = "review__movie_id";
    public static final String PREFIX_MOVIE = "review__movie";
    public static final String REVIEW_ID = "review_id";
    public static final String TABLE_NAME = "review";
    public static final String URL = "url";
    public static final String _ID = "_id";

    public static boolean hasColumns(String[] projection) {
        if (projection == null) {
            return true;
        }
        for (String c : projection) {
            if (c.equals(REVIEW_ID) || c.contains(".review_id") || c.equals(AUTHOR) || c.contains(".author") || c.equals(CONTENT) || c.contains(".content") || c.equals(URL) || c.contains(".url") || c.equals(MOVIE_ID) || c.contains(".review__movie_id")) {
                return true;
            }
        }
        return false;
    }
}

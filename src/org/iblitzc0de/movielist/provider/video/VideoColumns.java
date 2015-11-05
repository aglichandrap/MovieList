package org.iblitzc0de.movielist.provider.video;

import android.net.Uri;
import android.provider.BaseColumns;

public class VideoColumns implements BaseColumns {
    
    public static final Uri CONTENT_URI = Uri.parse("content://org.iblitzc0de.movieapp2.provider/video");
    public static final String DEFAULT_ORDER = "video._id";
    public static final String KEY = "key";
    public static final String MOVIE_ID = "video__movie_id";
    public static final String NAME = "name";
    public static final String PREFIX_MOVIE = "video__movie";
    public static final String SIZE = "size";
    public static final String TABLE_NAME = "video";
    public static final String TYPE = "type";
    public static final String VIDEO_ID = "video_id";
    public static final String _ID = "_id";

    public static boolean hasColumns(String[] projection) {
        if (projection == null) {
            return true;
        }
        for (String c : projection) {
            if (c.equals(VIDEO_ID) || c.contains(".video_id") || c.equals(NAME) || c.contains(".name") || c.equals(KEY) || c.contains(".key") || c.equals(SIZE) || c.contains(".size") || c.equals(TYPE) || c.contains(".type") || c.equals(MOVIE_ID) || c.contains(".video__movie_id")) {
                return true;
            }
        }
        return false;
    }
}

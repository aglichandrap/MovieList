package org.iblitzc0de.movielist.provider.movie;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieColumns implements BaseColumns {
    
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final Uri CONTENT_URI = Uri.parse("content://org.iblitzc0de.movieapp2.provider/movie");
    public static final String DEFAULT_ORDER = "movie._id";
    public static final String MOVIE_ID = "movie__movie_id";
    public static final String OVERVIEW = "overview";
    public static final String POSTER_PATH = "poster_path";
    public static final String RELEASE_DATE = "release_date";
    public static final String TABLE_NAME = "movie";
    public static final String TITLE = "title";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String _ID = "_id";

    public static boolean hasColumns(String[] projection) {
        if (projection == null) {
            return true;
        }
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains(".movie__movie_id") || c.equals(BACKDROP_PATH) || c.contains(".backdrop_path") || c.equals(OVERVIEW) || c.contains(".overview") || c.equals(RELEASE_DATE) || c.contains(".release_date") || c.equals(POSTER_PATH) || c.contains(".poster_path") || c.equals(TITLE) || c.contains(".title") || c.equals(VOTE_AVERAGE) || c.contains(".vote_average")) {
                return true;
            }
        }
        return false;
    }
}

package org.iblitzc0de.movielist.provider.video;

import android.database.Cursor;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractCursor;
import org.iblitzc0de.movielist.provider.movie.MovieColumns;

public class VideoCursor extends AbstractCursor implements VideoModel {
    public VideoCursor(Cursor cursor) {
        super(cursor);
    }

    public long getId() {
        Long res = getLongOrNull(VideoColumns._ID);
        if (res != null) {
            return res.longValue();
        }
        throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
    }

    @Nullable
    public String getVideoId() {
        return getStringOrNull(VideoColumns.VIDEO_ID);
    }

    @Nullable
    public String getName() {
        return getStringOrNull(VideoColumns.NAME);
    }

    @Nullable
    public String getKey() {
        return getStringOrNull(VideoColumns.KEY);
    }

    @Nullable
    public Integer getSize() {
        return getIntegerOrNull(VideoColumns.SIZE);
    }

    @Nullable
    public String getType() {
        return getStringOrNull(VideoColumns.TYPE);
    }

    public long getMovieId() {
        Long res = getLongOrNull(VideoColumns.MOVIE_ID);
        if (res != null) {
            return res.longValue();
        }
        throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
    }

    @Nullable
    public Long getMovieMovieId() {
        return getLongOrNull(MovieColumns.MOVIE_ID);
    }

    @Nullable
    public String getMovieBackdropPath() {
        return getStringOrNull(MovieColumns.BACKDROP_PATH);
    }

    @Nullable
    public String getMovieOverview() {
        return getStringOrNull(MovieColumns.OVERVIEW);
    }

    @Nullable
    public String getMovieReleaseDate() {
        return getStringOrNull(MovieColumns.RELEASE_DATE);
    }

    @Nullable
    public String getMoviePosterPath() {
        return getStringOrNull(MovieColumns.POSTER_PATH);
    }

    @Nullable
    public String getMovieTitle() {
        return getStringOrNull(MovieColumns.TITLE);
    }

    @Nullable
    public Double getMovieVoteAverage() {
        return getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
    }
}

package org.iblitzc0de.movielist.provider.review;

import android.database.Cursor;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractCursor;
import org.iblitzc0de.movielist.provider.movie.MovieColumns;
import org.iblitzc0de.movielist.provider.video.VideoColumns;

public class ReviewCursor extends AbstractCursor implements ReviewModel {
    public ReviewCursor(Cursor cursor) {
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
    public String getReviewId() {
        return getStringOrNull(ReviewColumns.REVIEW_ID);
    }

    @Nullable
    public String getAuthor() {
        return getStringOrNull(ReviewColumns.AUTHOR);
    }

    @Nullable
    public String getContent() {
        return getStringOrNull(ReviewColumns.CONTENT);
    }

    @Nullable
    public String getUrl() {
        return getStringOrNull(ReviewColumns.URL);
    }

    public long getMovieId() {
        Long res = getLongOrNull(ReviewColumns.MOVIE_ID);
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

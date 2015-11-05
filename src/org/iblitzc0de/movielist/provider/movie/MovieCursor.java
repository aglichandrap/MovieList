package org.iblitzc0de.movielist.provider.movie;

import android.database.Cursor;
import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.AbstractCursor;
import org.iblitzc0de.movielist.provider.video.VideoColumns;

public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
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
    public Long getMovieId() {
        return getLongOrNull(MovieColumns.MOVIE_ID);
    }

    @Nullable
    public String getBackdropPath() {
        return getStringOrNull(MovieColumns.BACKDROP_PATH);
    }

    @Nullable
    public String getOverview() {
        return getStringOrNull(MovieColumns.OVERVIEW);
    }

    @Nullable
    public String getReleaseDate() {
        return getStringOrNull(MovieColumns.RELEASE_DATE);
    }

    @Nullable
    public String getPosterPath() {
        return getStringOrNull(MovieColumns.POSTER_PATH);
    }

    public String getStringOrNull(String posterPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Nullable
    public String getTitle() {
        return getStringOrNull(MovieColumns.TITLE);
    }

    @Nullable
    public Double getVoteAverage() {
        return getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
    }

	public Double getDoubleOrNull(String voteAverage) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean moveToNext() {
		// TODO Auto-generated method stub
		return false;
	}
}

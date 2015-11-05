package org.iblitzc0de.movielist.provider.movie;

import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.BaseModel;

public interface MovieModel extends BaseModel {
    @Nullable
    String getBackdropPath();

    @Nullable
    Long getMovieId();

    @Nullable
    String getOverview();

    @Nullable
    String getPosterPath();

    @Nullable
    String getReleaseDate();

    @Nullable
    String getTitle();

    @Nullable
    Double getVoteAverage();
}

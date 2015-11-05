package org.iblitzc0de.movielist.provider.video;

import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.BaseModel;

public interface VideoModel extends BaseModel {
    @Nullable
    String getKey();

    long getMovieId();

    @Nullable
    String getName();

    @Nullable
    Integer getSize();

    @Nullable
    String getType();

    @Nullable
    String getVideoId();
}

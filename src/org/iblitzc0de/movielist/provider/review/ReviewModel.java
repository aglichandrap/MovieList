package org.iblitzc0de.movielist.provider.review;

import android.support.annotation.Nullable;

import org.iblitzc0de.movielist.provider.base.BaseModel;

public interface ReviewModel extends BaseModel {
    @Nullable
    String getAuthor();

    @Nullable
    String getContent();

    long getMovieId();

    @Nullable
    String getReviewId();

    @Nullable
    String getUrl();
}

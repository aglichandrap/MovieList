package org.iblitzc0de.movielist.provider.review;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.iblitzc0de.movielist.provider.base.AbstractSelection;
import org.iblitzc0de.movielist.provider.movie.MovieColumns;

public class ReviewSelection extends AbstractSelection<ReviewSelection> {
    protected Uri baseUri() {
        return ReviewColumns.CONTENT_URI;
    }

    public ReviewCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new ReviewCursor(cursor);
    }

    public ReviewCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public ReviewCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) {
            return null;
        }
        return new ReviewCursor(cursor);
    }

    public ReviewCursor query(Context context) {
        return query(context, null);
    }

    public ReviewSelection id(long... value) {
        addEquals(ReviewColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public ReviewSelection idNot(long... value) {
        addNotEquals(ReviewColumns.DEFAULT_ORDER, toObjectArray(value));
        return this;
    }

    public ReviewSelection orderById(boolean desc) {
        orderBy(ReviewColumns.DEFAULT_ORDER, desc);
        return this;
    }

    public ReviewSelection orderById() {
        return orderById(false);
    }

    public ReviewSelection reviewId(String... value) {
        addEquals(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection reviewIdNot(String... value) {
        addNotEquals(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection reviewIdLike(String... value) {
        addLike(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection reviewIdContains(String... value) {
        addContains(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection reviewIdStartsWith(String... value) {
        addStartsWith(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection reviewIdEndsWith(String... value) {
        addEndsWith(ReviewColumns.REVIEW_ID, value);
        return this;
    }

    public ReviewSelection orderByReviewId(boolean desc) {
        orderBy(ReviewColumns.REVIEW_ID, desc);
        return this;
    }

    public ReviewSelection orderByReviewId() {
        orderBy(ReviewColumns.REVIEW_ID, false);
        return this;
    }

    public ReviewSelection author(String... value) {
        addEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorNot(String... value) {
        addNotEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorLike(String... value) {
        addLike(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorContains(String... value) {
        addContains(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorStartsWith(String... value) {
        addStartsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorEndsWith(String... value) {
        addEndsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection orderByAuthor(boolean desc) {
        orderBy(ReviewColumns.AUTHOR, desc);
        return this;
    }

    public ReviewSelection orderByAuthor() {
        orderBy(ReviewColumns.AUTHOR, false);
        return this;
    }

    public ReviewSelection content(String... value) {
        addEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentNot(String... value) {
        addNotEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentLike(String... value) {
        addLike(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentContains(String... value) {
        addContains(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentStartsWith(String... value) {
        addStartsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentEndsWith(String... value) {
        addEndsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection orderByContent(boolean desc) {
        orderBy(ReviewColumns.CONTENT, desc);
        return this;
    }

    public ReviewSelection orderByContent() {
        orderBy(ReviewColumns.CONTENT, false);
        return this;
    }

    public ReviewSelection url(String... value) {
        addEquals(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlNot(String... value) {
        addNotEquals(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlLike(String... value) {
        addLike(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlContains(String... value) {
        addContains(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlStartsWith(String... value) {
        addStartsWith(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlEndsWith(String... value) {
        addEndsWith(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection orderByUrl(boolean desc) {
        orderBy(ReviewColumns.URL, desc);
        return this;
    }

    public ReviewSelection orderByUrl() {
        orderBy(ReviewColumns.URL, false);
        return this;
    }

    public ReviewSelection movieId(long... value) {
        addEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdNot(long... value) {
        addNotEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdGt(long value) {
        addGreaterThan(ReviewColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(ReviewColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieIdLt(long value) {
        addLessThan(ReviewColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieIdLtEq(long value) {
        addLessThanOrEquals(ReviewColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection orderByMovieId(boolean desc) {
        orderBy(ReviewColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieId() {
        orderBy(ReviewColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieMovieId(Long... value) {
        addEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieMovieIdNot(Long... value) {
        addNotEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieMovieIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieMovieIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieMovieIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection movieMovieIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, Long.valueOf(value));
        return this;
    }

    public ReviewSelection orderByMovieMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public ReviewSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public ReviewSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public ReviewSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public ReviewSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public ReviewSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public ReviewSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public ReviewSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public ReviewSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public ReviewSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, Double.valueOf(value));
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }
}

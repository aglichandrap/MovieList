package org.iblitzc0de.movielist.apis;

import org.iblitzc0de.movielist.apis.daos.BaseListApiDao;
import org.iblitzc0de.movielist.apis.daos.MovieDao;
import org.iblitzc0de.movielist.apis.daos.ReviewDao;
import org.iblitzc0de.movielist.apis.daos.VideoDao;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TheMovieService {
    @GET("discover/movie")
    Call<BaseListApiDao<MovieDao>> discoverMovie(@Query("api_key") String str, @Query("sort_by") String str2);

    @GET("movie/{movie_id}/reviews")
    Call<BaseListApiDao<ReviewDao>> movieReviews(@Path("movie_id") long j, @Query("api_key") String str);

    @GET("movie/{movie_id}/videos")
    Call<BaseListApiDao<VideoDao>> movieVideos(@Path("movie_id") long j, @Query("api_key") String str);
}

package org.iblitzc0de.popularlist.apis;

import org.iblitzc0de.popularlist.apis.daos.DiscoverMovieApiDao;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TheMovieService {
    @GET("discover/movie")
    Call<DiscoverMovieApiDao> discoverMovie(@Query("api_key") String str, @Query("sort_by") String str2);
}

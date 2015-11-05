package org.iblitzc0de.movielist.apis;

import org.iblitzc0de.popularlist.utils.Constant;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Retrofit.Builder;

public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private Retrofit mRetrofit = new Builder().baseUrl(Constant.BASE_API_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private TheMovieService mService = ((TheMovieService) this.mRetrofit.create(TheMovieService.class));

    private RetrofitHelper() {
    }

    public static RetrofitHelper getInstance() {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelper();
        }
        return retrofitHelper;
    }

    public TheMovieService getService() {
        return this.mService;
    }
}

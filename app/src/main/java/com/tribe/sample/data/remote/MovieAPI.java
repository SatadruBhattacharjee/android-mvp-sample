package com.tribe.sample.data.remote;

import com.tribe.sample.utils.Config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET(Config.NOW_PLAYING)
    Call<ResponseBody> getNowPlayingMovies(
    );

    @GET(Config.MOVIE + "{id}")
    Call<ResponseBody> getMovieDetails(
    );
}

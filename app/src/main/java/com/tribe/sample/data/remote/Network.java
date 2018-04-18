package com.tribe.sample.data.remote;

import android.util.Log;

import com.tribe.sample.utils.Config;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Network {

    private static final String TAG = "Network";

    private MovieAPI movieAPI;

    public Network(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Config.showLog ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .build();
        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void getNowPlayingMovies(final NetworkCallback callback) {
        Call<ResponseBody> call = movieAPI.getNowPlayingMovies();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                checkResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.failure(null);
            }
        });
    }

    private void checkResponse(retrofit2.Response<ResponseBody> response, NetworkCallback callback) {
        String body = getBodyFromResponse(response);
        if (!response.isSuccessful()) {
            callback.failure(body);
        } else {
            callback.success(body);
        }
    }

    private String getBodyFromResponse(retrofit2.Response<ResponseBody> response) {
        String body = "";
        try {
            if (response != null && response.body() != null) {
                body = response.body().string();
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return body;
    }
}

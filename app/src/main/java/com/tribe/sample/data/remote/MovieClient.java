package com.tribe.sample.data.remote;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.MovieResponse;
import com.tribe.sample.utils.Config;

public class MovieClient {

    private Network network;

    public MovieClient() {
        network = new Network(Config.API_URL);
    }

    public void getNowPlayingMovies(@NonNull final MovieRepository.MovieListListener callback) {

        NetworkCallback networkCallback = new NetworkCallback() {
            @Override
            public void success(String body) {
                MovieResponse movieResponse = new Gson().fromJson(body, MovieResponse.class);
                callback.onMovieListReceived(movieResponse.getResults());
            }

            @Override
            public void failure(String body) {
                String message = "";
                if (!TextUtils.isEmpty(body)) {
                    Error error = new Gson().fromJson(body, Error.class);
                    message = error.getMessage();
                }
                callback.onFailure(message);
            }
        };

        network.getNowPlayingMovies(networkCallback);

    }

    public void getMovieDetails(int movieId, MovieRepository.MovieDetailsListener callback) {

    }

}

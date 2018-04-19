package com.tribe.sample.data;

import android.support.annotation.NonNull;

import com.tribe.sample.data.models.Movie;
import com.tribe.sample.data.models.MovieDetails;
import com.tribe.sample.data.remote.MovieClient;
import com.tribe.sample.utils.Config;

import java.util.List;

public class MovieRepository {

    private MovieClient movieClient;

    public MovieRepository() {
        this.movieClient = new MovieClient();
    }

    public interface RepositoryListener {
        void onFailure(String message);
    }

    public void getMovieList(@NonNull final MovieListListener callback) {
        movieClient.getNowPlayingMovies(callback);
    }

    public static String getMoviePosterUrl(String name) {
        return Config.POSTER_URL + name;
    }

    public static String getMovieBackdropUrl(String name) {
        return Config.BACKDROP_URL + name;
    }



    // =============================================================================
    // CALLBACKS
    // =============================================================================

    public interface MovieListListener extends RepositoryListener {
        void onMovieListReceived(List<Movie> movies);
    }

    public interface MovieDetailsListener extends RepositoryListener {
        void onMovieDetailsReceived(MovieDetails movieDetails);
    }
}

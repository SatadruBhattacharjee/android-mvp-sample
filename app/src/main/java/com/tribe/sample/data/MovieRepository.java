package com.tribe.sample.data;

import android.support.annotation.NonNull;

import com.tribe.sample.data.models.Movie;
import com.tribe.sample.data.models.MovieDetails;
import com.tribe.sample.data.remote.MovieClient;

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

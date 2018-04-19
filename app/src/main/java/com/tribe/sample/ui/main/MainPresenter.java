package com.tribe.sample.ui.main;


import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Movie;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MovieRepository.MovieListListener {

    private MainContract.MainView mainView;
    private MovieRepository movieRepository;

    public MainPresenter (MainContract.MainView mainView, MovieRepository movieRepository) {
        this.mainView = mainView;
        this.movieRepository = movieRepository;
    }

    @Override
    public void initialize() {
        movieRepository.getMovieList(this);
    }

    @Override
    public void onFailure(String message) {
        mainView.showError(message);
    }

    @Override
    public void onMovieListReceived(List<Movie> movies) {
        mainView.renderMovieList(movies);
    }

}

package com.tribe.sample.ui.main;


import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Movie;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MovieRepository.MovieListListener {

    private MainContract.MainView mMainView;
    private MovieRepository movieRepository;

    public MainPresenter (MainContract.MainView mainView) {
        this.mMainView = mainView;
        movieRepository = new MovieRepository();
    }

    @Override
    public void initialize() {
        movieRepository.getMovieList(this);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onMovieListReceived(List<Movie> movies) {
        this.mMainView.renderMovieList(movies);
    }

}

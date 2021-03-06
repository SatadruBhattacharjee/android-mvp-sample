package com.tribe.sample.ui.main;

import com.tribe.sample.data.models.Movie;

import java.util.List;

public class MainContract {

    public interface MainView {

        void renderMovieList(List<Movie> movies);

        void showError(String msg);

    }

    public interface Presenter {

        void initialize();

    }
}

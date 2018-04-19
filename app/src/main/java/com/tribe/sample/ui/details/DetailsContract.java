package com.tribe.sample.ui.details;

import com.tribe.sample.data.models.MovieDetails;

public class DetailsContract {

    public interface DetailsView {
        void renderDetailsView(MovieDetails movieDetails);
        void onError(String msg);
    }

    public interface DetailsPresenter {
        void initialize();
    }
}

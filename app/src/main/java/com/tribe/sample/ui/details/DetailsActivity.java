package com.tribe.sample.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tribe.sample.R;
import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Genre;
import com.tribe.sample.data.models.Movie;
import com.tribe.sample.data.models.MovieDetails;
import com.tribe.sample.ui.main.MainActivity;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.DetailsView {

    private static final String PARCEL_DATA = "PARCEL_MOVIE";

    private Movie movie;

    private int mPosterHeight;
    private int mPosterWidth;

    private int mBackdropHeight;
    private int mBackdropWidth;

    private ImageView mBackdropImageView;
    private ImageView mPosterImageView;
    private TextView mMovieTitle;
    private TextView mMovieYear;
    private TextView mGenreTextView;
    private TextView mMovieRating;
    private TextView mMovieDescription;

    public static Intent getCallingIntent(Activity from, Movie movie) {
        Intent intent = new Intent(from, DetailsActivity.class);
        intent.putExtra(PARCEL_DATA, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.movie = (Movie) getIntent().getParcelableExtra(PARCEL_DATA);
        setupUI();
    }

    private void setupUI() {
        mPosterWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.25);
        mPosterHeight = (int) (mPosterWidth / 0.66);
        mBackdropWidth = getResources().getDisplayMetrics().widthPixels;
        mBackdropHeight = (int) (mBackdropWidth / 1.77);

        mBackdropImageView = (ImageView) findViewById(R.id.image_view_backdrop);
        mBackdropImageView.getLayoutParams().height = mBackdropHeight;
        // loading Movie poster using Glide library
        Glide.with(getApplicationContext()).load(MovieRepository.getMovieBackdropUrl(movie.getBackdropPath())).into(mBackdropImageView);

        mPosterImageView = (ImageView) findViewById(R.id.image_view_poster);
        mPosterImageView.getLayoutParams().width = mPosterWidth;
        mPosterImageView.getLayoutParams().height = mPosterHeight;
        // loading Movie poster using Glide library
        Glide.with(getApplicationContext()).load(MovieRepository.getMoviePosterUrl(movie.getPosterPath())).into(mPosterImageView);

        mMovieTitle = (TextView) findViewById(R.id.text_view_title_movie_detail);
        mMovieTitle.setText(movie.getTitle());

        mMovieYear = (TextView) findViewById(R.id.text_view_year_movie_detail);
        mMovieYear.setText(movie.getReleaseDate());

        mGenreTextView = (TextView) findViewById(R.id.text_view_genre_movie_detail);
        setGenres(movie.getGenreIds());

        mMovieRating = (TextView) findViewById(R.id.text_view_rating_movie_detail);
        mMovieRating.setText(String.valueOf(movie.getVoteAverage()));


        mMovieDescription = (TextView) findViewById(R.id.text_view_details_movie_detail);
        mMovieDescription.setText(movie.getOverview());
    }

    private void setGenres(List<String> genresList) {

        StringBuilder csvBuilder = new StringBuilder();
        for(String genre : genresList){
            csvBuilder.append(genre);
            csvBuilder.append(",");
        }
        String csv = csvBuilder.toString();
        mGenreTextView.setText(csv);
    }

    @Override
    public void renderDetailsView(MovieDetails movieDetails) {

    }

    @Override
    public void onError(String msg) {

    }
}

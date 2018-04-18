package com.tribe.sample.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tribe.sample.R;
import com.tribe.sample.data.models.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private MainContract.Presenter  mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMainPresenter = new MainPresenter(this);

        this.mMainPresenter.initialize();

    }

    @Override
    public void renderMovieList(List<Movie> movies) {
        Log.d("MainActivity", "Received movies" );
    }
}

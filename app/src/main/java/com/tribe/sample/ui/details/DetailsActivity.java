package com.tribe.sample.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.tribe.sample.R;
import com.tribe.sample.data.models.Movie;
import com.tribe.sample.ui.main.MainActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    public static Intent getCallingIntent(Activity from, Movie movie) {
        Intent intent = new Intent(from, DetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, new Gson().toJson(movie));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}

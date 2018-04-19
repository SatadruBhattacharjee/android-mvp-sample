package com.tribe.sample.ui.main;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.tribe.sample.R;
import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Movie;
import com.tribe.sample.ui.adapters.MoviesAdapter;
import com.tribe.sample.ui.details.DetailsActivity;
import com.tribe.sample.ui.listeners.RecycleItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, RecycleItemClickListener {

    private static final String TAG = "MainActivity";

    private MainContract.Presenter  mMainPresenter;

    private RecyclerView mRecyclerView;

    private List<Movie> mMovieList;

    private MoviesAdapter mMoviesAdapter;

    private MovieRepository movieRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = new MovieRepository();

        mRecyclerView = (RecyclerView)findViewById(R.id.movie_list);


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mMoviesAdapter = new MoviesAdapter(getApplicationContext(), this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        this.mMainPresenter = new MainPresenter(this, movieRepository);

        this.mMainPresenter.initialize();

    }

    @Override
    public void renderMovieList(List<Movie> movies) {
        if (movies != null) {
            mMovieList = movies;
            mMoviesAdapter.setData(movies);
        }

    }

    @Override
    public void showError(String msg) {
        Log.e(TAG, msg);
    }

    @Override
    public void onRecycleItemClick(int pos) {
        if (mMovieList != null) {
            Movie movie = mMovieList.get(pos);
            Log.d(TAG, "You clicked " + movie.getTitle());
            startActivity(DetailsActivity.getCallingIntent(this, movie));
        }
    }
}

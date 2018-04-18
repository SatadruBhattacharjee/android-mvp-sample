package com.tribe.sample.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tribe.sample.R;
import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Movie;
import com.tribe.sample.ui.listeners.RecycleItemClickListener;
import com.tribe.sample.ui.main.MainContract;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> implements OnHolderClickListener {

    private List<Movie> mMoviesList;
    private Context mContext;

    private RecycleItemClickListener mRecycleItemClickListener;

    public MoviesAdapter(Context context, RecycleItemClickListener recycleItemClickListener) {
        this.mContext = context;
        this.mRecycleItemClickListener = recycleItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MovieViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.render(mContext, position, movie);

    }

    @Override
    public int getItemCount() {
        return mMoviesList == null ? 0 : mMoviesList.size();
    }

    public void setData(List<Movie> data) {
        this.mMoviesList = data;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(int holderPosition) {
        mRecycleItemClickListener.onRecycleItemClick(holderPosition);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private Movie mMovie;

        private ImageView mPoster;

        private int position;

        private OnHolderClickListener clickListener;

        public MovieViewHolder (View view, OnHolderClickListener clickListener) {
            super(view);
            this.mPoster = (ImageView) view.findViewById(R.id.poster);
            this.clickListener = clickListener;
        }


        public void render(Context context, int pos, Movie movie) {
            position = pos;
            mMovie = movie;
            // loading Movie poster using Glide library
            Glide.with(context).load(MovieRepository.getMoviePosterUrl(movie.getPosterPath())).into(mPoster);

            mPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(position);
                }
            });
        }
    }
}

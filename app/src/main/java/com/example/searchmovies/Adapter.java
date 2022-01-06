package com.example.searchmovies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Movie> movies;
    private Context context;

    public Adapter(Context applicationContext, List<Movie> movieArrayList) {
        this.movies = movieArrayList;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);

        viewHolder.title.setText(movie.getTitle() + " # " + position);


        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .error(R.drawable.ic_launcher_background)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(viewHolder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        Log.d("Size? : ", String.valueOf(movies.size()));
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView ivAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_movie_name);
            ivAvatar = itemView.findViewById(R.id.img_movie);
        }
    }

    public void addMovie(List<Movie> newMovies) {
        int initialSize = movies.size();
        movies.addAll(newMovies);
        notifyItemRangeInserted(initialSize, newMovies.size());
    }

    public void resetList() {
        movies.clear();
        notifyDataSetChanged();
    }
}


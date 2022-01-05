/*package com.example.searchmovies;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;

public class SliderAdapterViewPager extends RecyclerView.Adapter<>{

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;

        public SliderViewHolder(@NonNull View view){
            super(view);
            roundedImageView = view.findViewById(R.id.);
        }
        void setRoundedImageView(Movie movie){
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
    }
}*/

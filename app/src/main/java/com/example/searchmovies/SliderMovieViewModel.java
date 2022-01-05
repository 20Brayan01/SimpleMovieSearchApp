package com.example.searchmovies;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderMovieViewModel extends ViewModel {

    Context context;
    ViewPager2 viewPager;
    private TextView disconnectedTV;
    private SliderRecyclerAdapter sliderRecyclerAdapter = new SliderRecyclerAdapter(context, new ArrayList<>(), viewPager);

    public void sliderLoadJson() {
        try {
            Client client = new Client();
            Service apiService =
                    client.getClient().create(Service.class);
            Call<MovieResponse> call = apiService.getMovies();
            call.enqueue(new Callback<MovieResponse>() {

                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse movieResponse;
                    Log.d("status code", String.valueOf(response.code()));
                    movieResponse = response.body();
                    sliderRecyclerAdapter.addMovie(movieResponse.getMovies());
                }

                @Override
                public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show();
                    disconnectedTV.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

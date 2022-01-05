package com.example.searchmovies;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadMoviesViewModel extends ViewModel {

    Context context;
    public Adapter itemAdapter = new Adapter(context, new ArrayList<>());
    TextView disconnectedTV;

    //itemAdapter = new Adapter(, new ArrayList<>());

    public void loadJson() {
        // disconnectedTV = (TextView) findViewById(R.id.tv_disconnect);
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
                    itemAdapter.addMovie(movieResponse.getMovies());
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

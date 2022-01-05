package com.example.searchmovies;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieViewModel extends ViewModel {
    Context context;
    private Adapter itemAdapter;
    private TextView disconnectedTV;
    private boolean isLoading = false;

    public void search(String query, int pageNumber) {
        Log.e("MainActivity.Search", "Page number : " + pageNumber);
        isLoading = true;

        int pageRequest = pageNumber;
        Client client = new Client();
        Service apiService =
                client.getClient().create(Service.class);
        Call<MovieResponse> call = apiService.searchMovies(query, pageNumber);
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse;
                movieResponse = response.body();
                itemAdapter.addMovie(movieResponse.getMovies());
                isLoading = false;
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show();
                disconnectedTV.setVisibility(View.VISIBLE);
                isLoading = false;
            }
        });
    }
}

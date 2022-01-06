package com.example.searchmovies;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadMoviesViewModel extends ViewModel {

    Context context;
    private MutableLiveData<MovieResponse> moviesList;
    private List<MovieResponse> movieResponseList = new ArrayList<>();

    public LoadMoviesViewModel() {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<MovieResponse> getMoviesListObserver() {
        return moviesList;

    }

    public void loadJson() {
        try {
            Client client = new Client();
            Service apiService =
                    client.getClient().create(Service.class);
            Call<MovieResponse> call = apiService.getMovies();
            call.enqueue(new Callback<MovieResponse>() {

                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse movieResponse;
                    movieResponse = response.body();
                    moviesList.postValue(movieResponse);
                }

                @Override
                public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

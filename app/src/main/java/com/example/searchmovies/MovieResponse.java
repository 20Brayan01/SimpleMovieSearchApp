package com.example.searchmovies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public List<MovieModel> getItems() {
        return movies;
    }

    public void setItems(List<MovieModel> movies) {
        this.movies = movies;
    }

    public List<MovieModel> getMovies(){
        return movies;
    }
}

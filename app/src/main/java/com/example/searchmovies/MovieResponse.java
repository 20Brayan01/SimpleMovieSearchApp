package com.example.searchmovies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    public List<Movie> getItems() {
        return movies;
    }

    public void setItems(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies(){
        return movies;
    }
}

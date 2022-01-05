package com.example.searchmovies;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private String title;
    private String poster_path;
    private String release_date;
    @SerializedName("movie_id")
    private int movieId;
    private float vote_average;
    private String movie_overview;

    //Constructor
    public Movie(String title, String poster_path, String release_date, int movie_id, float vote_average, String movie_overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movieId = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovieId() {
        return movieId;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getMovieOverview() {
        return movie_overview;
    }
}

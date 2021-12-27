package com.example.searchmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular/?api_key=bfb7fd8f442e030e738ab0fb17221100")
    Call<MovieResponse> getMovies();

    @GET("search/movie/?api_key=bfb7fd8f442e030e738ab0fb17221100")
    Call<MovieResponse> searchMovies(
            @Query("query") String query
    );
}
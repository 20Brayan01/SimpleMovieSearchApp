package com.example.searchmovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
//
//    public static final String BASE_URL = "https://api.themoviedb.org/3/";
//
//    public static final String API_KEY = "bfb7fd8f442e030e738ab0fb17221100";
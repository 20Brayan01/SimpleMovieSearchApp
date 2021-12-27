package com.example.searchmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("items")
    private List<MovieModel> items;

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<MovieModel> getItems() {
        return items;
    }
}
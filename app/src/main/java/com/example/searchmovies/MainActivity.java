package com.example.searchmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView disconnectedTV;
    private Adapter itemAdapter;
    private List<MovieModel> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type movie name to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    return true;
    }

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJson();
    }


    public void loadJson() {
        disconnectedTV = (TextView) findViewById(R.id.tv_disconnect);
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
                    recyclerView.setAdapter(new Adapter(MainActivity.this, movieResponse.getItems()));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    disconnectedTV.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void search(String  query) {
        disconnectedTV = (TextView) findViewById(R.id.tv_disconnect);
        try {
            Client client = new Client();
            Service apiService =
                    client.getClient().create(Service.class);
            Call<MovieResponse> call = apiService.searchMovies(query);
            call.enqueue(new Callback<MovieResponse>() {

                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse movieResponse;
                    Log.d("status code", String.valueOf(response.code()));
                    movieResponse = response.body();
                    recyclerView.setAdapter(new Adapter(MainActivity.this, movieResponse.getItems()));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    disconnectedTV.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
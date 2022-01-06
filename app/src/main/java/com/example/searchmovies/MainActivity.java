package com.example.searchmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewPager2 viewPager;
    private Adapter itemAdapter;
    private SliderRecyclerAdapter sliderRecyclerAdapter;
    boolean isLoading = false;
    int defaultPage = 1, limit = 3;
    int currentPage = defaultPage;
    String currentSearchTerm;
    private SearchMovieViewModel searchMovieViewModel;
    private LoadMoviesViewModel loadMoviesViewModel;
    private SliderMovieViewModel sliderMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchMovieViewModel = new ViewModelProvider(this).get(SearchMovieViewModel.class);
        loadMoviesViewModel = new ViewModelProvider(this).get(LoadMoviesViewModel.class);
        sliderMovieViewModel = new ViewModelProvider(this).get(SliderMovieViewModel.class);

        itemAdapter = new Adapter(this, new ArrayList<>());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.smoothScrollToPosition(0);

        //Slider
        viewPager = findViewById(R.id.slide_view_pager);
        sliderRecyclerAdapter = new SliderRecyclerAdapter(MainActivity.this, new ArrayList<>(), viewPager); //TODO
        viewPager.setAdapter(sliderRecyclerAdapter);
        sliderMovieViewModel
                .getMoviesListObserver().observe(this, new Observer<MovieResponse>() {
                    @Override
                    public void onChanged(MovieResponse movieResponse) {
                        sliderRecyclerAdapter.addMovie(movieResponse.getMovies());
                    }

                });
        sliderMovieViewModel.sliderLoadJson();

        loadMoviesViewModel.getMoviesListObserver().observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse movieResponse) {
                itemAdapter.addMovie(movieResponse.getMovies());
            }
        });
        loadMoviesViewModel.loadJson();
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
                currentSearchTerm = query;
                currentPage = defaultPage;
                itemAdapter.resetList();
                searchMovieViewModel.search(currentSearchTerm, currentPage);
                searchMovieViewModel.getMoviesListObserver().observe(MainActivity.this, new Observer<MovieResponse>() {
                    @Override
                    public void onChanged(MovieResponse movieResponse) {
                        itemAdapter.addMovie(movieResponse.getMovies());
                    }
                });
                currentPage++;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
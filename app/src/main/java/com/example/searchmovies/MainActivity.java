package com.example.searchmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    private RecyclerView sliderView;
    private ViewPager2 viewPager;
    private Adapter itemAdapter;
    private SliderRecyclerAdapter sliderRecyclerAdapter;
    private List<Movie> movies;
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
                currentSearchTerm = query;
                currentPage = defaultPage;
                itemAdapter.resetList();
                searchMovieViewModel.search(currentSearchTerm, currentPage);
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

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        //itemAdapter = new Adapter(MainActivity.this, new ArrayList<>());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.smoothScrollToPosition(0);
        loadMoviesViewModel.loadJson();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = lm.getChildCount();
                int totalItemCount = lm.getItemCount();
                int pastVisibleItems = lm.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    //End of list
                    if (currentPage < limit && !isLoading && currentSearchTerm != null) {
                        ++currentPage;
                        searchMovieViewModel.search(currentSearchTerm, currentPage);

                    }
                }
            }
        });
        viewPager = findViewById(R.id.slide_view_pager);
        //sliderView = findViewById(R.id.slide_recycler);
        //sliderView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        //sliderView.smoothScrollToPosition(0);
        //    sliderRecyclerAdapter = new SliderRecyclerAdapter(MainActivity.this, new ArrayList<>(), viewPager); //TODO
        //sliderView.setAdapter(sliderRecyclerAdapter);
        viewPager.setAdapter(sliderRecyclerAdapter);
        sliderMovieViewModel.sliderLoadJson();
    }
}
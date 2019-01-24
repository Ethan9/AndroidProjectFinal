package com.example.ethan.androidprojectfinal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import static com.example.ethan.androidprojectfinal.NewFestivalActivity.EXTRA_REPLY;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_FESTIVAL_ACTIVITY_REQUEST_CODE = 1;
    public static final String MAP_EXTRA = "ok";

    private FestivalViewModel mFestivalViewModel;
    private List<Festival> festivalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FestivalAdapter adapter = new FestivalAdapter(this, new FestivalAdapter.FestivalClickListener() {
            @Override
            public void festivalOnClick(int i) {
                Festival festival = festivalList.get(i);
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra(MAP_EXTRA, festival);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mFestivalViewModel = ViewModelProviders.of(this).get(FestivalViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mFestivalViewModel.getmAllFestivals().observe(this, new Observer<List<Festival>>() {
            @Override
            public void onChanged(@Nullable final List<Festival> festivals) {
                // Update the cached copy of the words in the adapter.
                adapter.setmFestivals(festivals);
                festivalList = festivals;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewFestivalActivity.class);
                startActivityForResult(intent, NEW_FESTIVAL_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FESTIVAL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Festival festival = (Festival) data.getParcelableExtra(EXTRA_REPLY);
            mFestivalViewModel.insert(festival);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}


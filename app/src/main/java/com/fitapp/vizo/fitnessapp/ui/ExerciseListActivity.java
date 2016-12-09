package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.adapters.ExerciseListAdapter;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.services.WgerCallService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ExerciseListActivity extends AppCompatActivity {

    public ArrayList<Exercise> mExercises = new ArrayList<>();
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ExerciseListAdapter mAdapter;
    @Bind(R.id.spinnyBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String muscle = intent.getStringExtra("muscle");
        getExercises(muscle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getExercises(String muscleSelected) {
        final WgerCallService wgerCallService = new WgerCallService();
        wgerCallService.findExercises(muscleSelected, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                mExercises = wgerCallService.processResults(response);

                ExerciseListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        mAdapter = new ExerciseListAdapter(getApplicationContext(), mExercises);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExerciseListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(false);

                    }
                });
            }
        });
    }
}

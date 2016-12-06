package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.adapters.ExerciseListAdapter;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.services.WgerCallService;

import java.io.IOException;
import java.lang.reflect.Array;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String muscle = intent.getStringExtra("muscle");
        getExercises(muscle);


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
                        Log.d("test", mExercises.get(0).getName());
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

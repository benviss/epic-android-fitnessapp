package com.fitapp.vizo.fitnessapp.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.adapters.ExercisePagerAdapter;
import com.fitapp.vizo.fitnessapp.models.Exercise;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExerciseDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ExercisePagerAdapter adapterViewPager;
    ArrayList<Exercise> mExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        ButterKnife.bind(this);

        mExercises = Parcels.unwrap(getIntent().getParcelableExtra("exercises"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ExercisePagerAdapter(getSupportFragmentManager(), mExercises);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

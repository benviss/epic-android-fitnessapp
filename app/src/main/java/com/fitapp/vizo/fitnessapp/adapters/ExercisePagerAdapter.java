package com.fitapp.vizo.fitnessapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.ui.ExerciseDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/2/16.
 */
public class ExercisePagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Exercise> mExercises;

    public ExercisePagerAdapter(FragmentManager fm, ArrayList<Exercise> exercises) {
        super(fm);
        mExercises = exercises;
    }

    @Override
    public Fragment getItem(int position) {
        return ExerciseDetailFragment.newInstance(mExercises.get(position));
    }

    @Override
    public int getCount() {
        return mExercises.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mExercises.get(position).getName();
    }
}

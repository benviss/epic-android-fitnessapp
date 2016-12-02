package com.fitapp.vizo.fitnessapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.Exercise;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDetailFragment extends Fragment {
    @Bind(R.id.exerciseNameTextView) TextView mExerciseNameTextView;
    @Bind(R.id.primaryMuscleTextView) TextView mPrimaryMuscleTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.secondaryMuscleTextView) TextView mSecondaryMuscleTextView;

    private Exercise mExercise;

    public static ExerciseDetailFragment newInstance(Exercise exercise) {
        ExerciseDetailFragment exerciseDetailFragment = new ExerciseDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("exercise", Parcels.wrap(exercise));
        exerciseDetailFragment.setArguments(args);
        return exerciseDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExercise = Parcels.unwrap(getArguments().getParcelable("exercise"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        ButterKnife.bind(this, view);

        mExerciseNameTextView.setText(mExercise.getName());
        mPrimaryMuscleTextView.setText("Primary Muscle Changed");
        mSecondaryMuscleTextView.setText("Secondary Muscle Changed");
        mDescriptionTextView.setText(mExercise.getDescription());
        return view;
    }

}

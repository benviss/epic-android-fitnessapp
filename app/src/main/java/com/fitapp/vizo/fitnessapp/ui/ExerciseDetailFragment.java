package com.fitapp.vizo.fitnessapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.services.WgerConversions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ExerciseDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.setWeight) EditText setWeightField;
    @Bind(R.id.setReps) EditText setRepsField;
    @Bind(R.id.addSetButton) Button addSetButton;
    @Bind(R.id.favoriteStar)
    ImageView favoriteStar;

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
        String primaryMuscles = WgerConversions.convertMuscles(mExercise.getMuscles());
        String secondaryMuscles = WgerConversions.convertMuscles(mExercise.getSecondaryMuscles());
        mPrimaryMuscleTextView.setText(primaryMuscles);
        mSecondaryMuscleTextView.setText("Secondary Muscles: " + secondaryMuscles);
        mDescriptionTextView.setText(mExercise.getDescription());
        addSetButton.setOnClickListener(this);
        favoriteStar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == favoriteStar.getId()) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference exerciseRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_EXERCISES)
                    .child(uid);

            DatabaseReference pushRef = exerciseRef.push();
            String pushId = pushRef.getKey();
            mExercise.setPushId(pushId);
            pushRef.setValue(mExercise);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            v.setActivated(true);
        }
    }
}

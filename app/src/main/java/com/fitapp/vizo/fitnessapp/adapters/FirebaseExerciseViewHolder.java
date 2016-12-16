package com.fitapp.vizo.fitnessapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.services.WgerConversions;
import com.fitapp.vizo.fitnessapp.ui.ExerciseDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/9/16.
 */
public class FirebaseExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    View mView;
    Context mContext;

    public FirebaseExerciseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindExercise(Exercise exercise) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.exerciseNameTextView);
        TextView muscleTextView = (TextView) mView.findViewById(R.id.primaryMuscleTextView);

        nameTextView.setText(exercise.getName());
        muscleTextView.setText(WgerConversions.convertMuscles(exercise.getMuscles()));
    }

    @Override
    public void onClick(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        final ArrayList<Exercise> exercises = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EXERCISES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    exercises.add(snapshot.getValue(Exercise.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ExerciseDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("exercises", Parcels.wrap(exercises));


                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }




}

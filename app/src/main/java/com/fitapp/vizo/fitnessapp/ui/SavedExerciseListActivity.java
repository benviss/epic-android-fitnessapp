package com.fitapp.vizo.fitnessapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.adapters.FirebaseExerciseViewHolder;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedExerciseListActivity extends AppCompatActivity {
    private DatabaseReference mExerciseReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.spinnyBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mExerciseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXERCISES)
                .child(uid);

        setUpFirebaseAdapter();


    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Exercise, FirebaseExerciseViewHolder>
                (Exercise.class, R.layout.exercise_list_item, FirebaseExerciseViewHolder.class,
                        mExerciseReference) {

            @Override
            protected void populateViewHolder(FirebaseExerciseViewHolder viewHolder,
                                              Exercise model, int position) {
                progressBar.setVisibility(View.GONE);
                viewHolder.bindExercise(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}


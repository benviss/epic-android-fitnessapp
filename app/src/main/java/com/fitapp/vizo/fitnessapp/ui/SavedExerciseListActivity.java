package com.fitapp.vizo.fitnessapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ProgressBar;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.adapters.FirebaseExerciseListAdapter;
import com.fitapp.vizo.fitnessapp.adapters.FirebaseExerciseViewHolder;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.util.OnStartDragListener;
import com.fitapp.vizo.fitnessapp.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedExerciseListActivity extends AppCompatActivity implements OnStartDragListener{
    private DatabaseReference mExerciseReference;
    private FirebaseExerciseListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.spinnyBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise_list);
        ButterKnife.bind(this);



        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mExerciseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXERCISES)
                .child(uid);

        mFirebaseAdapter = new FirebaseExerciseListAdapter(Exercise.class,
                R.layout.exercise_list_item, FirebaseExerciseViewHolder.class,
                mExerciseReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}


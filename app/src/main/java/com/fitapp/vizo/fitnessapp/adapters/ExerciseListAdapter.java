package com.fitapp.vizo.fitnessapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.Exercise;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{

    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private Context mContext;

    public ExerciseListAdapter(Context context, ArrayList<Exercise> exercises) {
        mContext = context;
        mExercises = exercises;
    }

    @Override
    public ExerciseListAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_item, parent, false);

        ExerciseViewHolder viewHolder = new ExerciseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExerciseListAdapter.ExerciseViewHolder holder, int position) {
        holder.bindExercise(mExercises.get(position));
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.exerciseNameTextView) TextView mExerciseTextView;
        @Bind(R.id.primaryMuscleTextView) TextView mPrimaryMuscleTextView;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindExercise(Exercise exercise) {
            mExerciseTextView.setText(exercise.getName());
            mPrimaryMuscleTextView.setText(exercise.getDescription());

        }
    }
}

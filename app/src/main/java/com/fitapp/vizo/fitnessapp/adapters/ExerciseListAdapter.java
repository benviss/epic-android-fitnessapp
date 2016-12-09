package com.fitapp.vizo.fitnessapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.ui.ExerciseDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{

    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private ArrayList<Exercise> mExercisesCopy = new ArrayList<>();
    private Context mContext;

    public ExerciseListAdapter(Context context, ArrayList<Exercise> exercises) {
        mContext = context;
        mExercises = exercises;
        mExercisesCopy.addAll(exercises);
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

    public class ExerciseViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        @Bind(R.id.exerciseNameTextView) TextView mExerciseTextView;
        @Bind(R.id.primaryMuscleTextView) TextView mPrimaryMuscleTextView;
        private String mSelectedMuscle;
        private SharedPreferences mSharedPreferences;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            mSelectedMuscle = mSharedPreferences.getString(Constants.PREFERENCES_MUSCLE_KEY, "Primary Muscle");

            itemView.setOnClickListener(this);
        }

        public void bindExercise(Exercise exercise) {
            mExerciseTextView.setText(exercise.getName());
            mPrimaryMuscleTextView.setText(mSelectedMuscle);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ExerciseDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("exercises", Parcels.wrap(mExercises));
            mContext.startActivity(intent);
        }
    }

    public void filter(String text) {
        mExercises.clear();
        if(text.isEmpty()){
            mExercises.addAll(mExercisesCopy);
        } else{
            text = text.toLowerCase();
            for(Exercise exercise: mExercisesCopy){
                if(exercise.getName().toLowerCase().contains(text)){
                    mExercises.add(exercise);
                }
            }
        }
        notifyDataSetChanged();
    }
}

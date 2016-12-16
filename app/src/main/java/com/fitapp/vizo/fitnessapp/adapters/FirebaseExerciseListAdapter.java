package com.fitapp.vizo.fitnessapp.adapters;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.util.ItemTouchHelperAdapter;
import com.fitapp.vizo.fitnessapp.util.OnStartDragListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Guest on 12/16/16.
 */
public class FirebaseExerciseListAdapter extends FirebaseRecyclerAdapter<Exercise, FirebaseExerciseViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseExerciseListAdapter(Class<Exercise> modelClass, int modelLayout,
                                       Class<FirebaseExerciseViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseExerciseViewHolder viewHolder, Exercise model, int position) {
        viewHolder.bindExercise(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }


    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }
}

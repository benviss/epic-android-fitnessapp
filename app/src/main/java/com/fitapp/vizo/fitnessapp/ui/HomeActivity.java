package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.User;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    User currentUser;
    @Bind(R.id.muscle1) Button mMuscle1;
    @Bind(R.id.muscle2) Button mMuscle2;
    @Bind(R.id.muscle3) Button mMuscle3;
    @Bind(R.id.muscle4) Button mMuscle4;
    @Bind(R.id.muscle5) Button mMuscle5;
    @Bind(R.id.muscle6) Button mMuscle6;
    @Bind(R.id.muscle7) Button mMuscle7;
    @Bind(R.id.muscle8) Button mMuscle8;
    @Bind(R.id.muscle9) Button mMuscle9;
    @Bind(R.id.muscle10) Button mMuscle10;
    @Bind(R.id.muscle11) Button mMuscle11;
    @Bind(R.id.muscle12) Button mMuscle12;
    @Bind(R.id.muscle13) Button mMuscle13;
    @Bind(R.id.muscle14) Button mMuscle14;
    @Bind(R.id.muscle15) Button mMuscle15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Intent receiveUser = getIntent();
        currentUser = (User) receiveUser.getSerializableExtra("userSelected");
        mMuscle1.setOnClickListener(this);
        mMuscle2.setOnClickListener(this);
        mMuscle3.setOnClickListener(this);
        mMuscle4.setOnClickListener(this);
        mMuscle5.setOnClickListener(this);
        mMuscle6.setOnClickListener(this);
        mMuscle7.setOnClickListener(this);
        mMuscle8.setOnClickListener(this);
        mMuscle9.setOnClickListener(this);
        mMuscle10.setOnClickListener(this);
        mMuscle11.setOnClickListener(this);
        mMuscle12.setOnClickListener(this);
        mMuscle13.setOnClickListener(this);
        mMuscle14.setOnClickListener(this);
        mMuscle15.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        Button muscleNameButton = (Button) v;
        String muscleName = muscleNameButton.getText().toString();
        mEditor.putString(Constants.PREFERENCES_MUSCLE_KEY, muscleName).apply();
        String muscleSelected = v.getResources().getResourceEntryName(v.getId());
        String muscleNumber = muscleSelected.substring(6);
        Intent exerciseListIntent = new Intent(HomeActivity.this, ExerciseListActivity.class);
        exerciseListIntent.putExtra("muscle", muscleNumber);
        startActivity(exerciseListIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_saved_exercises) {
            Intent intent = new Intent(HomeActivity.this, SavedExerciseListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



}

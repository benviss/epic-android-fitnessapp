package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.User;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import com.fitapp.vizo.fitnessapp.services.WgerCallService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public ArrayList<Exercise> exercises = new ArrayList<>();
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
    }

    @Override
    public void onClick(View v) {
        String muscleSelected = v.getResources().getResourceEntryName(v.getId());
        int muscleNumber = Integer.parseInt(muscleSelected.substring(6));
//        Log.d("Test", muscleNumber);
    }


    private void getExercises() {
        final WgerCallService wgerCallService = new WgerCallService();
        wgerCallService.findExercises(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                exercises = wgerCallService.processResults(response);
            }
        });
    }
}

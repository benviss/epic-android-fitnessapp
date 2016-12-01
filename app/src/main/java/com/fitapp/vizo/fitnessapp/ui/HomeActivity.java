package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class HomeActivity extends AppCompatActivity {
    public ArrayList<Exercise> exercises = new ArrayList<>();
    @Bind(R.id.muscleGroupSelected) TextView muscleGroupSelected;
    @Bind(R.id.exerciseList)
    ListView mListView;
    @Bind(R.id.userFirstName) TextView userFirstName;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Intent receiveUser = getIntent();
        currentUser = (User) receiveUser.getSerializableExtra("userSelected");
//        userFirstName.setText(currentUser.getFirstName());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, exercises);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String exercise = ((TextView)view).getText().toString();
                Toast.makeText(HomeActivity.this, exercise, Toast.LENGTH_LONG).show();
            }
        });

        getExercises();
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

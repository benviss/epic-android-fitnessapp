package com.fitapp.vizo.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.muscleGroupSelected) TextView muscleGroupSelected;
    @Bind(R.id.exerciseList)
    ListView mListView;
    @Bind(R.id.userFirstName) TextView userFirstName;
    User currentUser;

    private String[] exercises = new String[] {"Incline Hammer Curls", "Incline Inner-Biceps Curl", "Standing Concentration Curl", "EZ Bar Curl",
            "Wide-grip standing barbell curl", "Barbell Curl", "Dumbbell Biceps Curl"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Intent receiveUser = getIntent();
        currentUser = (User) receiveUser.getSerializableExtra("userSelected");
        userFirstName.setText(currentUser.getFirstName());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, exercises);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String exercise = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, exercise, Toast.LENGTH_LONG).show();
            }
        });
    }

}

package com.fitapp.vizo.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.userFirstName) TextView userFirstName;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Intent receiveUser = getIntent();
        currentUser = (User) receiveUser.getSerializableExtra("userSelected");
        userFirstName.setText(currentUser.getFirstName());
    }

}

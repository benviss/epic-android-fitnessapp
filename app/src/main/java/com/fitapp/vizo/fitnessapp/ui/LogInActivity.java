package com.fitapp.vizo.fitnessapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fitapp.vizo.fitnessapp.R;


public class LogInActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LogInActivity.this, HomeActivity.class);
                startActivity(loginIntent);
            }
        });
    }
    public void onClickUserSignUp(View v) {
        Intent signUpIntent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

}

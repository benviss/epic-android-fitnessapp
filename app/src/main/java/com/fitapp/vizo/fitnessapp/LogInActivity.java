package com.fitapp.vizo.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "Log in functionality not yet enabled", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClickUserSignUp(View v) {
        Intent signUpIntent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

}

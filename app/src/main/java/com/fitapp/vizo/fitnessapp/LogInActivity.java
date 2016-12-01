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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.signUpUser) TextView mSignUpUser;
    @Bind(R.id.logInButton) Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mSignUpUser.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == mLogInButton.getId()) {
            //// TODO: 12/1/16 Will eventually do user Authentication, will set up once firebase has been implemented
            Toast.makeText(LogInActivity.this, "Log in functionality not yet enabled", Toast.LENGTH_LONG).show();
        } else if (v.getId() == mSignUpUser.getId()) {
            Intent signUpIntent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }
}
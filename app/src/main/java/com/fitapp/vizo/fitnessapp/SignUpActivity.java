package com.fitapp.vizo.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    @Bind(R.id.newUserFirstName) EditText newUserFirstNameField;
    @Bind(R.id.newUserLastName) EditText newUserLastNameField;
    @Bind(R.id.newUserUsername) EditText newUserUsernameField;
    @Bind(R.id.newUserPassword) EditText newUserPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
    }


//Creates new User and loges user contact info
    public void onClickSubmitUserContact(View v) {
        String newUserFirstNameInput  = newUserFirstNameField.getText().toString();
        String newUserLastNameInput = newUserLastNameField.getText().toString();
        String newUserUsernameInput = newUserUsernameField.getText().toString();
        String newUserPasswordInput = newUserPasswordField.getText().toString();

        User newUser = new User(newUserFirstNameInput, newUserLastNameInput, newUserUsernameInput, newUserPasswordInput);
    }


}

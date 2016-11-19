package com.fitapp.vizo.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    @Bind(R.id.newUserFirstName) EditText newUserFirstNameField;
    @Bind(R.id.newUserLastName) EditText newUserLastNameField;
    @Bind(R.id.newUserUsername) EditText newUserUsernameField;
    @Bind(R.id.newUserPassword) EditText newUserPasswordField;
    @Bind(R.id.userWeightInput) EditText newUserWeightField;
    @Bind(R.id.userHeightInput) EditText newUserHeightField;
    @Bind(R.id.userBirthInput) EditText newUserBirthDateField;
    @Bind(R.id.userTargetWeight) EditText newUserTargetWeightField;

    ViewFlipper viewflipper;
    private String genderSelected = "";
    private String goalSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.contactInfoView)));
    }




//Creates new User and loges user contact info
    public void onClickSubmitUserInfo(View v) {
        String newUserFirstNameInput  = newUserFirstNameField.getText().toString();
        String newUserLastNameInput = newUserLastNameField.getText().toString();
        String newUserUsernameInput = newUserUsernameField.getText().toString();
        String newUserPasswordInput = newUserPasswordField.getText().toString();
        int newUserWeight = 0;
        if(!(newUserWeightField.getText().toString().equals(""))) {
            newUserWeight  = Integer.parseInt(newUserWeightField.getText().toString());
        }
        String newUserHeight = newUserHeightField.getText().toString();
        String newUserBirthDate = newUserBirthDateField.getText().toString();

        int newUserTargetWeight = 0;
        if(!(newUserTargetWeightField.getText().toString().equals(""))) {
            newUserTargetWeight  = Integer.parseInt(newUserTargetWeightField.getText().toString());
        }
        if(
                (newUserFirstNameInput.equals("")) ||
                (genderSelected.equals("")) ||
                (newUserLastNameInput.equals("")) ||
                (newUserUsernameInput.equals("")) ||
                (newUserPasswordInput.equals("")) ||
                (newUserWeight == 0) ||
                (newUserHeight.equals("")) ||
                (newUserBirthDate.equals("")) ||
                (newUserTargetWeight == 0) ||
                (goalSelected.equals(""))) {
            Toast.makeText(SignUpActivity.this, "Please provide information for all fields", Toast.LENGTH_SHORT).show();
            Log.d("test", newUserFirstNameInput + "-1- " + newUserLastNameInput + "--" + newUserUsernameInput + "--" + newUserPasswordInput + "--" + newUserWeight + "--" + newUserHeight + "--" + newUserBirthDate + "--" + newUserTargetWeight + "--" + goalSelected);
        } else {

            Log.d("success", newUserFirstNameInput + "-1- " + newUserLastNameInput + "--" + newUserUsernameInput + "--" + newUserPasswordInput + "--" + newUserWeight + "--" + newUserHeight + "--" + newUserBirthDate + "--" + newUserTargetWeight + "--" + goalSelected);
            User newUser = new User(newUserFirstNameInput, newUserLastNameInput, newUserUsernameInput, newUserPasswordInput, newUserWeight, newUserHeight, newUserBirthDate, goalSelected, genderSelected, newUserTargetWeight);
            Intent newIntent = new Intent(SignUpActivity.this, MainActivity.class);
            newIntent.putExtra("userSelected", newUser);
            startActivity(newIntent);
        }
    }

    public void onClickContinue(View v) {
        if (v.getId() == (R.id.continueButton1)) {
            String newUserGreeting = newUserFirstNameField.getText().toString();
            Toast.makeText(SignUpActivity.this, "Welcome to VFit, " + newUserGreeting + ".", Toast.LENGTH_SHORT).show();
            viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.healthInfoView)));
        } else {
            viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.goalInfoView)));
        }

    }

    public void onClickBack(View v) {
        if (v.getId() == (R.id.backButton1)) {
            viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.contactInfoView)));
        } else {
            viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.healthInfoView)));
        }
    }

    public void onClickGenderSet(View v){
        if(v.getId() == (R.id.userGenderMaleRadio)) {
            genderSelected = "Male";
        } else {
            genderSelected = "Female";
        }
    }

    public void onClickUserGoal(View v){
        if(v.getId() == (R.id.loseWeight)) {
            goalSelected = "Lose Weight";
        } else if(v.getId() == (R.id.loseWeight)){
            goalSelected = "Maintain Weight";
        } else {
            goalSelected = "Gain Weight";
        }
    }



}

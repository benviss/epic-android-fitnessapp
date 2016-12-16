package com.fitapp.vizo.fitnessapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.R;
import com.fitapp.vizo.fitnessapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity  {
    public static final String TAG = SignUpActivity.class.getSimpleName();

    @Bind(R.id.newUserFirstName) EditText mName;
    @Bind(R.id.newUserLastName) EditText mLastname;
    @Bind(R.id.newEmail) EditText mEmail;
    @Bind(R.id.newUserPassword) EditText mPassword;
    @Bind(R.id.confirmPassword) EditText mConfirmPassword;
    @Bind(R.id.userWeightInput) EditText mWeight;
    @Bind(R.id.userHeight) EditText mHeight;
    @Bind(R.id.userBirthInput) EditText mBirth;
    @Bind(R.id.userTargetWeight) EditText mTargetWeight;
    @Bind(R.id.loseWeight) RadioButton mLoseWeight;
    @Bind(R.id.gainWeight) RadioButton mGainWeight;
    @Bind(R.id.maintainWeight) RadioButton mMaintainWeight;

    private FirebaseAuth mAuth;
    private ProgressDialog mAuthProgressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String name;

    ViewFlipper viewflipper;
    private String genderSelected = "";
    private String goalSelected = "";
    private int userTargetWeight = 0;
    private boolean validatedPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        viewflipper.setDisplayedChild(viewflipper.indexOfChild(findViewById(R.id.contactInfoView)));

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    //Creates new User and moves to MainActivity
    public void createAccount(View v) {
        name = mName.getText().toString().trim();
        final String newName  = mName.getText().toString().trim();
        final String newLastname = mLastname.getText().toString().trim();
        final String newEmail = mEmail.getText().toString().trim();
        final String newPassword = mPassword.getText().toString().trim();
        final String confirmPassword = mConfirmPassword.getText().toString().trim();
        final String newWeight = mWeight.getText().toString().trim();
        final String newHeight = mHeight.getText().toString().trim();
        final String newBirthdate = mBirth.getText().toString().trim();
        final String newTargetWeight = mTargetWeight.getText().toString().trim();
        boolean validName = isValidName(newName);
        boolean validLast = isValidLastname(newLastname);
        boolean validEmail = isValidEmail(newEmail);
        boolean validPassword = isValidPassword(newPassword, confirmPassword);
        boolean validWeight = isValidWeight(newWeight);
        boolean validHeight = isValidHeight(newHeight);
        boolean validBirth= isValidBirth(newBirthdate);
        boolean validGoal= true;
        boolean validTargetWeight = isValidTargetWeight(newTargetWeight, newWeight);

        if (!validTargetWeight || !validName || !validLast || !validEmail || !validPassword || !validWeight || !validHeight || !validBirth || !validGoal) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(newEmail, newPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                            createFirebaseUserProfile(task.getResult().getUser());
                            int intWeight = Integer.parseInt(newWeight);
                            User newUser = new User(newName, newLastname, newEmail, newPassword, intWeight, newHeight, newBirthdate, goalSelected, genderSelected, userTargetWeight);
                            DatabaseReference userRef = FirebaseDatabase
                                    .getInstance()
                                    .getReference(Constants.FIREBASE_CHILD_USERS);
                            userRef.push().setValue(newUser);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //continue button switches viewflipper
    public void onClickContinue(View v) {
        if (v.getId() == (R.id.continueButton1)) {
            String newUserGreeting = mName.getText().toString();
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
    //records radio button to change
    public void onClickUserGoal(View v){
        if(v.getId() == (mLoseWeight.getId())) {
            mGainWeight.setChecked(false);
            mMaintainWeight.setChecked(false);
            goalSelected = "Lose Weight";
        } else if(v.getId() == (mMaintainWeight.getId())){
            goalSelected = "Maintain Weight";
            mLoseWeight.setChecked(false);
            mGainWeight.setChecked(false);
        } else {
            goalSelected = "Gain Weight";
            mLoseWeight.setChecked(false);
            mMaintainWeight.setChecked(false);
        }
    }

//    user input validations
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String nameField) {
        if (nameField.equals("")) {
            mName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidLastname(String lastname) {
        if (lastname.equals("")) {
            mLastname.setError("Please enter your last name");
            return false;
        }
        return true;
    }
    private boolean isValidWeight(String weight) {
        if (weight.equals("")) {
            mLastname.setError("Please enter your weight");
            return false;
        }
        int intWeight = Integer.parseInt(weight);
        if (intWeight <= 0) {
            mWeight.setError("Please a weight greater than 0");
            return false;
        }
        return true;
    }
    private boolean isValidHeight(String height) {
        if (height.equals("")) {
            mHeight.setError("Please enter your height");
            return false;
        }
        return true;
    }
    private boolean isValidBirth(String birth) {
        if (birth.equals("")) {
            mBirth.setError("Please enter your birthdate");
            return false;
        }
        return true;
    }
    private boolean isValidGoal() {
        if (goalSelected.equals("")) {
            Toast.makeText(this, "Please enter a goal", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidTargetWeight(String targetWeight, String weight) {
        if (weight.equals("")) return false;
        if (genderSelected.equals("")) return false;
        if (goalSelected.equals("")) return false;
        if (goalSelected.equals("Maintain Weight")) {
            userTargetWeight = Integer.parseInt(weight);
            return true;
        }
        if (targetWeight.equals("")) return false;
        int intTarget = Integer.parseInt(targetWeight);
        if (intTarget <= 0) {
            mWeight.setError("Please a target weight greater than 0");
            return false;
        }
        return true;
    }


    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }


    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, user.getDisplayName());
                        }
                    }

                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

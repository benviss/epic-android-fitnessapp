package com.fitapp.vizo.fitnessapp;

/**
 * Created by Guest on 11/18/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int weight;
    private String height;
    private String birthdate;
    private String gender;
    private String goal;
    private int targetWeight;



    public User(String newFirstName, String newUserLastNameInput, String newUserUsernameInput, String newUserPasswordInput, int newUserWeight, String newUserHeight,String  newUserBirthDate, String newUserGoal, String newUserGender, int newUserTargetWeight)
    {
        this.firstName = newFirstName;
        this.lastName = newUserLastNameInput;
        this.username = newUserUsernameInput;
        this.password = newUserPasswordInput;
        this.weight = newUserWeight;
        this.height = newUserHeight;
        this.gender = newUserGender;
        this.goal = newUserGoal;
        this.targetWeight = newUserTargetWeight;
        this.birthdate = newUserBirthDate;

    }



//    Getters for User Info
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getHeight() {
        return this.height;
    }
    public String getBirthdate() {
        return this.birthdate;
    }
    public String getGender() {
        return this.gender;
    }
    public String getGoal() {
        return this.goal;
    }
    public int getWeight() {
        return this.weight;
    }
    public int getTargetWeight() {
        return this.targetWeight;
    }



//
}

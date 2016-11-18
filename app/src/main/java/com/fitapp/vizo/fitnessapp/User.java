package com.fitapp.vizo.fitnessapp;

/**
 * Created by Guest on 11/18/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;


    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
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
//
}

package com.fitapp.vizo.fitnessapp.models;

/**
 * Created by Guest on 12/6/16.
 */
public class Workout {
    private Exercise exercise;
    private int[] sets;

    public Workout(Exercise exercise, int[] sets) {
        this.exercise = exercise;
        this.sets = sets;
    }
}

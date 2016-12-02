package com.fitapp.vizo.fitnessapp.models;

import org.parceler.Parcel;
import java.util.ArrayList;

@Parcel
public class Exercise {
    private int id;
    private String name;
    private String description;
    private ArrayList<Integer> muscles;
    private ArrayList<Integer> secondaryMuscles;
    private ArrayList<Integer> equipment;
    private int category;

    public Exercise() {}

    public Exercise(int id, String name, String description, ArrayList<Integer> muscles, ArrayList<Integer> secondaryMuscles, ArrayList<Integer> equipment, int category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscles = muscles;
        this.secondaryMuscles = secondaryMuscles;
        this.equipment = equipment;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getMuscles() {
        return muscles;
    }

    public void setMuscles(ArrayList<Integer> muscles) {
        this.muscles = muscles;
    }

    public ArrayList<Integer> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(ArrayList<Integer> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }

    public ArrayList<Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Integer> equipment) {
        this.equipment = equipment;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

package com.fitapp.vizo.fitnessapp.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import com.fitapp.vizo.fitnessapp.Constants;
import com.fitapp.vizo.fitnessapp.models.Exercise;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WgerCallService {

    public static void findExercises(String muscle, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WGER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WGER_MUSCLE_QUERY, muscle);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Exercise> processResults(Response response) {
        ArrayList<Exercise> exercises = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject wgerResultsJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = wgerResultsJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject exerciseJSON = resultsJSON.getJSONObject(i);
                    int id = Integer.parseInt(exerciseJSON.optString("id", "-1"));
                    String name = exerciseJSON.optString("name", "Name Not Found");
                    int category = Integer.parseInt(exerciseJSON.optString("category", "-1"));
                    String description = exerciseJSON.optString("description", "Description Not Found");
                    ArrayList<Integer> muscles = new ArrayList<>();
                    JSONArray muscleArray = exerciseJSON.getJSONArray("muscles");
                    for (int j = 0; j < muscleArray.length(); j++) {
                        int muscle = Integer.parseInt(muscleArray.getString(j));
                        muscles.add(muscle);
                    }
                    JSONArray secondaryMuscleArray = exerciseJSON.getJSONArray("muscles_secondary");
                    ArrayList<Integer> secondaryMuscles = new ArrayList<>();
                    for (int j = 0; j < secondaryMuscleArray.length(); j++) {
                        int muscle = Integer.parseInt(secondaryMuscleArray.getString(j));
                        secondaryMuscles.add(muscle);
                    }
                    JSONArray equipmentJSON = exerciseJSON.getJSONArray("equipment");
                    ArrayList<Integer> equipment = new ArrayList<>();
                    for (int j = 0; j < equipmentJSON.length(); j++) {
                        int equip = Integer.parseInt(equipmentJSON.getString(j));
                        equipment.add(equip);
                    }
                    Exercise newExercise = new Exercise(id, name, description, muscles ,secondaryMuscles ,equipment ,category);
                    Log.d("ben", newExercise.getName());
                    exercises.add(newExercise);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exercises;
    }
}

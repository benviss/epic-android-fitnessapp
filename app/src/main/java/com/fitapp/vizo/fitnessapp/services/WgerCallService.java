package com.fitapp.vizo.fitnessapp.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WgerCallService {

    public static void findExercises(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WGER_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.WEATHER_API_QUERY, Constants.WEATHER_KEY);
//        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY, location);
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
                    Log.d("Test", "id = " + id + "|" + " | name = " + name + "|" + "  |Category = " + category + "|");

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

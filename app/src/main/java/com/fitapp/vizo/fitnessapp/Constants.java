package com.fitapp.vizo.fitnessapp;

import android.os.Build;

import com.fitapp.vizo.fitnessapp.BuildConfig;

/**
 * Created by Guest on 12/1/16.
 */
public class Constants {
    public static final String WGER_API_KEY = BuildConfig.WGER_API_KEY;
    public static final String WGER_BASE_URL = "https://wger.de/api/v2/exercise/?language=2&status=2&limit=50";
    public static final String WGER_MUSCLE_QUERY = "muscles";

    public static final String PREFERENCES_MUSCLE_KEY= "muscle";
}

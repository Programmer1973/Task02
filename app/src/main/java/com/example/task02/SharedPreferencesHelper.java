package com.example.task02;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences mSharedPreferences;
    public static final String SHARED_PREF_HELPER_KEY = "SharedPreferencesHelper.Key";
    public static final String BUTTON_KEY = "SharedPreferencesButton.Key";

    public SharedPreferencesHelper(Activity activity) {
        mSharedPreferences = activity.getSharedPreferences(SHARED_PREF_HELPER_KEY, Context.MODE_PRIVATE);
    }

    public void addPressedRadioButton(String str) {
        mSharedPreferences.edit().clear().putString(BUTTON_KEY, str).apply();
    }

    public String getPressedRadioButton() {
        return mSharedPreferences.getString(BUTTON_KEY, "");
    }
}
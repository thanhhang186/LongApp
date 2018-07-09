package com.khtn.healthcare.view.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    public static final String PREF_NAME = "prefs";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context){
        return getPrefs(context).getString(USERNAME,"");
    }

    public static void putUsername(Context context, String username){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(USERNAME, username);
        editor.commit();
    }

    public static String getPassword(Context context){
        return getPrefs(context).getString(PASSWORD,"");
    }

    public static void putPassword(Context context, String password){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PASSWORD, password);
        editor.commit();
    }

}
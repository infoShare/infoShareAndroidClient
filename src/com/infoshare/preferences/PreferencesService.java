package com.infoshare.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.infoshare.R;

public class PreferencesService {
    public static final String LOGIN_USERNAME_KEY = "__USERNAME__";
    public static final String LOGIN_PASSWORD_KEY = "__PASSWORD__";
    public static final String CATEGORY_KEY = "__CATEGORY__";
    public static final String USER_KEY = "__USER__";
    public static final String USER_ID_KEY = "__USER_ID__";
    public static final String UPDATE_KEY = "__UPDATE__";
    public static final String LAST_UPDATE_KEY = "__LAST_UPDATE__";

    public static void saveToPrefs(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.commit();
    }
    
    public static void saveToPrefs(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    
    public static void saveToPrefs(Context context, String key, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key,value);
        editor.commit();
    }
    
    public static void saveToPrefs(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static String getStringFromPrefs(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
             e.printStackTrace();
             return defaultValue;
        }
    }
    
    public static Boolean getBooleanFromPrefs(Context context, String key, Boolean defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
             e.printStackTrace();
             return defaultValue;
        }
    }
    
    public static int getIntFromPrefs(Context context, String key, int defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
             e.printStackTrace();
             return defaultValue;
        }
    }
    
    public static long getLongFromPrefs(Context context, String key, long defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getLong(key, defaultValue);
        } catch (Exception e) {
             e.printStackTrace();
             return defaultValue;
        }
    }
    
    public static long getLoggedUser(Context ctx){
    	return getLongFromPrefs(ctx, USER_ID_KEY, 0);
    }
    
    public static String getCurrentCategory(Context ctx){
    	return getStringFromPrefs(ctx, CATEGORY_KEY, ctx.getResources().getString(R.string.noCategory));
    }
    
    public static String getLastUpdate(Context ctx){
    	return getStringFromPrefs(ctx, LAST_UPDATE_KEY, "");
    }
    
    public static void clearPreferences(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
    
    public static void deletePreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if(prefs.contains(key)){
        	final SharedPreferences.Editor editor = prefs.edit();
        	editor.remove(key);
    		editor.commit();
        }
    }
    
}
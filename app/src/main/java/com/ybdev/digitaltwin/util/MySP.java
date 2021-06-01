package com.ybdev.digitaltwin.util;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {

    public interface KEYS{
        String PROJECT = "PROJECT";
        String BUILDING = "BUILDING";
        String APARTMENT = "APARTMENT";
        String ROOM = "ROOM";
    }

    private static MySP instance;
    private SharedPreferences prefs;

    public static MySP getInstance(){
        return instance;
    }

    private MySP(Context context){
        prefs = context.getApplicationContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE);
    }

    public static MySP initHelper(Context context){
        if (instance == null)
            instance = new MySP(context);
        return instance;
    }

    public void putString(String key, String value){
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String def){
        return prefs.getString(key, def);
    }

}

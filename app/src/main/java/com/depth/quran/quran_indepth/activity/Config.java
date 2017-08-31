package com.depth.quran.quran_indepth.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Sadmansamee on 7/25/15.
 */
public class Config {


    final public static String LANG = "lang";
    final public static String SHOW_TRANSLATION = "showTranslation";
    final public static String FIRST_RUN = "firstRun";
    final public static String DATABASE_VERSION = "dbVersion";
    final public static boolean defaultShowTranslation = true;

    // public String lang;
    public boolean rtl;
    public boolean showTranslation;


    public void load(Context context) {
        Log.d("Config", "Load");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            Log.d("Config", "Loading Custom");

        } catch (Exception e) {
            Log.d("Config", "Exception Loading Defaults");
        }
    }


    private int getStringInt(SharedPreferences sp, String key, int defValue) {
        return Integer.parseInt(sp.getString(key, Integer.toString(defValue)));
    }

}

package com.depth.quran.quran_indepth.activity;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * Created by Sadmansamee on 7/27/15.
 */
public class App extends Application {
    public static App app;
    final public Config config = new Config();
    SharedPreferences sharedPreferences;
    private String loadedFont = "1";
    private String loadedFontSize = "1";

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        app = this;
        config.load(this);
        Log.e("app ", "onCreate");
    }
}

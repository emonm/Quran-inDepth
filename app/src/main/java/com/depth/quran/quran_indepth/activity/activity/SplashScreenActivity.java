package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.Config;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.io.IOException;


public class SplashScreenActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        mHandler.postDelayed(mPendingLauncherRunnable, 2000);
    }

    private final Runnable mPendingLauncherRunnable = new Runnable() {
        @Override
        public void run() {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreenActivity.this);
            boolean showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, false);
            if (showTranslation) {
                Intent mm = new Intent(SplashScreenActivity.this, ExplorerActivity.class);
                startActivity(mm);
            } else {
                try {
                    DataBaseHelper dataBaseHelper;
                    dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
                    dataBaseHelper.getChapteList();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(SplashScreenActivity.this, QuranChapterArabicActivity.class);
                ChapterListModel model_list = AllChapterList.getChapterList(0);
                String ida = model_list.getChapter_id().toString();
                String name = model_list.getChapter_english();
                Log.w("English Name:", name);
                String verses = model_list.getVerses().toString();
                String rukus = model_list.getRuku_Count();
                String relevation = model_list.getRevelation_Number().toString();
                String parah = model_list.getParas();
                String sajda = model_list.getSajdaVerses();
                String chapterAraic = model_list.getChapter_arabic();
                intent.putExtra("id", ida);
                intent.putExtra("title", name);
                intent.putExtra("aravic", chapterAraic);
                intent.putExtra("verses", verses);
                intent.putExtra("rukus", rukus);
                intent.putExtra("relevation", relevation);
                intent.putExtra("parah", parah);
                intent.putExtra("sajda", sajda);

                startActivity(intent);
            }


            SplashScreenActivity.this.finish();

        }
    };


}

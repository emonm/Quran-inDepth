package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.Config;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.io.IOException;
import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv;
    BaseAdpterList baseAdpterList;
    DataBaseHelper dataBaseHelper;
    static boolean showTranslation;
    Switch aSwitch;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        aSwitch = (Switch) findViewById(R.id.TranslationSwitch);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, false);

        if (showTranslation) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }

        lv = (ListView) findViewById(R.id.left_drawer);


        String names[] = {"Analyze Quran", "Explorer", "Quran Chapters", "Quran Dictionary", "Bookmarks", "Start Tour", "About", "Settings"};
        int images[] = {R.drawable.analyze_quran, R.drawable.ic_library_books, R.drawable.ic_list, R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp, R.drawable.ic_direction,
                R.drawable.ic_error_outline_white_36dp, R.drawable.ic_settings_white_36dp};

        baseAdpterList = new BaseAdpterList(this, images, names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    boolean showTranslation;
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                    showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, false);
                    if (showTranslation) {
                        Intent intent = new Intent(SettingActivity.this, QuranDetailsActivity.class);
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
                    } else {
                        Intent intent = new Intent(SettingActivity.this, QuranChapterArabicActivity.class);
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
                }
                if (i == 1) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                    showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, false);
                    if (showTranslation) {
                        Intent intent = new Intent(SettingActivity.this, QuranDetailsActivity.class);
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
                    } else {
                        Intent intent = new Intent(SettingActivity.this, QuranChapterArabicActivity.class);
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
                }
                if (i == 2) {
                    Intent intent = new Intent(SettingActivity.this, QuranChapterActivity.class);
                    startActivity(intent);
                }
                if (i == 3) {
                    Intent intent = new Intent(SettingActivity.this, QuranDictionaryActivity.class);
                    startActivity(intent);
                }
                if (i == 4) {
                    Intent intent = new Intent(SettingActivity.this, BookmarksActivity.class);
                    startActivity(intent);
                }
                if (i == 5) {
                    Intent intent = new Intent(SettingActivity.this, StartTourActivity.class);
                    startActivity(intent);
                }
                if (i == 6) {
                    Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
                if (i == 7) {
                    Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ifCleaked) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                if (ifCleaked) {
                    sharedPreferences.edit().putBoolean(Config.SHOW_TRANSLATION, true).apply();
                } else {
                    sharedPreferences.edit().putBoolean(Config.SHOW_TRANSLATION, false).apply();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
            showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, false);

            finishAffinity();
            if (showTranslation) {
                Intent intent = new Intent(SettingActivity.this, QuranDetailsActivity.class);
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
            } else {
                Intent intent = new Intent(SettingActivity.this, QuranChapterArabicActivity.class);
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

//            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

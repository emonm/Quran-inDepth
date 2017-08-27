package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;

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

        aSwitch=(Switch)findViewById(R.id.TranslationSwitch);
        lv=(ListView)findViewById(R.id.left_drawer);
        mContext=this;

        String names[]={"Quran-in Depth","Explorer","Quran Chapters","Quran Dictionary","Bookmarks","About","Settings"};
        int images[]={R.drawable.analyze_quran,R.drawable.ic_library_books,R.drawable.ic_list,R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp,R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(SettingActivity.this,AnalyzeQuranActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==1){
                    Intent intent=new Intent(SettingActivity.this,ExplorerActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==2){
                    Intent intent=new Intent(SettingActivity.this,QuranChapterActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==3){
                    Intent intent=new Intent(SettingActivity.this,QuranDictionaryActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==4){
                    Intent intent=new Intent(SettingActivity.this,BookmarksActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==5){
                    Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==6){
                    Intent intent=new Intent(SettingActivity.this,SettingActivity.class);
                    startActivity(intent);
                    finish();
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


                if(ifCleaked){

                }
                else {

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
            super.onBackPressed();
        }
        this.startActivity(new Intent(SettingActivity.this,ExplorerActivity.class));
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

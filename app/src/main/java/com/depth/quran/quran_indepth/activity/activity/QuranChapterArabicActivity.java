package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.adapter.ChapterArabicDetailsListAdapter;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;

import java.io.IOException;

public class QuranChapterArabicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    DataBaseHelper dataBaseHelper;

    TextView txt_chapter_name, txt_verses, txt_rukus, txt_relevation,
            txt_parah, txt_serail_num, txt_arabic, txt_sajda;
    String name;
    String quran_id;
    String verses, relevation, rukus, parah, arabic_name, sajda_count;

    ChapterArabicDetailsListAdapter chapterArabicDetailsListAdapter;
    TextView txt_chapter_title;

    ListView lv;
    BaseAdpterList baseAdpterList;
    ImageView facebookLink,youtubeLink,googlePluseLink,websiteLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_chapter_arabic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv=(ListView)findViewById(R.id.left_drawer);
        facebookLink=(ImageView)findViewById(R.id.facebook);
        youtubeLink=(ImageView)findViewById(R.id.youtube);
        googlePluseLink=(ImageView)findViewById(R.id.goolge_plus);
        websiteLink=(ImageView)findViewById(R.id.website);
        mContext = this;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        String names[]={"Analyze Quran","Explorer","Quran Chapters","Quran Dictionary","Bookmarks","Start Tour","About","Settings"};
        int images[]={R.drawable.analyze_quran,R.drawable.ic_library_books,R.drawable.ic_list,R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp,R.drawable.ic_direction,
                R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,ExplorerActivity.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,QuranChapterActivity.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,QuranDictionaryActivity.class);
                    startActivity(intent);
                }
                if(i==4){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,BookmarksActivity.class);
                    startActivity(intent);
                }
                if(i==5){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,StartTourActivity.class);
                    startActivity(intent);
                }
                if(i==6){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,AboutActivity.class);
                    startActivity(intent);
                }
                if(i==7){
                    Intent intent=new Intent(QuranChapterArabicActivity.this,SettingActivity.class);
                    startActivity(intent);
                }
            }
        });
        facebookLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://www.facebook.com/kanateleyoun";
                Uri uriUrl = Uri.parse(url);
                Intent facebookLink = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(facebookLink);
            }
        });
        youtubeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://www.youtube.com/user/maboussa";
                Uri uriUrl = Uri.parse(url);
                Intent facebookLink = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(facebookLink);
            }
        });
        googlePluseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://plus.google.com/+KanatEleyoun";
                Uri uriUrl = Uri.parse(url);
                Intent facebookLink = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(facebookLink);
            }
        });
        websiteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://www.eleyoun.com";
                Uri uriUrl = Uri.parse(url);
                Intent facebookLink = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(facebookLink);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        datalode();

        initUI();
    }

    private void initUI() {

        txt_serail_num = (TextView) this.findViewById(R.id.Serail_number);
        txt_chapter_title = (TextView) this.findViewById(R.id.ChapterNameEnTextView);
        txt_arabic = (TextView) this.findViewById(R.id.ChapterNameArTextView);
        txt_verses = (TextView) this.findViewById(R.id.verses_number);
        txt_rukus = (TextView) this.findViewById(R.id.rukus_number);
        txt_relevation = (TextView) this.findViewById(R.id.Revelation_number);
        txt_parah = (TextView) this.findViewById(R.id.parah_number);
        txt_sajda = (TextView) this.findViewById(R.id.sajda_number);
        txt_serail_num.setText("" + quran_id);
        txt_chapter_title.setText("" + name);
        txt_arabic.setText("" + arabic_name);
        txt_verses.setText("" + verses);
        txt_rukus.setText("" + rukus);
        txt_relevation.setText("" + relevation);
        txt_parah.setText("" + parah);
        txt_sajda.setText("" + sajda_count);
        chapterArabicDetailsListAdapter = new ChapterArabicDetailsListAdapter(mContext, R.layout.row_arabic_chpater_details, AllQuranList.getAllQuranList());
        ListView listView = (ListView) this.findViewById(R.id.list_quran);
        listView.setAdapter(chapterArabicDetailsListAdapter);
        chapterArabicDetailsListAdapter.notifyDataSetChanged();

    }


    public void datalode() {
        try {
            dataBaseHelper = new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "not", Toast.LENGTH_LONG).show();
        }
        Intent intent = getIntent();
        quran_id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("title");
        arabic_name = intent.getExtras().getString("aravic");
        verses = intent.getExtras().getString("verses");
        rukus = intent.getExtras().getString("rukus");
        relevation = intent.getExtras().getString("relevation");
        parah = intent.getExtras().getString("parah");
        sajda_count = intent.getExtras().getString("sajda");
        dataBaseHelper.chapter_arabic_details(quran_id);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

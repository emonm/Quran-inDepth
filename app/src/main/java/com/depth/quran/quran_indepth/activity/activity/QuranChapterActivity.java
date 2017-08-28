package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.adapter.ChapterListAdapter;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.io.File;
import java.io.IOException;

public class QuranChapterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener{
    ListView lv;
    BaseAdpterList baseAdpterList;
    private Context mContext;
    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private ChapterListAdapter mChapterListAdapter;
    ImageView facebookLink,youtubeLink,googlePluseLink,websiteLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_chapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext =getApplicationContext();

        lv=(ListView)findViewById(R.id.left_drawer);
        facebookLink=(ImageView)findViewById(R.id.facebook);
        youtubeLink=(ImageView)findViewById(R.id.youtube);
        googlePluseLink=(ImageView)findViewById(R.id.goolge_plus);
        websiteLink=(ImageView)findViewById(R.id.website);

        String names[]={"Quran-in Depth","Explorer","Quran Chapters","Quran Dictionary","Bookmarks","About","Settings"};
        int images[]={R.drawable.analyze_quran,R.drawable.ic_library_books,R.drawable.ic_list,R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp, R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i==0){
                            Intent intent=new Intent(QuranChapterActivity.this,AnalyzeQuranActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==1){
                            Intent intent=new Intent(QuranChapterActivity.this,ExplorerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==2){
                            Intent intent=new Intent(QuranChapterActivity.this,QuranChapterActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==3){
                            Intent intent=new Intent(QuranChapterActivity.this,QuranDictionaryActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==4){
                            Intent intent=new Intent(QuranChapterActivity.this,BookmarksActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==5){
                            Intent intent=new Intent(QuranChapterActivity.this,AboutActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(i==6){
                            Intent intent=new Intent(QuranChapterActivity.this,SettingActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
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

        DataLoad();
        initUI();

    }

    public void DataLoad() {
        try {
            dataBaseHelper = new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File data = mContext.getDatabasePath(DataBaseHelper.DB_PATH);
        if (false == data.exists()) {
            dataBaseHelper.getReadableDatabase();
        }
        dataBaseHelper.getChapteList();
    }


    private void initUI() {
        mChapterListAdapter = new ChapterListAdapter(mContext, R.layout.row_chapter, AllChapterList.getAllChapterList());
        listView = (ListView)findViewById(R.id.listviewChapter);
        listView.setAdapter(mChapterListAdapter);
        mChapterListAdapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, QuranChapterArabicActivity.class);
                ChapterListModel model_list = AllChapterList.getChapterList(position);
                String ida = model_list.getChapter_id().toString();
                String name = model_list.getChapter_english();
                Log.w("English Name:",name);
                String verses = model_list.getVerses().toString();
                String rukus = model_list.getRuku_Count();
                String relevation = model_list.getRevelation_Number().toString();
                String parah = model_list.getParas();
                String sajda = model_list.getSajdaVerses();
                String chapterAraic=model_list.getChapter_arabic();
                intent.putExtra("id", ida);
                intent.putExtra("title", name);
                intent.putExtra("aravic", chapterAraic);
                intent.putExtra("verses", verses);
                intent.putExtra("rukus", rukus);
                intent.putExtra("relevation", relevation);
                intent.putExtra("parah", parah);
                intent.putExtra("sajda", sajda);

                startActivity(intent);
                finish();
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
        this.startActivity(new Intent(QuranChapterActivity.this,ExplorerActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chapter_search_menu, menu);
        MenuItem search=menu.findItem(R.id.action_search_chapter);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        mChapterListAdapter.getFilter().filter(newText.toString());
        return true;
    }

}

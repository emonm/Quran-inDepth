package com.depth.quran.quran_indepth.activity.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.adapter.ChapterDetailsListAdapter;
import com.depth.quran.quran_indepth.activity.adapter.ChapterListAdapter;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class QuranDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        ,SearchView.OnQueryTextListener,AdapterView.OnItemSelectedListener{

    Context mContext;
    DataBaseHelper dataBaseHelper;
    TextView txt_chapter_name,txt_verses,txt_rukus,txt_relevation,
            txt_parah,txt_serail_num,txt_arabic,txt_sajda;
    String name;
    String quran_id;
    String verses,relevation,rukus,parah,arabic_name,sajda_count;
    ChapterDetailsListAdapter mChapterDetailsListAdapter;
    TextView txt_chapter_title;
    ListView lv;
    BaseAdpterList baseAdpterList;
    Spinner spinner2;
    ArrayAdapter<String> names;
    EditText editText;
    Vector<ChapterListModel> vc;
    ChapterListAdapter chapterListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_details);

        mContext = this;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv=(ListView)findViewById(R.id.left_drawer);

        String names[]={"Analyze Quran","Explorer","Quran Chapters","Quran Dictionary","Bookmarks","Start Tour","About","Settings"};
        int images[]={R.drawable.analyze_quran,R.drawable.ic_library_books,R.drawable.ic_list,R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp,R.drawable.ic_direction,
                R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i==1){
                            Intent intent=new Intent(QuranDetailsActivity.this,ExplorerActivity.class);
                            startActivity(intent);
                        }
                        if(i==2){
                            Intent intent=new Intent(QuranDetailsActivity.this,QuranChapterActivity.class);
                            startActivity(intent);
                        }
                        if(i==3){
                            Intent intent=new Intent(QuranDetailsActivity.this,QuranDictionaryActivity.class);
                            startActivity(intent);
                        }
                        if(i==4){
                            Intent intent=new Intent(QuranDetailsActivity.this,BookmarksActivity.class);
                            startActivity(intent);
                        }
                        if(i==5){
                            Intent intent=new Intent(QuranDetailsActivity.this,StartTourActivity.class);
                            startActivity(intent);
                        }
                        if(i==6){
                            Intent intent=new Intent(QuranDetailsActivity.this,AboutActivity.class);
                            startActivity(intent);
                        }
                        if(i==7){
                            Intent intent=new Intent(QuranDetailsActivity.this,SettingActivity.class);
                            startActivity(intent);
                        }
                    }
                });
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
        quranSpineer();
        initUI();
    }

    private void initUI() {

        txt_serail_num=(TextView)this.findViewById(R.id.Serail_number);
        txt_chapter_title=(TextView)this.findViewById(R.id.ChapterNameEnTextView);
        txt_arabic=(TextView)this.findViewById(R.id.ChapterNameArTextView);
        txt_verses=(TextView)this.findViewById(R.id.verses_number);
        txt_rukus=(TextView)this.findViewById(R.id.rukus_number);
        txt_relevation=(TextView)this.findViewById(R.id.Revelation_number);
        txt_parah=(TextView)this.findViewById(R.id.parah_number);
        txt_sajda=(TextView)this.findViewById(R.id.sajda_number);
        txt_serail_num.setText(""+quran_id);
        txt_chapter_title.setText(""+name);
        txt_arabic.setText(""+arabic_name);
        txt_verses.setText(""+verses);
        txt_rukus.setText(""+rukus);
        txt_relevation.setText(""+relevation);
        txt_parah.setText(""+parah);
        txt_sajda.setText(""+sajda_count);
        mChapterDetailsListAdapter=new ChapterDetailsListAdapter(mContext,R.layout.row_chpater_details, AllQuranList.getAllQuranList());
        ListView listView=(ListView)this.findViewById(R.id.list_quran_verses);
        listView.setAdapter(mChapterDetailsListAdapter);
        mChapterDetailsListAdapter.notifyDataSetChanged();

    }


    public void datalode(){
        try {
            dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
        }
        Intent intent = getIntent();
        quran_id = intent.getExtras().getString("id");
        name=intent.getExtras().getString("title");
        arabic_name=intent.getExtras().getString("aravic");
        verses = intent.getExtras().getString("verses");
        rukus=intent.getExtras().getString("rukus");
        relevation = intent.getExtras().getString("relevation");
        parah=intent.getExtras().getString("parah");
        sajda_count=intent.getExtras().getString("sajda");

        dataBaseHelper.lodetoexplorae(quran_id,name,arabic_name,verses,rukus,relevation,parah,sajda_count);
        dataBaseHelper.caper_details(quran_id);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quran_details_search, menu);
        MenuItem search=menu.findItem(R.id.action_search_quran);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search_number_quran) {
//            Toast.makeText(mContext,"dkd",Toast.LENGTH_SHORT).show();
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
//            View mView = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
//            final EditText mEmail = (EditText) mView.findViewById(R.id.editText);
//            Button cancel = (Button) mView.findViewById(R.id.cancel);
//            Button done = (Button) mView.findViewById(R.id.done);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
            View mView = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
            editText = (EditText) mView.findViewById(R.id.editText);
            Button done = (Button) mView.findViewById(R.id.done);
            Button cancel = (Button) mView.findViewById(R.id.cancel);

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                                mChapterDetailsListAdapter.getFilter().filter(editText.getText().toString());
                    }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"Cancel",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
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
        mChapterDetailsListAdapter.getFilter().filter(newText.toString());
        return true;
    }

    private void quranSpineer(){
        Spinner spinner = (Spinner)findViewById(R.id.spineer);

        ArrayList<String> mylist = new ArrayList<String>();
        //        AllLetters all=new AllLetters();
        vc= AllChapterList.getAllChapterList();

        for (int i=0;i<vc.size();i++){
            mylist.add(vc.get(i).getChapter_english());
        }
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,R.layout.spinner_item, mylist);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

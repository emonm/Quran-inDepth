package com.depth.quran.quran_indepth.activity.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.abcd;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.adapter.Wordlist;
import com.depth.quran.quran_indepth.activity.adapter.expanda_adapter;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.holder.AllLetters;
import com.depth.quran.quran_indepth.activity.holder.AllLetters_detl;
import com.depth.quran.quran_indepth.activity.holder.Allword;
import com.depth.quran.quran_indepth.activity.holder.test;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.word_model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class QuranDictionaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    ListView lv;
    BaseAdpterList baseAdpterList;
    ImageView facebookLink,youtubeLink,googlePluseLink,websiteLink;

    DataBaseHelper dataBaseHelper;
    Context mContext;
    Vector<ChapterListModel>vc;
    Vector<ChapterListModel>vc_ayat;
    private String lete_id = "1";
    ArrayList<String> ayatarbi;
    ArrayAdapter<String> spin;
    String select_ayat,calculatdata;
    TextView txt_detailfor;
    Wordlist wo;
    Vector <word_model>vv;
    ExpandableListView listView;
    abcd abcd;
    ArrayList< String >string=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_dictionay);
        mContext=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ExpandableListView)findViewById(R.id.listword);
        txt_detailfor=(TextView)this.findViewById(R.id.txt_detailfor);
        string=new ArrayList<String>();
        string.add("abc");
        listView.setGroupIndicator(null);
        datalode();
        Ise();


        lv=(ListView)findViewById(R.id.left_drawer);
        facebookLink=(ImageView)findViewById(R.id.facebook);
        youtubeLink=(ImageView)findViewById(R.id.youtube);
        googlePluseLink=(ImageView)findViewById(R.id.goolge_plus);
        websiteLink=(ImageView)findViewById(R.id.website);

        String names[]={"Quran-in Depth","Explorer","Quran Chapters","Quran Dictionary","Bookmarks","About","Settings"};
        int images[]={R.drawable.analyze_quran,R.drawable.ic_library_books,R.drawable.ic_list,R.drawable.ic_font_download,
                R.drawable.ic_bookmark_white_36dp,R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(QuranDictionaryActivity.this,AnalyzeQuranActivity.class);
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent=new Intent(QuranDictionaryActivity.this,ExplorerActivity.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent=new Intent(QuranDictionaryActivity.this,QuranChapterActivity.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent=new Intent(QuranDictionaryActivity.this,QuranDictionaryActivity.class);
                    startActivity(intent);
                }
                if(i==4){
                    Intent intent=new Intent(QuranDictionaryActivity.this,BookmarksActivity.class);
                    startActivity(intent);
                }
                if(i==5){
                    Intent intent=new Intent(QuranDictionaryActivity.this,AboutActivity.class);
                    startActivity(intent);
                }
                if(i==6){
                    Intent intent=new Intent(QuranDictionaryActivity.this,SettingActivity.class);
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
    }
    public void datalode(){
        try {
            dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
        }
        dataBaseHelper.leter();

    }

    void is(){

        Spinner spinner2 = (Spinner)findViewById(R.id.Spb1);
        ayatarbi.clear();
        for (int a=0;a<vc_ayat.size();a++){
            ayatarbi.add(vc_ayat.get(a).getChapter_leater_arbi());
        }

        spin = new ArrayAdapter<String>(mContext,R.layout.spinner_item, ayatarbi);

        spin.setDropDownViewResource(R.layout.spinner_item); // The drop down view
        spin.notifyDataSetChanged();
        spinner2.setAdapter(spin);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_ayat = vc_ayat.get(i).getChapter_leater_arbi();

                dataBaseHelper.getselectdata(select_ayat);
                vv= Allword.getAllChapterList();

                getSupportActionBar().setTitle(" ("+select_ayat+") "+vc_ayat.get(i).getChapter_leater_enhlish());
                txt_detailfor.setText("The word "+vc_ayat.get(i).getChapter_leater_enhlish()+" ("+select_ayat+") "+


                vv.size()+" times,in 1 derive forms");
//               adapetlist();

                expanda_adapter neww=new expanda_adapter(mContext,string,hasyh(vv),1);
                listView.setAdapter(neww);
                neww.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void Ise(){
        Spinner spinner = (Spinner)findViewById(R.id.Spblood_name);

        ArrayList<String> mylist = new ArrayList<String>();
        ayatarbi = new ArrayList<String>();
        //        AllLetters all=new AllLetters();
        vc= AllLetters.getleterList();

        for (int i=0;i<vc.size();i++){
            mylist.add(vc.get(i).getChapter_leater());
        }
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,R.layout.spinner_item, mylist);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);



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
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String selectedItem = vc.get(i).getChapter_leater_id();


        //check which spinner triggered the listener
        switch (adapterView.getId()) {
            //country spinne
            case R.id.Spblood_name:

                lete_id = selectedItem;
                dataBaseHelper.leterayat(lete_id);
                vc_ayat= AllLetters_detl.getleterList();
                Toast.makeText(mContext,""+ vc_ayat.size(),Toast.LENGTH_SHORT).show();

                is();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    HashMap<String, Vector<word_model>> hasyh(Vector<word_model> a){
        HashMap<String, Vector<word_model>> hashMap = new HashMap<String, Vector<word_model>>();
        a=Allword.getAllChapterList();
        hashMap.put(string.get(0),a);
        return hashMap;
    }
    HashMap<String, Vector<String>> hash(Vector <word_model> vv){
        HashMap<String, Vector<String>> hashMap = new HashMap<String, Vector<String>>();
        ArrayList<Vector<String>>ab=new ArrayList<>();
        int a=vv.size();
        Vector<String>agg;
        for (int i=0;i<a;i++){
            dataBaseHelper.test(vv.get(i).getVerseId());
            agg= test.getAllChapterList();
            ab.add(agg);
        }
        for (int i=0;i<a;i++){
            hashMap.put(vv.get(i).getWordAr(),ab.get(i));
            System.out.print(";;;;;;;;;;;;;;;; "+ab.size());
        }
        return hashMap;
    }
}

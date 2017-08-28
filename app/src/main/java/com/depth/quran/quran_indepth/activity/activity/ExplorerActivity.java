package com.depth.quran.quran_indepth.activity.activity;

import android.app.AlertDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.depth.quran.quran_indepth.activity.holder.Expolorar_holder;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ExplorerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        SearchView.OnQueryTextListener,AdapterView.OnItemSelectedListener  {
    Context mContext;
    ListView lv;
    BaseAdpterList baseAdpterList;
    ImageView facebookLink,youtubeLink,googlePluseLink,websiteLink;
    EditText editText;
    String name;
    String quran_id;
    Vector<ChapterListModel> vc;
    ChapterListAdapter chapterListAdapter;
    Spinner spinner;
    ArrayAdapter<String> spinnerArrayAdapter;
    Vector <QuranListModel> models;
    ImageView image_makki;
    TextView txtmakki_madani;

    String verses,relevation,rukus,parah,arabic_name,sajda_count;
    DataBaseHelper dataBaseHelper;
    TextView txt_chapter_name,txt_verses,txt_rukus,txt_relevation,
            txt_parah,txt_serail_num,txt_arabic,txt_sajda;

    ChapterDetailsListAdapter mChapterDetailsListAdapter;
    TextView txt_chapter_title;
    String a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=this;

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
                if(i==0){
                    Intent intent=new Intent(ExplorerActivity.this,AnalyzeQuranActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==1){
                    Intent intent=new Intent(ExplorerActivity.this,ExplorerActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==2){
                    Intent intent=new Intent(ExplorerActivity.this,QuranChapterActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==3){
                    Intent intent=new Intent(ExplorerActivity.this,QuranDictionaryActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==4){
                    Intent intent=new Intent(ExplorerActivity.this,BookmarksActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==5){
                    Intent intent=new Intent(ExplorerActivity.this,AboutActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==6){
                    Intent intent=new Intent(ExplorerActivity.this,SettingActivity.class);
                    startActivity(intent);
                    finish();
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
        image_makki=(ImageView)this.findViewById(R.id.image_makki);
        txtmakki_madani=(TextView)this.findViewById(R.id.txtmakki_madani);
    }

    public void datalode(){
        try {
            dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
        }

        dataBaseHelper.getChapteList();
         models= Expolorar_holder.getAllQuranList();
        if (models.size()>0) {
            quran_id = models.get(0).getChapter_id();
            name = models.get(0).getChapter_english();
            arabic_name = models.get(0).getChapter_arabic();
            verses = models.get(0).getVerses();
            rukus = models.get(0).getRuku_Count();
            relevation = models.get(0).getRevelation_Number();
            parah = models.get(0).getParas();
            sajda_count = models.get(0).getSajdaVerses();
            dataBaseHelper.caper_details(quran_id);
        }

        else {

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ChapterListModel model_list = AllChapterList.getChapterList(i);
        quran_id = model_list.getChapter_id().toString();
        name = model_list.getChapter_english();
//        Log.w("English Name:",name);
        verses = model_list.getVerses().toString();
        rukus = model_list.getRuku_Count();
        relevation = model_list.getRevelation_Number().toString();
        parah = model_list.getParas();
        sajda_count = model_list.getSajdaVerses();
        arabic_name = model_list.getChapter_arabic();

        dataBaseHelper.lodetoexplorae(quran_id,name,arabic_name,verses,rukus,relevation,parah,sajda_count);
        dataBaseHelper.caper_details(quran_id);

        txt_serail_num.setText(""+quran_id);
        txt_chapter_title.setText(""+name);
        txt_arabic.setText(""+arabic_name);
        txt_verses.setText(""+verses);
        txt_rukus.setText(""+rukus);
        txt_relevation.setText(""+relevation);
        txt_parah.setText(""+parah);

        if (model_list.getIsMakki().equals("0")){
            image_makki.setImageResource(R.drawable.madina_image);

            txtmakki_madani.setText("Madani");
        }else {
            image_makki.setImageResource(R.drawable.makkah_image);
            txtmakki_madani.setText("Makki");
        }

        if (sajda_count.equals("null")) {
            txt_sajda.setText("- -");
        }else {
            txt_sajda.setText("" + sajda_count);
        }
        mChapterDetailsListAdapter=new ChapterDetailsListAdapter(mContext,R.layout.row_chpater_details, AllQuranList.getAllQuranList());
        ListView listView=(ListView)this.findViewById(R.id.list_quran_verses);
        listView.setAdapter(mChapterDetailsListAdapter);
        mChapterDetailsListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (models.size()>0){
            spinner.setSelection(Integer.parseInt(models.get(0).getChapter_id())-1);
        }else {
//        models.get(0).getChapter_id()
            spinner.setSelection(0);
        }
    }

    private void quranSpineer(){
        spinner = (Spinner)findViewById(R.id.spineer);

        ArrayList<String> mylist = new ArrayList<String>();
        //        AllLetters all=new AllLetters();
        vc= AllChapterList.getAllChapterList();

        for (int i=0;i<vc.size();i++){
            mylist.add(vc.get(i).getChapter_arabic());
        }
        // Application of the Array to the Spinner
        spinnerArrayAdapter = new ArrayAdapter<String>(mContext,R.layout.spineer_lst, mylist);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(this);

    }


}

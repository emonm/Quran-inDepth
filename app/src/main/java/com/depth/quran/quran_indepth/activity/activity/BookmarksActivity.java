package com.depth.quran.quran_indepth.activity.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.adapter.BaseAdpterList;
import com.depth.quran.quran_indepth.activity.adapter.Fevoreat_ListAdapter;
import com.depth.quran.quran_indepth.activity.dbhelper.Database_foverate;
import com.depth.quran.quran_indepth.activity.holder.All_Foveratlit;
import com.depth.quran.quran_indepth.activity.model.foverat_model;

import java.util.Vector;

public class BookmarksActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv;
    BaseAdpterList baseAdpterList;
    ImageView facebookLink,youtubeLink,googlePluseLink,websiteLink;
    Context mContext;
    Database_foverate fev;
    Fevoreat_ListAdapter febadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
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
                R.drawable.ic_settings_white_36dp,R.drawable.ic_error_outline_white_36dp,R.drawable.ic_settings_white_36dp};

        baseAdpterList=new BaseAdpterList(this,images,names);
        lv.setAdapter(baseAdpterList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(BookmarksActivity.this,AnalyzeQuranActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==1){
                    Intent intent=new Intent(BookmarksActivity.this,ExplorerActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==2){
                    Intent intent=new Intent(BookmarksActivity.this,QuranChapterActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==3){
                    Intent intent=new Intent(BookmarksActivity.this,QuranDictionaryActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==4){
                    Intent intent=new Intent(BookmarksActivity.this,BookmarksActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==5){
                    Intent intent=new Intent(BookmarksActivity.this,AboutActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(i==6){
                    Intent intent=new Intent(BookmarksActivity.this,SettingActivity.class);
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


        test();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        this.startActivity(new Intent(BookmarksActivity.this,ExplorerActivity.class));
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void test(){
        ListView listView=(ListView) this.findViewById(R.id.asa);
         fev=new Database_foverate(mContext);
        fev.getallfevorate();
        final Vector<foverat_model>aa=All_Foveratlit.getallfoveratlist();
         febadp=new Fevoreat_ListAdapter(mContext,R.layout.row_fevorat,aa);
        listView.setAdapter(febadp);
        febadp.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_box(String.valueOf(aa.get(position).getId()));
                febadp.notifyDataSetChanged();
            }
        });



    }

    private void dialog_box(final String  a) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Icon to Dialog


        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
//                Toast.makeText(getApplicationContext(),""+a,Toast.LENGTH_SHORT).show();
                fev=new Database_foverate(mContext);
                fev.delete(a);
                febadp.notifyDataSetChanged();

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Showing Alert Message
        alertDialog.show();

    }

}

package com.depth.quran.quran_indepth.activity.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context mycontext;

    public static String DB_NAME = "AnalyzeQuran1";//the extension may be .sqlite or .db
    public SQLiteDatabase myDataBase;
    public static String DB_PATH = "";

    public DataBaseHelper(Context context, String nameDatabase) throws IOException {
        super(context, nameDatabase, null, 1);
        this.mycontext = context;

        DB_NAME = nameDatabase;

        DB_PATH = "/data/data/" + mycontext.getApplicationContext().getPackageName() + "/databases/";
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("adapetlist Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }


    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (dbexist) {
            //System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
            System.out.println("Database isss exist");
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public void getChapteList() {
        AllChapterList.allChapterList.removeAllElements();
        String sqlTables = " Chapters ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables, null);
        ChapterListModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new ChapterListModel();
                model.setChapter_arabic("" + cursor.getString(cursor.getColumnIndex("NameAr")));
                model.setChapter_english("" + cursor.getString(cursor.getColumnIndex("NameEn")));
                model.setRevelation_Number("" + cursor.getString(cursor.getColumnIndex("RevelationNumber")));
                model.setRuku_Count("" + cursor.getString(cursor.getColumnIndex("RukuCount")));
                model.setVerses("" + cursor.getString(cursor.getColumnIndex("VerseCount")));
                model.setParas("" + cursor.getString(cursor.getColumnIndex("ParahsFall")));
                model.setMuqattaat("" + cursor.getString(cursor.getColumnIndex("IsMuqattaat")));
                model.setMtid("" + String.valueOf(cursor.getInt(cursor.getColumnIndex("MuqattaatId"))));
                model.setCum_Verses("" + cursor.getString(cursor.getColumnIndex("CVersesCount")));
                model.setSajdaVerses("" + cursor.getString(cursor.getColumnIndex("SajdaVerses")));
                model.setChapter_id("" + cursor.getString(cursor.getColumnIndex("ChapterId")));
                AllChapterList.setChapterList(model);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        Log.w("Total Size", "are" + AllChapterList.getAllChapterList().size());
    }

    public String getChaptemn(String aa) {
        String sqlTables = " Muqattaats ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " WHERE MuqattaatId = '" + aa + "'", null);
        String a = null;
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(cursor.getColumnIndex("MuqattaatWordAr"));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return a;

    }

    public void caper_details(String capter) {
        String sqlTables = " Verses ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " where " + " ChapterId =" + capter, null);
        QuranListModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new QuranListModel();
                model.setChapSerialNumber(cursor.getString(cursor.getColumnIndex("ChapSerialNumber")));
                model.setVerseAr(cursor.getString(cursor.getColumnIndex("VerseAr")));
                model.setVerseEn(cursor.getString(cursor.getColumnIndex("VerseEn")));
                model.setChapter_id(cursor.getString(cursor.getColumnIndex("ChapterId")));
                AllQuranList.setAllQuranList(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}

package com.depth.quran.quran_indepth.activity.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.holder.AllLetters;
import com.depth.quran.quran_indepth.activity.holder.AllLetters_detl;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.holder.Allword;
import com.depth.quran.quran_indepth.activity.holder.Expolorar_holder;
import com.depth.quran.quran_indepth.activity.holder.test;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;
import com.depth.quran.quran_indepth.activity.model.word_model;

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

    //    public void getChapteList() {
//        AllChapterList.allChapterList.removeAllElements();
//        String sqlTables = " Chapters ";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables, null);
//        ChapterListModel model;
//        if (cursor.moveToFirst()) {
//            do {
//                model = new ChapterListModel();
//                model.setChapter_arabic("" + cursor.getString(cursor.getColumnIndex("NameAr")));
//                model.setChapter_english("" + cursor.getString(cursor.getColumnIndex("NameEn")));
//                model.setRevelation_Number("" + cursor.getString(cursor.getColumnIndex("RevelationNumber")));
//                model.setRuku_Count("" + cursor.getString(cursor.getColumnIndex("RukuCount")));
//                model.setVerses("" + cursor.getString(cursor.getColumnIndex("VerseCount")));
//                model.setParas("" + cursor.getString(cursor.getColumnIndex("ParahsFall")));
//                model.setMuqattaat("" + cursor.getString(cursor.getColumnIndex("IsMuqattaat")));
//                model.setMtid("" + String.valueOf(cursor.getInt(cursor.getColumnIndex("MuqattaatId"))));
//                model.setCum_Verses("" + cursor.getString(cursor.getColumnIndex("CVersesCount")));
//                model.setSajdaVerses("" + cursor.getString(cursor.getColumnIndex("SajdaVerses")));
//                model.setChapter_id("" + cursor.getString(cursor.getColumnIndex("ChapterId")));
//                AllChapterList.setChapterList(model);
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//
//        Log.w("Total Size", "are" + AllChapterList.getAllChapterList().size());
//    }
//
//    public String getChaptemn(String aa) {
//        String sqlTables = " Muqattaats ";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " WHERE MuqattaatId = '" + aa + "'", null);
//        String a = null;
//        if (cursor.moveToFirst()) {
//            do {
//                a = cursor.getString(cursor.getColumnIndex("MuqattaatWordAr"));
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return a;
//
//    }
//
//    public void caper_details(String capter) {
//        String sqlTables = " Verses ";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " where " + " ChapterId =" + capter, null);
//        QuranListModel model;
//        if (cursor.moveToFirst()) {
//            do {
//                model = new QuranListModel();
//                model.setChapSerialNumber(cursor.getString(cursor.getColumnIndex("ChapSerialNumber")));
//                model.setVerseAr(cursor.getString(cursor.getColumnIndex("VerseAr")));
//                model.setVerseEn(cursor.getString(cursor.getColumnIndex("VerseEn")));
//                model.setChapter_id(cursor.getString(cursor.getColumnIndex("ChapterId")));
//                AllQuranList.setAllQuranList(model);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//    }
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
                model.setIsMakki("" + cursor.getString(cursor.getColumnIndex("IsMakki")));
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
        AllQuranList.removeallQuranlist();
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





    public void chapter_arabic_details(String capter) {
        String sqlTables = " Verses ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " where " + " ChapterId =" + capter, null);
        QuranListModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new QuranListModel();
                model.setChapSerialNumber(cursor.getString(cursor.getColumnIndex("ChapSerialNumber")));
                model.setVerseAr(cursor.getString(cursor.getColumnIndex("VerseAr")));
                model.setChapter_id(cursor.getString(cursor.getColumnIndex("ChapterId")));
                AllQuranList.setAllQuranList(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
    public void leter() {
        AllLetters.allleterid.removeAllElements();
        String sqlTables = "AbjadLetters";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables, null);
        ChapterListModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new ChapterListModel();
                model.setChapter_leater_id("" + cursor.getString(cursor.getColumnIndex("AbjadLetterId")));
                model.setChapter_leater("" + cursor.getString(cursor.getColumnIndex("LetterCh")));

                AllLetters.setleterrList(model);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

    }


    public void leterayat(String a) {
        AllLetters_detl.allleter_det.removeAllElements();
        String sqlTables = "RootWords";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables +" WHERE AbjadLetterId = "+a, null);
        ChapterListModel model;
        if (cursor.moveToFirst()) {
            do {
                model = new ChapterListModel();
                model.setChapter_leater_arbi("" + cursor.getString(cursor.getColumnIndex("RootArabic")));
                model.setChapter_leater_enhlish("" + cursor.getString(cursor.getColumnIndex("RootEnglish")));

                AllLetters_detl.setleterrList(model);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
    public void   getselectdata(String a) {
        Allword.allChapterList.removeAllElements();
        String sqlTables = "Words";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables +" WHERE RootWordAr LIKE '%"+a+"%'", null);
        word_model model;
        if (cursor.moveToFirst()) {

            do {
                model = new word_model();
//                model.setWordId("" + cursor.getString(cursor.getColumnIndex("WordId")));
                model.setVerseId("" + cursor.getString(cursor.getColumnIndex("VerseId")));
                model.setWordNum("" + cursor.getString(cursor.getColumnIndex("WordNum")));
                model.setWordAr("" + cursor.getString(cursor.getColumnIndex("WordAr")));
                model.setMeaningEng("" + cursor.getString(cursor.getColumnIndex("MeaningEng")));
//                model.setMeaningUr("" + cursor.getString(cursor.getColumnIndex("MeaningUr")));
//                model.setRootWordAr("" + cursor.getString(cursor.getColumnIndex("RootWordAr")));
//                model.setRootWordCode("" + cursor.getString(cursor.getColumnIndex("RootWordCode")));
//                model.setRootWordId("" + cursor.getString(cursor.getColumnIndex("RootWordId")));
//                model.setLetterCount("" + cursor.getString(cursor.getColumnIndex("LetterCount")));
//                model.setSerialSum("" + cursor.getString(cursor.getColumnIndex("SerialSum")));
//                model.setWeightSum("" + cursor.getString(cursor.getColumnIndex("WeightSum")));
                model.setWordChapterId("" + cursor.getString(cursor.getColumnIndex("ChapterId")));

                Allword.setChapterList(model);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

    }

    public String word_details(String capter) {
        AllQuranList.removeallQuranlist();
        String sqlTables = " Verses ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " where " + " VerseId =" + capter, null);
        QuranListModel model;
        String a=null;
        if (cursor.moveToFirst()) {
            do {

                a=(cursor.getString(cursor.getColumnIndex("VerseAr")));
//
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return a;
    }
    public void   test(String a) {
        test.allChapterList.removeAllElements();
        String sqlTables = "Words";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables +" WHERE VerseId ="+a, null);
        word_model model;
        if (cursor.moveToFirst()) {

            do {
                model = new word_model();
//                model.setWordId("" + cursor.getString(cursor.getColumnIndex("WordId")));
                String as=("" + cursor.getString(cursor.getColumnIndex("WordAr")));
//

                test.setChapterList(as);

            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

    }

    public String  searchchap(String a) {
        test.allChapterList.removeAllElements();
        String sqlTables = "Chapters";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables +" WHERE ChapterId ="+a, null);
        String as=null;
        if (cursor.moveToFirst()) {

            do {

//                model.setWordId("" + cursor.getString(cursor.getColumnIndex("WordId")));
                as=("" + cursor.getString(cursor.getColumnIndex("NameAr")));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return as;
    }

    public String seasayatno(String capter) {
        AllQuranList.removeallQuranlist();
        String sqlTables = " Verses ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + sqlTables + " where " + " VerseId =" + capter, null);
        QuranListModel model;
        String a=null;
        if (cursor.moveToFirst()) {
            do {

                a=(cursor.getString(cursor.getColumnIndex("ChapSerialNumber")));
//
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return a;
    }

    public void lodetoexplorae(String id,String title,String aravic,String verses,
                               String rukus,String relevation,String parah,String sajda) {
        Expolorar_holder.removeallQuranlist();
        QuranListModel model;
        model=new QuranListModel();
        model.setChapter_id(id);
        model.setChapter_english(title);
        model.setChapter_arabic(aravic);
        model.setCum_Verses(verses);
        model.setRuku_Count(rukus);
        model.setRevelation_Number(relevation);
        model.setParas(parah);
        model.setSajdaVerses(sajda);

        Expolorar_holder.setAllQuranList(model);

    }


}

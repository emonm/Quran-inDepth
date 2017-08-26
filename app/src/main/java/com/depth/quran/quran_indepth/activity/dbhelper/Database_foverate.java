package com.depth.quran.quran_indepth.activity.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.depth.quran.quran_indepth.activity.holder.All_Foveratlit;
import com.depth.quran.quran_indepth.activity.model.foverat_model;


/**
 * Created by DAFFODIL on 8/24/2017.
 */

public class Database_foverate extends SQLiteOpenHelper {

    Context mContext;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "forever_database";
    private static final String TABLE_CONTACTS = "forever";
    private static final String KEY_id  = "id";
    private static final String KEY_chaptername = "chaptername";
    private static final String KEY_chapter_detils = "chapterid";
    private static final String KEY_ayatno = "ayatno";
    private static final String KEY_test = "test";





    String CREATE_CONTACTS_TABLE ="CREATE TABLE " + TABLE_CONTACTS + "("+KEY_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_chaptername +" TEXT ,"
            + KEY_chapter_detils +" TEXT ,"+ KEY_ayatno +" TEXT );";


    public Database_foverate(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insartdata(foverat_model hh){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

//        cv.put(KEY_chaptername,hh.getChapter_bangla());
//        cv.put(KEY_chapter_detils,hh.getChapter_english());
        cv.put(KEY_ayatno,hh.getChapter_arabic());
        cv.put(KEY_test,hh.getAyeat());

        db.insert(TABLE_CONTACTS,null,cv);
        db.close();
    }


    public void insaall____(String a,String b,String d){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_chaptername,a);
        cv.put(KEY_chapter_detils,b);
//        cv.put(KEY_test,c);
        cv.put(KEY_ayatno,d);


        db.insert(TABLE_CONTACTS,null,cv);
        db.close();
    }


    public void getallfevorate() {
        All_Foveratlit.allfoveratlist.removeAllElements();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        foverat_model model;
        if (cursor.moveToFirst()) {
            do {
                model=new foverat_model();
//

                model.setChapter_arabic(cursor.getString(cursor.getColumnIndex(KEY_chaptername)));
                model.setArabic_details(cursor.getString(cursor.getColumnIndex(KEY_chapter_detils)));
                model.setAyeat(cursor.getString(cursor.getColumnIndex(KEY_ayatno)));
                model.setId(cursor.getString(cursor.getColumnIndex(KEY_id)));

                All_Foveratlit.setallfoveratlist(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }



//    public void delete(String chapter_id,String ayrat) {
//        String where = KEY_capter_id + " = ? AND "+KEY_test + " = ?";
//        String[] whereArgs = { String.valueOf(chapter_id),String.valueOf(ayrat) };
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, where, whereArgs);
//
//    }
//
//    public void updatnode(String chaer_id,String ayat,String value) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues newValues = new ContentValues();
//        newValues.put(KEY_node, ""+value);
//
//        String[] args = new String[]{""+chaer_id, ""+ayat};
//        db.update(""+TABLE_CONTACTS, newValues, "capter_id=? AND ayeat=?", args);
//        db.close();
//    }

    public String node(String chaer_id,String ayat) {
        SQLiteDatabase db = this.getReadableDatabase();
        String s1=null;
        Cursor c = db.rawQuery("select * from forever where capter_id= "+chaer_id+" and ayeat="+ayat,null);
        if (c.moveToFirst())
        {
            do
            {
                s1 = c.getString(c.getColumnIndex("node"));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return s1;
    }
    public void delete(String time) {
        String where = KEY_id + " = ?";
        String[] whereArgs = { String.valueOf(time) };

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, where, whereArgs);

    }

}
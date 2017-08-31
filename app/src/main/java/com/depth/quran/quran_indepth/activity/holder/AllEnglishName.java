package com.depth.quran.quran_indepth.activity.holder;

import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/18/2017.
 */

public class AllEnglishName {

    public static Vector<ChapterListModel> allleterid = new Vector<ChapterListModel>();


    public static Vector<ChapterListModel> getleterList() {
        return allleterid;
    }

    public static void setAllleterList(Vector<ChapterListModel> allChapterList) {
        AllLetters.allleterid = allChapterList;
    }


    public static ChapterListModel getleterList(int postiong) {
        return allleterid.elementAt(postiong);
    }

    public static void setleterrList(ChapterListModel _allChapterList) {
        AllLetters.allleterid.add(_allChapterList);
    }

    public static void removedAllChapterList() {
        AllLetters.allleterid.removeAllElements();
    }
}


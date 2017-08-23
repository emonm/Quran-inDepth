package com.depth.quran.quran_indepth.activity.holder;




import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.util.Vector;

/**
 * Created by shofikul on 07/15/17.
 */
public class AllChapterList {

    public static Vector<ChapterListModel> allChapterList = new Vector<ChapterListModel>();


    public static Vector<ChapterListModel> getAllChapterList() {
        return allChapterList;
    }

    public static void setAllChapterList(Vector<ChapterListModel> allChapterList) {
        AllChapterList.allChapterList = allChapterList;
    }


    public static ChapterListModel getChapterList(int postiong) {
        return allChapterList.elementAt(postiong);
    }

    public static void setChapterList(ChapterListModel _allChapterList) {
        AllChapterList.allChapterList.add(_allChapterList);
    }

    public static void removedAllChapterList() {
        AllChapterList.allChapterList.removeAllElements();
    }
}

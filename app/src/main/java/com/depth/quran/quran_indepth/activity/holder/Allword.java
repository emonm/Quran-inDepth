package com.depth.quran.quran_indepth.activity.holder;


import com.depth.quran.quran_indepth.activity.model.word_model;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/21/2017.
 */

public class Allword {
    public static Vector<word_model> allChapterList = new Vector<word_model>();


    public static Vector<word_model> getAllChapterList() {
        return allChapterList;
    }

    public static void setAllChapterList(Vector<word_model> allChapterList) {
        Allword.allChapterList = allChapterList;
    }


    public static word_model getChapterList(int postiong) {
        return allChapterList.elementAt(postiong);
    }

    public static void setChapterList(word_model _allChapterList) {
        Allword.allChapterList.add(_allChapterList);
    }

    public static void removedAllChapterList() {
        Allword.allChapterList.removeAllElements();
    }
}

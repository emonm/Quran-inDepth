package com.depth.quran.quran_indepth.activity.holder;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/22/2017.
 */

public class test {
    public static Vector<String> allChapterList = new Vector<String>();


    public static Vector<String> getAllChapterList() {
        return allChapterList;
    }

    public static void setAllChapterList(Vector<String> allChapterList) {
        test.allChapterList = allChapterList;
    }


    public static String getChapterList(int postiong) {
        return allChapterList.elementAt(postiong);
    }

    public static void setChapterList(String _allChapterList) {
        test.allChapterList.add(_allChapterList);
    }

    public static void removedAllChapterList() {
        test.allChapterList.removeAllElements();
    }
}


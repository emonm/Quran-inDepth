package com.depth.quran.quran_indepth.activity.holder;


import com.depth.quran.quran_indepth.activity.model.ChapterListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/19/2017.
 */

public class AllLetters_detl {


    public static Vector<ChapterListModel> allleter_det = new Vector<ChapterListModel>();


    public static Vector<ChapterListModel> getleterList() {
        return allleter_det;
    }

    public static void setAllleterList(Vector<ChapterListModel> allChapterList) {
        AllLetters_detl.allleter_det = allChapterList;
    }


    public static ChapterListModel getleterList(int postiong) {
        return allleter_det.elementAt(postiong);
    }

    public static void setleterrList(ChapterListModel _allChapterList) {
        AllLetters_detl.allleter_det.add(_allChapterList);
    }

    public static void removedAllChapterList() {
        AllLetters_detl.allleter_det.removeAllElements();
    }
}



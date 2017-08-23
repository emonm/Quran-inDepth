package com.depth.quran.quran_indepth.activity.holder;



import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class AllQuranList {

    public static Vector<QuranListModel> allQuranList=new Vector<QuranListModel>();
    public static Vector<QuranListModel> getAllQuranList()
    {
        return allQuranList;
    }

    public static QuranListModel getQuranlist(int position){
        return  allQuranList.elementAt(position);
    }
    public static void setAllQuranList(QuranListModel allQuranList){
        AllQuranList.allQuranList.add(allQuranList);
    }
    public static void setQuranList(QuranListModel _allQuranList) {
        AllQuranList.allQuranList.add(_allQuranList);
    }

    public  static void removeallQuranlist()
    {
        AllQuranList.allQuranList.removeAllElements();
    }
}

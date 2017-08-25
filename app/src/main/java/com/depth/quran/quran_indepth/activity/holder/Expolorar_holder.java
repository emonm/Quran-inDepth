package com.depth.quran.quran_indepth.activity.holder;

import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/25/2017.
 */

public class Expolorar_holder {
    public static Vector<QuranListModel> allQuranList=new Vector<QuranListModel>();
    public static Vector<QuranListModel> getAllQuranList()
    {
        return allQuranList;
    }

    public static QuranListModel getQuranlist(int position){
        return  allQuranList.elementAt(position);
    }
    public static void setAllQuranList(QuranListModel allQuranList){
        Expolorar_holder.allQuranList.add(allQuranList);
    }
    public static void setQuranList(QuranListModel _allQuranList) {
        Expolorar_holder.allQuranList.add(_allQuranList);
    }

    public  static void removeallQuranlist()
    {
        Expolorar_holder.allQuranList.removeAllElements();
    }
}


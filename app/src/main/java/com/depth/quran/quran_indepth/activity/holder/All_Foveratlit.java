package com.depth.quran.quran_indepth.activity.holder;

import com.depth.quran.quran_indepth.activity.model.foverat_model;

import java.util.Vector;

/**
 * Created by DAFFODIL on 8/24/2017.
 */

public class All_Foveratlit {
    public static Vector<foverat_model> allfoveratlist=new Vector<foverat_model>();
    public static Vector<foverat_model>getallfoveratlist(){
        return allfoveratlist;
    }

    public static foverat_model getAll_Foveratlit(int position){
        return  allfoveratlist.elementAt(position);
    }
    public static void setallfoveratlist(foverat_model allQuranList){
        All_Foveratlit.allfoveratlist.add(allQuranList);
    }

    public  static void removeallQuranlist(){
        All_Foveratlit.allfoveratlist.removeAllElements();
    }


}



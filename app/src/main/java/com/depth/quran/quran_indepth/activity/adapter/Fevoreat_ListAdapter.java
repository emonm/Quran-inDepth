package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.dbhelper.Database_foverate;
import com.depth.quran.quran_indepth.activity.holder.All_Foveratlit;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;
import com.depth.quran.quran_indepth.activity.model.foverat_model;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by DAFFODIL on 7/30/2017.
 */

public class Fevoreat_ListAdapter extends ArrayAdapter<foverat_model> {
    Context mContext;
    Database_foverate fov;
    DataBaseHelper dataBaseHelper;
    QuranListModel model_list;

    public Fevoreat_ListAdapter(Context context, int resource, Vector<foverat_model> quranLis) {
        super(context, resource,quranLis);
        this.mContext=context;
        datalode();
    }

    public void datalode(){
        try {
            dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Fevoreat_ListAdapter.ViewHolder holder;
        final LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_fevorat, null);


            holder = new Fevoreat_ListAdapter.ViewHolder();
            holder.txt_fev_no = (TextView) convertView.findViewById(R.id.txt_fev_no);
            holder.txt_fev_chapter = (TextView) convertView.findViewById(R.id.txt_fev_chapter);
            holder.txt_fev_ayat = (TextView) convertView.findViewById(R.id.txt_fev_ayat);


            convertView.setTag(holder);
        } else {
            holder = (Fevoreat_ListAdapter.ViewHolder) convertView.getTag();
        }
        final Vector<foverat_model> model = All_Foveratlit.getallfoveratlist();



        holder.txt_fev_no.setText(model.get(position).getAyeat());
        holder.txt_fev_chapter.setText(  dataBaseHelper.searchchap( model.get(position).getChapter_arabic()));
        holder.txt_fev_ayat.setText(model.get(position).getArabic_details());


//        View.OnClickListener leasenar=new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.Relative_foverat_del:
//                        model_list=new QuranListModel();
//                        model_list=All_Foveratlit.getAll_Foveratlit(position);
//                        fov=new Database_foverate(mContext);
//                        fov.delete(model_list.getChapter_id(),model_list.getAyeat());
//                        update(model_list.getChapter_id(),model_list.getAyeat(),"0");
//                        holder.Relative_foverat_del.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        };

        return convertView;

    }

//    public void update(String chapter_id, String ayat, String vaue){
//        try {
//            dataBaseHelper=new DataBaseHelper(mContext, "DBMSalution");
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(mContext,"not", Toast.LENGTH_LONG).show();
//        }
//
//        dataBaseHelper.updatebook(chapter_id,ayat,vaue);
//    }



    class ViewHolder {
//        TextView txt_chapter_english;
        TextView txt_fev_no;
        TextView txt_fev_chapter;
        TextView txt_fev_ayat;



    }



}

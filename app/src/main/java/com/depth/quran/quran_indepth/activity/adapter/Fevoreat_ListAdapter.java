package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
            holder.linear_fev=(LinearLayout)convertView.findViewById(R.id.linear_fev);

            convertView.setTag(holder);
        } else {
            holder = (Fevoreat_ListAdapter.ViewHolder) convertView.getTag();
        }
        final Vector<foverat_model> model = All_Foveratlit.getallfoveratlist();

        holder.txt_fev_no.setText(model.get(position).getAyeat());
        holder.txt_fev_chapter.setText(  dataBaseHelper.searchchap( model.get(position).getChapter_arabic()));
        holder.txt_fev_ayat.setText(model.get(position).getArabic_details());

//        holder.linear_fev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog_box(String.valueOf(model.get(position).getId()));
//
//            }
//        });


        return convertView;

    }

    class ViewHolder {
//        TextView txt_chapter_english;
        TextView txt_fev_no;
        TextView txt_fev_chapter;
        TextView txt_fev_ayat;
        LinearLayout linear_fev;


    }

    private void dialog_box(final String  a) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Icon to Dialog


        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
//                Toast.makeText(getApplicationContext(),""+a,Toast.LENGTH_SHORT).show();
                fov=new Database_foverate(mContext);
                fov.delete(a);


            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Showing Alert Message
        alertDialog.show();

    }


}

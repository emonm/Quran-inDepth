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
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;


import java.io.IOException;
import java.util.Vector;


/**
 * Created by Anu on 6/20/2016.
 */
public class ChapterListAdapter extends ArrayAdapter<ChapterListModel> {
    Context mContext;
    private DataBaseHelper dataBaseHelper;
    Vector<ChapterListModel> chapterListModels;

    public ChapterListAdapter(Context context, int textViewResourceId, Vector<ChapterListModel> chapterListData) {
        super(context, textViewResourceId, chapterListData);
        this.mContext = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_chapter, null);
            holder = new ViewHolder();
            holder.txt_number = (TextView) convertView.findViewById(R.id.ChapterNoTextView);
            holder.txt_english = (TextView) convertView.findViewById(R.id.ChapterNameEnTextView);
            holder.txt_aravice = (TextView) convertView.findViewById(R.id.ChapterNameArTextView);
            holder.txt_revelation = (TextView) convertView.findViewById(R.id.Revelation_number);
            holder.txt_rukus = (TextView) convertView.findViewById(R.id.Rukus_number);
            holder.txt_verses = (TextView) convertView.findViewById(R.id.VersesCountTextView);
            holder.txt_paras = (TextView) convertView.findViewById(R.id.ParahsFallTextView);
            holder.txt_muqattaat = (TextView) convertView.findViewById(R.id.MuqattaatWordsTextView);
            holder.txt_cum_verses = (TextView) convertView.findViewById(R.id.CVersesCountTextView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vector<ChapterListModel> model = AllChapterList.getAllChapterList();
        holder.txt_english.setText(model.get(position).getChapter_english());
        holder.txt_aravice.setText(model.get(position).getChapter_arabic());
        holder.txt_revelation.setText(model.get(position).getRevelation_Number());
        holder.txt_rukus.setText(model.get(position).getRuku_Count());
        holder.txt_verses.setText(model.get(position).getVerses());
        holder.txt_paras.setText(model.get(position).getParas());

        if (model.get(position).getMuqattaat().equals("1")){
            try {
                dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQuran1");
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
            }
            String  aa=dataBaseHelper.getChaptemn(model.get(position).getMtid());
            holder.txt_muqattaat.setText(aa);
        }else {
            holder.txt_muqattaat.setText("-");
        }
        holder.txt_cum_verses.setText("" + model.get(position).getCum_Verses());
        holder.txt_number.setText("" + model.get(position).getChapter_id());
        return convertView;

    }

    class ViewHolder {
        TextView txt_number;
        TextView txt_english;;
        TextView txt_aravice;
        TextView txt_revelation;
        TextView txt_rukus;
        TextView txt_verses;;
        TextView txt_paras;
        TextView txt_muqattaat;
        TextView txt_cum_verses;

    }

}

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
import com.depth.quran.quran_indepth.activity.holder.Allword;
import com.depth.quran.quran_indepth.activity.model.word_model;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by DAFFODIL on 8/21/2017.
 */

public class Wordlist extends ArrayAdapter<word_model> {
    Context mContext;
    DataBaseHelper dataBaseHelper;


    public Wordlist(Context context, int textViewResourceId, Vector<word_model> chapterListData) {
        super(context, textViewResourceId, chapterListData);
        this.mContext = context;

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_word, null);
            holder = new ViewHolder();
            holder.txt_ward_Ar=(TextView)convertView.findViewById(R.id.txt_ward_Ar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vector<word_model> model = Allword.getAllChapterList();
        try {
            dataBaseHelper=new DataBaseHelper(mContext, "AnalyzeQura");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"not",Toast.LENGTH_LONG).show();
        }
        holder.txt_ward_Ar.setText(dataBaseHelper.word_details(model.get(position).getVerseId()));


        return convertView;

    }

    class ViewHolder {
        TextView txt_ward_Ar;


    }
}

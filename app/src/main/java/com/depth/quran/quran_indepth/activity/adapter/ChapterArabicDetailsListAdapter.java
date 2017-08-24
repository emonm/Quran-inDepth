package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class ChapterArabicDetailsListAdapter extends ArrayAdapter<QuranListModel> {
    Context mContext;

    public ChapterArabicDetailsListAdapter(Context context, int resource, Vector<QuranListModel> quranLis) {
        super(context, resource, quranLis);
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChapterArabicDetailsListAdapter.ViewHolder holder;
        final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_arabic_chpater_details, null);
            holder = new ViewHolder();
            holder.ChapSerialNumber = (TextView) convertView.findViewById(R.id.chap_Serial_Number);
            holder.VerseAr = (TextView) convertView.findViewById(R.id.txt_Verse_Ar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Vector<QuranListModel> model = AllQuranList.getAllQuranList();
        holder.VerseAr.setText(model.get(position).getVerseAr());
        holder.ChapSerialNumber.setText("" + model.get(position).getChapSerialNumber());
        return convertView;
    }
    class ViewHolder {
        TextView ChapSerialNumber;
        TextView VerseAr;
    }




}

package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.dbhelper.Database_foverate;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class ChapterArabicDetailsListAdapter extends ArrayAdapter<QuranListModel> {
    Context mContext;
    Database_foverate fa;

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
            holder.bookmarh=(ImageView)convertView.findViewById(R.id.bookmarhArabic);
            holder.share=(ImageView)convertView.findViewById(R.id.ic_share);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();


        }
        final Vector<QuranListModel> model = AllQuranList.getAllQuranList();
        holder.VerseAr.setText(model.get(position).getVerseAr());
        holder.ChapSerialNumber.setText("" + model.get(position).getChapSerialNumber());

        holder.bookmarh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fa=new Database_foverate(mContext);
                fa.insaall____(model.get(position).getChapter_id(),model.get(position).getVerseAr(),
                        model.get(position).getChapter_id()+":"+model.get(position).getChapSerialNumber());
                Toast.makeText(mContext,""+model.get(position).getChapter_id()+":"+model.get(position).getChapSerialNumber()
                        +" Bookmarks Added",Toast.LENGTH_SHORT).show();
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("shaire", "intent are" + AllQuranList.getQuranlist(position).getVerseAr());
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        AllQuranList.getQuranlist(position).getChapSerialNumber()
                                +"\n"
                                + AllQuranList.getQuranlist(position).getVerseAr());
                mContext.startActivity(shareIntent);
            }
        });
        return convertView;
    }
    class ViewHolder {
        TextView ChapSerialNumber;
        TextView VerseAr;
        ImageView bookmarh,share;
    }
}

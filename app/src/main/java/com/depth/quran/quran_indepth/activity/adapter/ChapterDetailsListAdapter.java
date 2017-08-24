package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.dbhelper.Database_foverate;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class ChapterDetailsListAdapter extends ArrayAdapter<QuranListModel> {
    Context mContext;
    Database_foverate fa;
    DataBaseHelper dataBaseHelper;

    public ChapterDetailsListAdapter(Context context, int resource, Vector<QuranListModel> quranLis) {
        super(context, resource, quranLis);
        this.mContext = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChapterDetailsListAdapter.ViewHolder holder;
        final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_chpater_details, null);
            holder = new ViewHolder();
            holder.ChapSerialNumber = (TextView) convertView.findViewById(R.id.chap_Serial_Number);
            holder.VerseEn = (TextView) convertView.findViewById(R.id.txt_Verse_En);
            holder.VerseAr = (TextView) convertView.findViewById(R.id.txt_Verse_Ar);
            holder.bookmarh=(ImageView)convertView.findViewById(R.id.bookmarh);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Vector<QuranListModel> model = AllQuranList.getAllQuranList();
        holder.VerseEn.setText(model.get(position).getVerseEn());
        holder.VerseAr.setText(model.get(position).getVerseAr());
        holder.ChapSerialNumber.setText("" + model.get(position).getChapSerialNumber());

        holder.bookmarh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 fa=new Database_foverate(mContext);
                fa.insaall____(model.get(position).getChapter_id(),model.get(position).getVerseAr(),
                        model.get(position).getChapter_id()+":"+model.get(position).getChapSerialNumber());
                Toast.makeText(mContext,""+model.get(position).getChapter_id()+":"+model.get(position).getChapSerialNumber()
                        +"is save as fevoat",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView ChapSerialNumber;
        TextView VerseEn;
        TextView VerseAr;
        ImageView bookmarh;
    }




}

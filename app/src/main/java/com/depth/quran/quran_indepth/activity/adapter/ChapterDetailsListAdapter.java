package com.depth.quran.quran_indepth.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.Config;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.dbhelper.Database_foverate;
import com.depth.quran.quran_indepth.activity.holder.AllChapterList;
import com.depth.quran.quran_indepth.activity.holder.AllQuranList;
import com.depth.quran.quran_indepth.activity.model.ChapterListModel;
import com.depth.quran.quran_indepth.activity.model.QuranListModel;

import java.util.Vector;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class ChapterDetailsListAdapter extends ArrayAdapter<QuranListModel> {
    Context mContext;
    Database_foverate fa;
    DataBaseHelper dataBaseHelper;
    private Vector<QuranListModel> originalList;
    private Vector<QuranListModel> chatList;
    private CityFilter filter;
    static boolean showTranslation;

    public ChapterDetailsListAdapter(Context context, int resource, Vector<QuranListModel> quranLis) {
        super(context, resource, quranLis);
        this.mContext = context;
        this.chatList = new Vector<QuranListModel>();
        this.originalList = new Vector<QuranListModel>();
        this.chatList.addAll(quranLis);
        this.originalList.addAll(quranLis);
        this.mContext = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        showTranslation = sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, Config.defaultShowTranslation);

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
            holder.share=(ImageView)convertView.findViewById(R.id.share);

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
                        +"Bookmarks Added",Toast.LENGTH_SHORT).show();
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
                                + AllQuranList.getQuranlist(position).getVerseAr()
                        +"\n"+AllQuranList.getQuranlist(position).getVerseEn());
                mContext.startActivity(shareIntent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView ChapSerialNumber;
        TextView VerseEn;
        TextView VerseAr;
        ImageView bookmarh,share;
    }

    private class CityFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                Vector<QuranListModel> filteredItems = new Vector<QuranListModel>();

                for (int i = 0, l = originalList.size(); i < l; i++) {
                    QuranListModel country = originalList.get(i);
                    if (country.getChapSerialNumber().toString().toLowerCase().contains(constraint)) {
                        filteredItems.add(country);
                    } else if (country.getVerseEn().toString().toLowerCase().contains(constraint)) {
                        filteredItems.add(country);
                    }
                    else if (country.getVerseAr().toString().toLowerCase().contains(constraint)) {
                        filteredItems.add(country);
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            chatList = (Vector<QuranListModel>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = chatList.size(); i < l; i++)

                add(chatList.get(i));
            notifyDataSetInvalidated();
        }
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CityFilter();
        }
        return filter;
    }

}

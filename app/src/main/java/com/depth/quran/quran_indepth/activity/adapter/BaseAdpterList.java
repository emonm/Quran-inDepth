package com.depth.quran.quran_indepth.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depth.quran.quran_indepth.R;

public class BaseAdpterList extends BaseAdapter {

    String names[];
    int imd[];
    Context context;

    Context mContext;

    public BaseAdpterList(Context context, int[]imsd, String[]namesz)
    {
        names=namesz;
        imd=imsd;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);


        convertView = inflater.inflate(R.layout.custom_list, null);

        ImageView imageview=(ImageView)convertView.findViewById(R.id.imagesrc);
        TextView name=(TextView)convertView.findViewById(R.id.name);



        imageview.setImageResource(imd[position]);
        name.setText(names[position]);


        return convertView;
    }
}
package com.depth.quran.quran_indepth.activity.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.depth.quran.quran_indepth.R;
import com.depth.quran.quran_indepth.activity.dbhelper.DataBaseHelper;
import com.depth.quran.quran_indepth.activity.model.word_model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by DAFFODIL on 8/22/2017.
 */

public class abcd extends BaseExpandableListAdapter {


    DataBaseHelper dataBaseHelper;
    private Context _context;
    private Vector<word_model> header; // header titles
    // Child data in format of header title, child title
    private HashMap<String, Vector<String>> child;

    public abcd(Context context, Vector<word_model> listDataHeader,
                HashMap<String, Vector<String>> listChildData) {
        this._context = context;
        data();
        this.header = listDataHeader;
        this.child = listChildData;
    }

    void data(){
        try {
            dataBaseHelper=new DataBaseHelper(_context, "AnalyzeQuran1");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(_context,"not",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public int getGroupCount() {
        return header.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return child.get(header.get(i).getWordAr()).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.header.get(i).getWordAr();

    }

    @Override
    public Object getChild(int i, int i1) {
        return this.child.get(this.header.get(i).getWordAr()).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String headerTitle=dataBaseHelper.word_details(header.get(i).getVerseId());
//        String headerTitle = (String) getGroup(i);

        // Inflating header layout and setting text
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.header, viewGroup, false);
        }

        TextView header_text = (TextView) view.findViewById(R.id.header);

        header_text.setText(headerTitle);

        // If group is expanded then change the text into bold and change the
        // icon
        if (b) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_up, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_down, 0);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        // Getting child text
        final String childText = (String) getChild(i, i1);

        // Inflating child layout and setting textview
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.childs, viewGroup, false);
        }

        TextView child_text = (TextView) view.findViewById(R.id.child);

        child_text.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

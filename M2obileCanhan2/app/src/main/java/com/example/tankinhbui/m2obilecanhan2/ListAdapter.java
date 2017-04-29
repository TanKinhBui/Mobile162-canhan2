package com.example.tankinhbui.m2obilecanhan2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tankinhbui on 29/04/2017.
 */

public class ListAdapter extends ArrayAdapter<DateA> {

    public ListAdapter(Context context, int resource, List<DateA> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item, null);
        }
        DateA p = getItem(position);
        Log.e("possssssssssssssss", String.valueOf(position));

            // Anh xa + Gan gia tri
            TextView tv0 = (TextView) view.findViewById(R.id.iDate);
            tv0.setText(p.getDate());
            TextView tv1 = (TextView) view.findViewById(R.id.igiai1);
            tv1.setText(p.getGiai1());
            TextView tv2 = (TextView) view.findViewById(R.id.igiai2);
            tv2.setText(String.valueOf(p.getGiai2()));
            TextView tv3 = (TextView) view.findViewById(R.id.igiai3);
            tv2.setText(String.valueOf(p.getGiai3()));
            TextView tv4 = (TextView) view.findViewById(R.id.igiai4);
            tv2.setText(String.valueOf(p.getGiai4()));
            TextView tv5 = (TextView) view.findViewById(R.id.igiai5);
            tv2.setText(String.valueOf(p.getGiai5()));
            TextView tv6 = (TextView) view.findViewById(R.id.igiai6);
            tv2.setText(String.valueOf(p.getGiai6()));
            TextView tv7 = (TextView) view.findViewById(R.id.igiai7);
            tv2.setText(String.valueOf(p.getGiai7()));
            TextView tv8 = (TextView) view.findViewById(R.id.igiai8);
            tv2.setText(String.valueOf(p.getGiai8()));
            TextView tvDB = (TextView) view.findViewById(R.id.igiaiDB);
            tv2.setText(String.valueOf(p.getDB()));

        return view;
    }

}
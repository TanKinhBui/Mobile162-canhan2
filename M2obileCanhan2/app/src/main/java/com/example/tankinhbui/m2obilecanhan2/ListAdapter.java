package com.example.tankinhbui.m2obilecanhan2;

import android.content.Context;
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


            // Anh xa + Gan gia tri
            TextView tv0 = (TextView) view.findViewById(R.id.iDate);
            tv0.setText("              Ngày sổ      " + p.getDate());
            TextView tv1 = (TextView) view.findViewById(R.id.igiai1);
            tv1.setText("Giải 1:   " +p.getGiai1());
            TextView tv2 = (TextView) view.findViewById(R.id.igiai2);
            tv2.setText("Giải 2:   " +p.getGiai2());
            TextView tv3 = (TextView) view.findViewById(R.id.igiai3);
            tv3.setText("Giải 3:   " + p.getGiai3());
            TextView tv4 = (TextView) view.findViewById(R.id.igiai4);
            tv4.setText("Giải 4:   " +p.getGiai4());
            TextView tv5 = (TextView) view.findViewById(R.id.igiai5);
            tv5.setText("Giải 5:   " +p.getGiai5());
            TextView tv6 = (TextView) view.findViewById(R.id.igiai6);
            tv6.setText("Giải 6:   " +p.getGiai6());
            TextView tv7 = (TextView) view.findViewById(R.id.igiai7);
            tv7.setText("Giải 7:   " +p.getGiai7());
            TextView tv8 = (TextView) view.findViewById(R.id.igiai8);
            tv8.setText("Giải 8:   " +p.getGiai8());
            TextView tvDB = (TextView) view.findViewById(R.id.igiaiDB);
            tvDB.setText("Giải :   " +p.getDB());

        return view;
    }

}
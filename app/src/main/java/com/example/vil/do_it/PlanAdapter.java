package com.example.vil.do_it;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vil on 2017-06-15.
 */

public class PlanAdapter extends BaseAdapter{
    ArrayList<Plan> data;
    Context c;

    public PlanAdapter(ArrayList<Plan> data, Context c){
        this.data = data;
        this.c = c;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.itemName);
        TextView num = (TextView)convertView.findViewById(R.id.itemNum);
        ImageView img = (ImageView)convertView.findViewById(R.id.itemImg);

        final Plan restData = data.get(position);

        name.setText(restData.getName());
        num.setText(restData.getTime());
        img.setImageResource(R.drawable.start);

        return convertView;
    }
}

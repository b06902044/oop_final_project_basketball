package com.example.basketballrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
    The adapter of the ListView lv in HistoryActivity
 */
public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater li;
    private ArrayList<String[]> data;

    public HistoryAdapter(Context context, ArrayList data){
        this.context = context;
        this.data = data;
        this.li = LayoutInflater.from(this.context);
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
        Object obj = data.get(position);
        return data.indexOf(obj);
    }

    private static class ViewHolder{
        TextView game;
        TextView date;
        TextView vs;
        TextView result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = li.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.game = (TextView) convertView.findViewById(R.id.tv1);
            holder.date = (TextView) convertView.findViewById(R.id.tv2);
            holder.vs = (TextView) convertView.findViewById(R.id.tv3);
            holder.result = (TextView) convertView.findViewById(R.id.tv4);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.game.setText(data.get(position)[0]);
        holder.date.setText(data.get(position)[1]);
        holder.vs.setText(data.get(position)[2]);
        holder.result.setText(data.get(position)[3]);
        if(data.get(position)[3].equals("勝")) holder.result.setBackgroundResource(R.color.colorGreen);
        else holder.result.setBackgroundResource(R.color.colorRed);
        return convertView;
    }
}

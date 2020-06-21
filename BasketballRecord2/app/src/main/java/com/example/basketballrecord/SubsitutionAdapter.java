package com.example.basketballrecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//self define adapter that control the player list of the listview

public class SubsitutionAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private ArrayList<Player> players;

    public SubsitutionAdapter(ArrayList<Player> players){
        this.players = players;
    }

    public void refresh(ArrayList<Player> players){
        this.players = players;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return players == null? 0 : players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        TextView number;
        TextView name;
        Button oncourt;
        Button delete;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(context == null)
            context = viewGroup.getContext();
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player,null);
            viewHolder = new ViewHolder();
            viewHolder.number = (TextView)view.findViewById(R.id.number);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.delete = (Button)view.findViewById(R.id.delete);
            viewHolder.oncourt = (Button) view.findViewById(R.id.oncourt);

            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder)view.getTag();

        viewHolder.number.setText(players.get(position).getNumber());
        viewHolder.name.setText(players.get(position).getName());
        viewHolder.oncourt.setText(players.get(position).getOncourt());
        viewHolder.delete.setTag(R.id.delete, position);
        viewHolder.delete.setOnClickListener(this);
        viewHolder.oncourt.setTag(R.id.oncourt, position);
        viewHolder.oncourt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete:
                int pos = (int)v.getTag(R.id.delete);
                //Toast.makeText(context, "delete" + pos, Toast.LENGTH_SHORT).show();
                players.remove(pos);
                notifyDataSetChanged();
                break;
            case R.id.oncourt:
                int p = (int)v.getTag(R.id.oncourt);
                //Toast.makeText(context, "delete" + p, Toast.LENGTH_SHORT).show();
                String temp = players.get(p).getOncourt();
                if(temp.equals("")){
                    players.get(p).setOncourt("v");
                }
                else if(temp.equals("v")){
                    players.get(p).setOncourt("");
                }
                notifyDataSetChanged();
                break;
        }
    }
}

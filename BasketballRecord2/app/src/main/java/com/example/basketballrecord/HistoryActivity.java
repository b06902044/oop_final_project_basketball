package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView lv;
    private Spinner sn;
    private ArrayList<AllStatistics> allHistoryStats = new ArrayList<>();
    private ArrayList<String[]> stringListView = new ArrayList<>();
    private ArrayList<String> compNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getData();

        snInitialize();
    }
    void getData(){
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                MySqlConnection con = new MySqlConnection();
                allHistoryStats = con.getGameData();
            }
        });
        try {
            T.start();
            T.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    void lvInitialize(String compName){
        stringListView = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv_history);
        int i = 0;
        for(AllStatistics s:allHistoryStats){
            if(compName.equals("ALL") || compName.equals(s.getType())) {
                String[] k = new String[4];
                k[0] = s.getType();
                k[1] = s.getDate();
                k[2] = s.getHome() + " vs " + s.getGuest();
                k[3] = String.valueOf(i);
                stringListView.add(k);
                Log.v("History", k[0] + " " + k[1] + " " + k[2]);
            }
            i++;
        }
        HistoryAdapter adapter = new HistoryAdapter(this, stringListView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = Integer.parseInt(stringListView.get(position)[3]);
                Intent intent = new Intent(HistoryActivity.this, EndGame.class);
                intent.putExtra("player_list", allHistoryStats.get(p).getPlayers());
                intent.putExtra("hscore", allHistoryStats.get(p).getMyScoreSec());
                intent.putExtra("gscore", allHistoryStats.get(p).getGuestScoreSec());
                intent.putExtra("homeTotal", allHistoryStats.get(p).getMyScore());
                intent.putExtra("guestTotal", allHistoryStats.get(p).getGuestScore());
                intent.putExtra("home", allHistoryStats.get(p).getHome());
                intent.putExtra("guest", allHistoryStats.get(p).getGuest());
                intent.putExtra("type", allHistoryStats.get(p).getType());
                intent.putExtra("date", allHistoryStats.get(p).getDate());
                startActivity(intent);
            }
        });
    }


    void snInitialize(){
        sn = (Spinner) findViewById(R.id.spinner_history);
        compNames.add("ALL");
        for(String str:UserInfo.compNames){
            compNames.add(str);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, compNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        sn.setAdapter(arrayAdapter);

        sn.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getSelectedItem().toString();
                lvInitialize(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
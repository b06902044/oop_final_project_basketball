package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class EndGame extends AppCompatActivity {

    private ArrayList<Player> arrayList;
    private GameStatsDB gameStatsDB;

    private String DB_NAME = "GAME.db";
    private String TableName = "temp";
    private int DB_VERSION = 1;
    private ListView stats, players;
    private int homeTotal, guestTotal;
    private ArrayList<Integer> hscore, gscore;
    private String home, guest, type, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        gameStatsDB = new GameStatsDB(this, DB_NAME, null, DB_VERSION, TableName);
        init();
        showScore();
        showPlayers();
        showStats();
    }

    private void showScore() {
        TextView[] textViews = new TextView[3];
        textViews[0] = findViewById(R.id.homescore);
        textViews[1] = findViewById(R.id.score);
        textViews[2] = findViewById(R.id.guestscore);
        textViews[0].setText(home);
        textViews[1].setText("" + homeTotal + " : " + guestTotal);
        textViews[2].setText(guest);
        TextView[] h = new TextView[7];
        h[0] = findViewById(R.id.h);
        h[1] = findViewById(R.id.h1);
        h[2] = findViewById(R.id.h2);
        h[3] = findViewById(R.id.h3);
        h[4] = findViewById(R.id.h4);
        h[5] = findViewById(R.id.hOT1);
        h[6] = findViewById(R.id.hOT2);
        TextView[] g = new TextView[7];
        g[0] = findViewById(R.id.g);
        g[1] = findViewById(R.id.g1);
        g[2] = findViewById(R.id.g2);
        g[3] = findViewById(R.id.g3);
        g[4] = findViewById(R.id.g4);
        g[5] = findViewById(R.id.gOT1);
        g[6] = findViewById(R.id.gOT2);
        h[0].setText(home);
        g[0].setText(guest);
        for(int i = 0; i < hscore.size(); i++){
            h[i + 1].setText("" + hscore.get(i));
            g[i + 1].setText("" + gscore.get(i));
        }
    }

    private void init() {
        Intent intent = getIntent();
        arrayList = (ArrayList<Player>)intent.getSerializableExtra("player_list");
        homeTotal = intent.getIntExtra("homeTotal", 0);
        guestTotal = intent.getIntExtra("guestTotal", 0);
        hscore = intent.getIntegerArrayListExtra("hscore");
        gscore = intent.getIntegerArrayListExtra("gscore");
        home = intent.getStringExtra("home");
        guest = intent.getStringExtra("guest");
        type = intent.getStringExtra("type");
        date = intent.getStringExtra("date");
        for (int i = 0; i < arrayList.size(); i++){
            Log.v("john", "" + arrayList.get(i).print());
        }
    }

    private void showPlayers() {
        ArrayList<HashMap<String , String>> data = new ArrayList<>();
        for(int i = 0; i < arrayList.size(); i++){
            HashMap<String, String> one = new HashMap<>();
            one.put("Number", arrayList.get(i).getNumber() + " " + arrayList.get(i).getName());
            data.add(one);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.player_list,
                new String[]{"Number"}, new int[]{R.id.stat_number});
        players = findViewById(R.id.players);
        players.setAdapter(adapter);
    }

    private void showStats() {
        ArrayList<HashMap<String , String>> data = new ArrayList<>();
        String[] from = new String[]{"PTS", "FGM", "FGA", "FG%", "3PM", "3PA", "3P%", "FTM", "FTA",
                "FT%", "OFF", "DEF", "REB", "AST", "STL", "BLK", "TO", "PF"};
        int[] to = new int[]{R.id.PTS, R.id.FGM, R.id.FGA, R.id.FG, R.id.threePM, R.id.threePA, R.id.threeP,
        R.id.FTM, R.id.FTA, R.id.FT, R.id.OFF, R.id.DEF, R.id.REB, R.id.AST, R.id.STL, R.id.BLK, R.id.TO, R.id.PF};

        for(int i = 0; i < arrayList.size(); i++){
            HashMap<String, String> one = new HashMap<>();
            one.put("PTS", "" + arrayList.get(i).getvalue("PTS"));
            one.put("FGM", "" + arrayList.get(i).getvalue("FGM"));
            one.put("FGA", "" + arrayList.get(i).getvalue("FGA"));
            one.put("FG%", arrayList.get(i).getPercentage("FG%"));
            one.put("3PM", "" + arrayList.get(i).getvalue("3PM"));
            one.put("3PA", "" + arrayList.get(i).getvalue("3PA"));
            one.put("3P%", arrayList.get(i).getPercentage("3P%"));
            one.put("FTM", "" + arrayList.get(i).getvalue("FTM"));
            one.put("FTA", "" + arrayList.get(i).getvalue("FTA"));
            one.put("FT%", arrayList.get(i).getPercentage("FT%"));
            one.put("OFF", "" + arrayList.get(i).getvalue("OFF"));
            one.put("DEF", "" + arrayList.get(i).getvalue("DEF"));
            one.put("REB", "" + arrayList.get(i).getvalue("REB"));
            one.put("AST", "" + arrayList.get(i).getvalue("AST"));
            one.put("STL", "" + arrayList.get(i).getvalue("STL"));
            one.put("BLK", "" + arrayList.get(i).getvalue("BLK"));
            one.put("TO", "" + arrayList.get(i).getvalue("TO"));
            one.put("PF", "" + arrayList.get(i).getvalue("PF"));
            data.add(one);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.stats_list, from, to);
        stats = findViewById(R.id.stats);
        stats.setAdapter(adapter);
    }


}

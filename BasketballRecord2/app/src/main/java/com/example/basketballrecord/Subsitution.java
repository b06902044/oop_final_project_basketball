package com.example.basketballrecord;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//this activity is using Listview to show the operation of substitution

public class Subsitution extends AppCompatActivity {

    private ListView listView;
    private SubsitutionAdapter adapter;
    //LinkedList<HashMap<String, String>> data;
    private ArrayList<Player> players;
    private String home, guest, type, date;
    private int points, guest_points, period;
    private ArrayList<Integer> hscore;
    private ArrayList<Integer> gscore;
    //HashMap<String, Player> players;
    //String[] from = new String[]{"number", "name"};
    //int[] to = new int[]{R.id.number, R.id.name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsitution);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();
        getData();
        listView = findViewById(R.id.list_player);
        //players = new HashMap<>();
        init_data();
        init_player();
    }

    private void init_data() {
        hscore = new ArrayList<>();
        gscore = new ArrayList<>();
        points = 0;
        guest_points = 0;
        period = 1;
    }

    private void getData() {
        Intent intent = getIntent();
        home = intent.getStringExtra("home");
        guest = intent.getStringExtra("guest");
        type = intent.getStringExtra("type");
        date = intent.getStringExtra("date");
        //Log.v("john", home + guest + type + date);
    }

    private void init_player() {
        players = new ArrayList<>();
        adapter = new SubsitutionAdapter(players);
        listView.setAdapter(adapter);
    }


    //when the add player button is clicked , show a dialogue that can input number
    public void add_player_number(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新增球員號碼");
        final EditText number = new EditText(this);
        builder.setView(number);

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Appendable value = number.getText();
                add_player_name(String.valueOf(value));
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    //show a dialogue that can input name
    private void add_player_name(final String number) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新增球員姓名");
        final EditText name = new EditText(this);
        builder.setView(name);

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Appendable value = name.getText();
                Player player;
                if(players.size() < 5){
                    player = new Player(number, String.valueOf(value), "v");
                }
                else{
                    player = new Player(number, String.valueOf(value), "");
                }
                players.add(player);
                adapter.refresh(players);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    //put the information to intent and start the Game activity
    public void confirmSub(View view){
        Intent intent = new Intent(this, Game.class);
        int i = 0;
        intent.putExtra("player_list", players);
        intent.putExtra("home", home);
        intent.putExtra("guest", guest);
        intent.putExtra("type", type);
        intent.putExtra("date", date);
        intent.putExtra("points", points);
        intent.putExtra("guest_points", guest_points);
        intent.putExtra("period", period);
        intent.putExtra("hscore", hscore);
        intent.putExtra("gscore", gscore);
        startActivityForResult(intent, 100);
    }


    //get the information back
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 300){
            players = (ArrayList<Player>)data.getSerializableExtra("player_list");
            points = data.getIntExtra("points", 0);
            guest_points = data.getIntExtra("guest_points", 0);
            period = data.getIntExtra("period", 1);
            hscore = data.getIntegerArrayListExtra("hscore");
            gscore = data.getIntegerArrayListExtra("gscore");
            for(Player p : players){
                Log.v("john", p.getNumber() + " " + p.getName() + " " + p.print());
            }
            adapter.refresh(players);
        }
    }
}

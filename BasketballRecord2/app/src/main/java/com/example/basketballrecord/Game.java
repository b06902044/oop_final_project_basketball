package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private ArrayList<Player> arrayList;
    private Button[] textView = new Button[5];
    private int player;
    private String action;
    private int[] index;
    private String home, guest, type, date;
    private TextView homeTeam, guestTeam;
    private Button homeScore, guestScore, prd;
    private int points, guest_points, period;
    private ArrayList<Integer> hscore;
    private ArrayList<Integer> gscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//螢幕保持橫向
        getData();
        init_player();
        init_team();
    }

    private void init_team() {
        homeScore = findViewById(R.id.homeScore);
        homeScore.setText("" + points);
        guestScore = findViewById(R.id.guest_points);
        guestScore.setText("" + guest_points);
        prd = findViewById(R.id.period);
        prd.setText("" + period);
    }

    private void getData() {
        homeTeam = findViewById(R.id.hometeam);
        guestTeam = findViewById(R.id.guestteam);
        Intent intent = getIntent();
        home = intent.getStringExtra("home");
        guest = intent.getStringExtra("guest");
        type = intent.getStringExtra("type");
        date = intent.getStringExtra("date");
        points = intent.getIntExtra("points", 0);
        guest_points = intent.getIntExtra("guest_points", 0);
        period = intent.getIntExtra("period", 1);
        hscore = intent.getIntegerArrayListExtra("hscore");
        gscore = intent.getIntegerArrayListExtra("gscore");
        Log.v("john", home + guest + type + date);
        homeTeam.setText(home);
        guestTeam.setText(guest);
    }

    private void init_player() {
        index = new int[5];
        textView[0] = (Button) findViewById(R.id.player1);
        textView[1] = (Button) findViewById(R.id.player2);
        textView[2] = (Button) findViewById(R.id.player3);
        textView[3] = (Button) findViewById(R.id.player4);
        textView[4] = (Button) findViewById(R.id.player5);
        Intent intent = getIntent();
        arrayList = (ArrayList<Player>)intent.getSerializableExtra("player_list");
        int cnt = 0;
        for(int i = 0; i < arrayList.size(); i++){
            //Log.v("john", "i = " + i + " cnt = " + cnt);
            if(cnt == 5){
                break;
            }
            //Log.v("john", "" + arrayList.get(i).getNumber() + " " + arrayList.get(i).getName() + " " + arrayList.get(i).getOncourt());
            if(arrayList.get(i).getOncourt().equals("v")) {
                String str = arrayList.get(i).getNumber() + " " + arrayList.get(i).getName();
                textView[cnt].setText(str);
                index[cnt] = i;
                cnt++;
            }
        }
        player = -1;
    }



    public void add_stat(View view) {
        switch (view.getId()){
            case R.id.player1:
                player = index[0];
                break;
            case R.id.player2:
                player = index[1];
                break;
            case R.id.player3:
                player = index[2];
                break;
            case R.id.player4:
                player = index[3];
                break;
            case R.id.player5:
                player = index[4];
                break;
            default:
                player = -1;
                break;
        }
        //Toast.makeText(this, "" + player + "make shot", Toast.LENGTH_SHORT).show();
    }

    public void add_action(View view) {
        if(player == -1){
            return;
        }
        int p = 0;
        switch (view.getId()){
            case R.id.twoMade:
                action = "twoMade";
                p = 2;
                break;
            case R.id.twoMissed:
                action = "twoMissed";
                break;
            case R.id.threeMade:
                action = "threeMade";
                p = 3;
                break;
            case R.id.threeMissed:
                action = "threeMissed";
                break;
            case R.id.freeThrowMade:
                action = "freeThrowMade";
                p = 1;
                break;
            case R.id.freeThrowMissed:
                action = "freeThrowMissed";
                break;
            case R.id.offenseRebound:
                action = "offenseRebound";
                break;
            case R.id.defenseRebound:
                action = "defenseRebound";
                break;
            case R.id.assist:
                action = "assist";
                break;
            case R.id.steal:
                action = "steal";
                break;
            case R.id.block:
                action = "block";
                break;
            case R.id.turnover:
                action = "turnover";
                break;
            case R.id.offenseFoul:
                action = "offenseFoul";
                break;
            case R.id.defenseFoul:
                action = "defenseFoul";
                break;
            default:
                action = "";
                Log.v("john", "fuckyou");
        }
        //Log.v("john", "player = " + player + " " + action + " : " + arrayList.get(player).getvalue(action));
        add_points(0, p);
        arrayList.get(player).add(action);
        //Log.v("john", "number = " + arrayList.get(player).getNumber() + " " + action + " : " + arrayList.get(player).getvalue(action));
        Log.v("john", "number = " + arrayList.get(player).getNumber() + arrayList.get(player).print());
        player = -1;
        action = "";
    }

    public void substitution(View view) {
        Intent intent = new Intent();
        intent.putExtra("player_list", arrayList);
        intent.putExtra("points", points);
        intent.putExtra("guest_points", guest_points);
        intent.putExtra("period", period);
        intent.putExtra("hscore", hscore);
        intent.putExtra("gscore", gscore);
        setResult(300, intent);
        finish();
    }

    public void endGame(View view) {
        for(int i = 0; i < arrayList.size(); i++){
            Log.v("john", " i  = " + i);
            arrayList.get(i).finalCal();
        }
        Intent intent = new Intent(this, EndGame.class);
        intent.putExtra("player_list", arrayList);
        intent.putExtra("hscore", hscore);
        intent.putExtra("gscore", gscore);
        intent.putExtra("homeTotal", points);
        intent.putExtra("guestTotal", guest_points);
        intent.putExtra("home", home);
        intent.putExtra("guest", guest);
        intent.putExtra("type", type);
        intent.putExtra("date", date);
        next();

        startActivity(intent);
    }

    public void add_home_point(View view) {
        add_points(0, 1);
    }

    public void subtrac_home_point(View view) {
        add_points(0, -1);
    }

    private void add_points(int id, int point) {
        if(id == 0){
            points += point;
            if(points < 0){
                points = 0;
            }
            homeScore.setText("" + points);
        }
        else if(id == 1){
            guest_points += point;
            if(guest_points < 0){
                guest_points = 0;
            }
            guestScore.setText("" + guest_points);
        }
    }

    public void add_guest_point(View view) {
        add_points(1, 1);
    }

    public void subtrac_guest_point(View view) {
        add_points(1, -1);
    }

    public void nextPeriod(View view) {
        next();
    }

    public void next(){
        Log.v("john", "period = " + period);
        if(period == 1){
            hscore.add(points);
            gscore.add(guest_points);
        }
        else if(period > 1){
            hscore.add(points - hscore.get(period - 2));
            gscore.add(guest_points - gscore.get(period - 2));
        }
        for(int i = 0; i < hscore.size(); i++){
            Log.v("john", "period = " + (period - 1) + hscore.get(i));
        }
        period++;
        prd.setText("" + period);
    }
}

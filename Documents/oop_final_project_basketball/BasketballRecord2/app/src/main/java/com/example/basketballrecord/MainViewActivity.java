package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainViewActivity extends AppCompatActivity {
    private Button btnNewCompetition, btnNewGame, btnSearch, btnLogout;
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        btnNewCompetition = (Button) findViewById(R.id.new_competition);
        btnNewGame = (Button) findViewById(R.id.new_game);
        btnSearch = (Button) findViewById(R.id.game_search);
        btnLogout = (Button) findViewById(R.id.logout);
        tvName = (TextView) findViewById(R.id.show_text);
        if(UserInfo.userID != null){
            tvName.setText("歡迎回來 " + UserInfo.userID);
        }
        setListener();
    }
    private void setListener(){
        Onclick onclick = new Onclick();
        btnNewCompetition.setOnClickListener(onclick);
        btnNewGame.setOnClickListener(onclick);
        btnSearch.setOnClickListener(onclick);
        btnLogout.setOnClickListener(onclick);
    }
    private class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.new_competition:
                    intent = new Intent(MainViewActivity.this, CompetitionActivity.class);
                    break;
                case R.id.new_game:
                    intent = new Intent(MainViewActivity.this, CreateGame.class);
                    break;
                case R.id.game_search:
                    intent = new Intent(MainViewActivity.this, HistoryActivity.class);
                    break;
                case R.id.logout:
                    UserInfo.userID = null;
                    intent = new Intent(MainViewActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
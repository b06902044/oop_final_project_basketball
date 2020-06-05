package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateGame extends AppCompatActivity {

    private EditText Home, guest, type, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
    }

    public void confirm(View view) {
        Home = findViewById(R.id.Home);
        guest = findViewById(R.id.guest);
        type = findViewById(R.id.type);
        date = findViewById(R.id.date);
        Intent sub = new Intent(this, Subsitution.class);
        sub.putExtra("home", String.valueOf(Home.getText()));
        sub.putExtra("guest", String.valueOf(guest.getText()));
        sub.putExtra("type", String.valueOf(type.getText()));
        sub.putExtra("date", String.valueOf(date.getText()));
        startActivity(sub);
    }
}

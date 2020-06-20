package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class CreateGame extends AppCompatActivity {

    private EditText Home, guest, type, date;
    private Spinner Spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        Spn = (Spinner) findViewById(R.id.spinner_comp);
        setAdapter();

    }

    public void confirm(View view) {
        Home = findViewById(R.id.Home);
        guest = findViewById(R.id.guest);
        //type = findViewById(R.id.type);
        String compText = Spn.getSelectedItem().toString();
        date = findViewById(R.id.date);
        Intent sub = new Intent(this, Subsitution.class);
        sub.putExtra("home", String.valueOf(Home.getText()));
        sub.putExtra("guest", String.valueOf(guest.getText()));
        sub.putExtra("type", compText);
        sub.putExtra("date", String.valueOf(date.getText()));
        startActivity(sub);
    }
    private void setAdapter(){

        Log.v("comp size", ""+UserInfo.compNames.size());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, UserInfo.compNames);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        Spn.setAdapter(adapter);
    }

}

package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnRegister, mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnRegister = (Button) findViewById(R.id.register);
        mBtnLogin = (Button) findViewById(R.id.login);
        setListener();
    }
    private void setListener(){
        OnClick onClick = new OnClick();
        mBtnRegister.setOnClickListener(onClick);
        mBtnLogin.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.register:
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                    break;
                case R.id.login:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}

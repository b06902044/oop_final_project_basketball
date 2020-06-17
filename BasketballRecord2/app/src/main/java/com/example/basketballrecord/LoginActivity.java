package com.example.basketballrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.login_username);
        etPassword = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final boolean[] check = new boolean[1];
                Thread T = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MySqlConnection con = new MySqlConnection();
                        if(con.checkRepeat(username, password)){
                            check[0] = true;
                        } else check[0] = false;
                    }
                });
                T.start();
                try {
                    T.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(check[0]){
                    Intent back = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(back);
                }
                else{
                    Toast.makeText(LoginActivity.this, "使用者或密碼錯誤", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

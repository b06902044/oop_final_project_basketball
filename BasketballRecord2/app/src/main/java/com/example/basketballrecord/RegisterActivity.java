package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    TextView tvWarning, tvWarning_pwd;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = (EditText)findViewById(R.id.reg_username);
        etPassword = (EditText)findViewById((R.id.reg_password));
        tvWarning = (TextView)findViewById(R.id.tv_warning);
        tvWarning_pwd = (TextView)findViewById((R.id.tv_warning2));

        btnRegister = (Button)findViewById(R.id.reg_btn);
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String word = s.toString();
                if(s.length() < 4){
                    tvWarning.setText("帳號請輸入大於四個字");
                }
                else tvWarning.setText("");
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String word = s.toString();
                if(s.length() < 8){
                    tvWarning_pwd.setText("密碼請輸入大於八個字");
                }
                else tvWarning_pwd.setText("");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MySqlConnection con = new MySqlConnection();
                        if(con.insertData(username, password)){
                            Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "使用者名稱重複", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
            }
        });
    }

}

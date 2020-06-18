package com.example.basketballrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompetitionActivity extends AppCompatActivity {
    EditText etName, etYear;
    Button btnBack, btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        etName = (EditText) findViewById(R.id.comp_name);
        etYear = (EditText) findViewById(R.id.comp_year);
        btnBack = (Button) findViewById(R.id.comp_back);
        btnConfirm = (Button) findViewById(R.id.comp_confirm);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompetitionActivity.this, MainViewActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                int year;
                final boolean[] check = new boolean[1];
                try{
                    name = etName.getText().toString();
                    if(name.length() == 0) throw new Exception();
                    year = Integer.parseInt(etYear.getText().toString());
                }
                catch (NumberFormatException e){
                    Toast.makeText(CompetitionActivity.this, "年份輸入錯誤", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(CompetitionActivity.this, "盃賽名稱不得為空", Toast.LENGTH_SHORT).show();
                }
                Thread T = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = etName.getText().toString();
                        int year = Integer.parseInt(etYear.getText().toString());
                        MySqlConnection con = new MySqlConnection();
                        if(con.insertCompetitionData(UserInfo.userID, name, year)){
                            check[0] = true;
                        }
                        else check[0] = false;
                    }
                });
                try{
                    T.start();
                    T.join();
                }
                catch(InterruptedException e){
                    Toast.makeText(CompetitionActivity.this, "請稍等", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                if(check[0]){
                    Toast.makeText(CompetitionActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CompetitionActivity.this, MainViewActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(CompetitionActivity.this, "盃賽已存在", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
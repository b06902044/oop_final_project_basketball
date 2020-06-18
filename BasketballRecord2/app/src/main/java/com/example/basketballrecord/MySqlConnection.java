package com.example.basketballrecord;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
    public boolean insertRegisterData(String name, String pwd){
        try {
            Log.v("DB", "yes?");
            Connection con = DriverManager.getConnection(DBdata.url, DBdata.dbUser, DBdata.dbPwd);
            Statement st = con.createStatement();
            Log.v("DB", "yes");
            String query = "SELECT * FROM login WHERE `username` = '" + name + "'";
            ResultSet rs = st.executeQuery(query);
            Log.v("DB", "well");
            if(rs.next() == false) {
                Log.v("DB", "穩了");
                String cmd = "INSERT INTO `login` VALUES ('" + name + "', '" + pwd + "')";
                st = con.createStatement();
                st.executeUpdate(cmd);
                st.close();
                Log.v("DB", "寫入資料完成：" + name + ", " + pwd);
                return true;
            }
            else {
                Log.v("DB", "重複");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            Log.e("DB", "爆了");
            Log.e("DB", e.toString());
            return false;
        }
    }
    public boolean insertCompetitionData(String username, String compName, int year){
        try {
            Log.v("DB", "yes?");
            Connection con = DriverManager.getConnection(DBdata.url, DBdata.dbUser, DBdata.dbPwd);
            Statement st = con.createStatement();
            Log.v("DB", "yes");
            String query = "SELECT * FROM competition WHERE `userid` = '" + username + "' AND `name` = '" + compName +"' AND `year` = " + year;
            ResultSet rs = st.executeQuery(query);
            Log.v("DB", "well");
            if(rs.next() == false) {
                Log.v("DB", "穩了");
                String cmd = "INSERT INTO `competition` VALUES ('" + username + "', '" + compName + "', '" + year + "')";
                st = con.createStatement();
                st.executeUpdate(cmd);
                st.close();
                Log.v("DB", "寫入資料完成：" + username + ", " + compName + ", " + year);
                return true;
            }
            else {
                Log.v("DB", "重複");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            Log.e("DB", "爆了");
            Log.e("DB", e.toString());
            return false;
        }
    }
    public boolean checkRepeat(String name, String pwd){
        try{
            Log.v("DB", "yes?");
            Connection con = DriverManager.getConnection(DBdata.url, DBdata.dbUser, DBdata.dbPwd);
            Statement st = con.createStatement();
            Log.v("DB", "yes");
            String query = "SELECT * FROM login WHERE `username` = '" + name + "' AND `password` = '" + pwd + "'";
            ResultSet rs = st.executeQuery(query);
            Log.v("DB", "well");
            if(rs.next()) return true;
            return false;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e("DB", "爆了");
            Log.e("DB", e.toString());
            return false;
        }
    }
}

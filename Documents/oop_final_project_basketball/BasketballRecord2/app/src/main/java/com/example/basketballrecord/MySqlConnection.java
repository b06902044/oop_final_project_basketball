package com.example.basketballrecord;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySqlConnection {
    public ArrayList<AllStatistics> getGameData(){
        ArrayList<AllStatistics> retStats = null;
        try{
            retStats = new ArrayList<AllStatistics>();
            Log.v("DB", "yes?");
            Connection con = DriverManager.getConnection(DBdata.url, DBdata.dbUser, DBdata.dbPwd);
            Statement st = con.createStatement();
            Log.v("DB", "yes");
            String query = "SELECT * FROM `history_stats` WHERE `username` = '" + UserInfo.userID + "'";
            ResultSet rs = st.executeQuery(query);
            Log.v("DB", "yes2");
            int i = 0;
            byte[] buf;
            ObjectInputStream objectIn;
            while(rs.next()){
                buf = rs.getBytes(1);
                objectIn = null;
                if (buf != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
                Object deSerializedObject = objectIn.readObject();
                ArrayList<Player> putPlayer = (ArrayList<Player>)deSerializedObject;
                buf = rs.getBytes(2);
                objectIn = null;
                if (buf != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
                deSerializedObject = objectIn.readObject();
                ArrayList<Integer> myScoreSec = (ArrayList<Integer>)deSerializedObject;
                buf = rs.getBytes(3);
                objectIn = null;
                if (buf != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
                deSerializedObject = objectIn.readObject();
                ArrayList<Integer> guestScoreSec = (ArrayList<Integer>)deSerializedObject;
                retStats.add(new AllStatistics(putPlayer, myScoreSec, guestScoreSec, rs.getInt(4),
                rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                Log.v("DB", retStats.get(i).toString());
                Log.v("DB", "123");
                i++;
            }
            Log.v("DB", "yes3");
        }catch(SQLException e){
            e.printStackTrace();
            Log.e("DB", "怎麼了");
        }finally {
            return retStats;
        }
    }
    public void insertGameData(ArrayList<Player> playerData, ArrayList<Integer> myScoreSection, ArrayList<Integer> guestScoreSection, int myScore, int guestScore
            , String home, String guest, String type, String date, String username){
        try {
            Log.v("DB", "yes?");
            Connection con = DriverManager.getConnection(DBdata.url, DBdata.dbUser, DBdata.dbPwd);
            Statement st = con.createStatement();
            Log.v("DB", "yes");
            String create = "CREATE TABLE IF NOT EXISTS `history_stats`( " +
                    "`player` BLOB,"  +
                    "`my_score_sec` BLOB," +
                    "`guest_score_sec` BLOB," +
                    "`my_score` INT(10) NOT NULL," +
                    "`guest_score` INT(10) NOT NULL," +
                    "`home` VARCHAR(20) NOT NULL," +
                    "`guest` VARCHAR(20) NOT NULL," +
                    "`type` VARCHAR(20) NOT NULL," +
                    "`date` VARCHAR(20) NOT NULL," +
                    "`username` VARCHAR(40) NOT NULL) ENGINE=InnoDB";
            st.executeUpdate(create);
            Log.v("DB", "yes_create");
            String SQL_SERIALIZE_OBJ = "INSERT INTO history_stats(player, my_score_sec, guest_score_sec, " +
                    "my_score, guest_score, home, guest, type, date, username) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pStatement = con.prepareStatement(SQL_SERIALIZE_OBJ);
            pStatement.setObject(1, playerData);
            pStatement.setObject(2, myScoreSection);
            pStatement.setObject(3, guestScoreSection);
            pStatement.setInt(4, myScore);
            pStatement.setInt(5, guestScore);
            pStatement.setString(6, home);
            pStatement.setString(7, guest);
            pStatement.setString(8, type);
            pStatement.setString(9, date);
            pStatement.setString(10, username);
            pStatement.executeUpdate();


            Log.v("DB", "well");
            Log.v("DB", "穩了");

            st.close();
            Log.v("DB", "寫入資料完成");
        }
        catch(SQLException e){
            e.printStackTrace();
            Log.e("DB", "爆了");
            Log.e("DB", e.toString());
        }
    }

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
                query = "SELECT * FROM competition WHERE `userid` = '" + username + "'";
                st = con.createStatement();
                rs = st.executeQuery(query);
                UserInfo.compNames = new ArrayList<String>();
                while(rs.next()){
                    String n = rs.getString("name");
                    String y = rs.getString("year");
                    (UserInfo.compNames).add(n + " (" + y + ") ");
                }
                Log.v("DB", "寫入資料完成：" + username + ", " + compName + ", " + year);
                st.close();
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
            if(rs.next()){
                query = "SELECT * FROM competition WHERE `userid` = '" + name + "'";
                st = con.createStatement();
                rs = st.executeQuery(query);
                UserInfo.compNames = new ArrayList<String>();
                while(rs.next()){
                    String n = rs.getString("name");
                    String y = rs.getString("year");
                    (UserInfo.compNames).add(n + " (" + y + ") ");
                }

                return true;
            }
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

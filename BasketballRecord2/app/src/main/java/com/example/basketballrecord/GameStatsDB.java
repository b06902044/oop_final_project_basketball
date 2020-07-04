package com.example.basketballrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//this activity is no use

public class GameStatsDB extends SQLiteOpenHelper {

    private String TableName;

    public GameStatsDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, String TableName) {
        super(context, name, factory, version);
        this.TableName = TableName;
        Log.v("john", "createDataBase");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command = "CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Number TEXT, " +
                "Name TEXT, " +
                "PTS INTEGER, " +
                "FGM INTEGER, " +
                "FGA INTEGER, " +
                "FG% REAL, " +
                "3PM INTEGER, " +
                "3PA INTEGER, " +
                "3P% REAL, " +
                "FTM INTEGER, " +
                "FTA INTEGER, " +
                "FT% REAL, " +
                "REB INTEGER, " +
                "OFF INTEGER, " +
                "DEF INTEGER, " +
                "AST INTEGER, " +
                "STL INTEGER, " +
                "BLK INTEGER, " +
                "TO INTEGER, " +
                "PF INTEGER, " +
                ");";
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String command = "DROP TABLE " + TableName;
        db.execSQL(command);
    }

    public void add(Player player){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Number", player.getNumber());
        contentValues.put("Name", player.getName());
        contentValues.put("PTS", player.getvalue("PTS"));
        contentValues.put("FGM", player.getvalue("FGM"));
        contentValues.put("FGA", player.getvalue("FGA"));
        contentValues.put("FG%", player.getPercentage("FG%"));
        contentValues.put("3PM", player.getvalue("threeMade"));
        contentValues.put("3PA", player.getvalue("3PA"));
        contentValues.put("3P%", player.getPercentage("3P%"));
        contentValues.put("FTM", player.getvalue("freeThrowMade"));
        contentValues.put("FTA", player.getvalue("FTA"));
        contentValues.put("FT%", player.getPercentage("FT%"));
        contentValues.put("REB", player.getvalue("REB"));
        contentValues.put("OFF", player.getvalue("offenseRebound"));
        contentValues.put("DEF", player.getvalue("defenseRebound"));
        contentValues.put("AST" ,player.getvalue("assist"));
        contentValues.put("STL", player.getvalue("steal"));
        contentValues.put("BLK", player.getvalue("block"));
        contentValues.put("TO", player.getvalue("turnover"));
        contentValues.put("PF", player.getvalue("PF"));
        db.insert(TableName, null, contentValues);
    }
}

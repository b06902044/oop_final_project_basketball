package com.example.basketballrecord;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {

    private String number;
    private String name;
    private String oncourt;
    private String[] type = new String[]{"twoMade", "twoMissed","threeMade", "threeMissed", "freeThrowMade", "freeThrowMissed",
"offenseRebound", "defenseRebound", "assist", "steal", "block", "turnover", "offenseFoul", "defenseFoul"};
    private HashMap<String, Integer> stat;
    private HashMap<String, String> percentage;

    public Player(String number, String name, String oncourt){
        this.number = number;
        this.name = name;
        this.oncourt = oncourt;
        stat = new HashMap<>();
        percentage = new HashMap<>();
        for(String str : type){
            stat.put(str, 0);
        }
    }

    public String print(){
        String out = "";
        for(String s : type){
            out += s;
            out += " : ";
            out += stat.get(s);
        }
        return out;
    }

    public void add(String s){
        stat.put(s, stat.get(s) + 1);
    }

    public String getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }

    public String getOncourt(){
        return oncourt;
    }

    public void setOncourt(String s){
        oncourt = s;
    }

    public int getvalue(String str){
        return stat.get(str);
    }

    public String getPercentage(String str){
        return percentage.get(str);
    }

    public void setValue(String str){
        stat.put(str, stat.get(str) + 1);
    }

    public void finalCal(){
        int points = 2 * stat.get("twoMade") + 3 * stat.get("threeMade") + stat.get("freeThrowMade");
        Log.v("john", "point = " + points);
        stat.put("PTS",points);
        Log.v("john", "PTS = " + stat.get("PTS"));
        stat.put("FGM", stat.get("twoMade") + stat.get("threeMade"));
        stat.put("FGA", stat.get("FGM") + stat.get("twoMissed") + stat.get("threeMissed"));
        if(stat.get("FGA") > 0){
            float tmp = (float) (stat.get("FGM")) / stat.get("FGA");
            percentage.put("FG%", "" + tmp * 100 + "%");
        }
        else{
            percentage.put("FG%", "0.0%");
        }
        stat.put("3PM", stat.get("threeMade"));
        stat.put("3PA", stat.get("threeMade") + stat.get("threeMissed"));
        if(stat.get("3PA") > 0){
            float tmp = (float) (stat.get("threeMade")) / stat.get("3PA");
            percentage.put("3P%", "" + tmp * 100 + "%");
        }
        else{
            percentage.put("3P%", "0.0%");
        }
        stat.put("FTM", stat.get("freeThrowMade"));
        stat.put("FTA", (stat.get("freeThrowMade") + stat.get("freeThrowMissed")));
        if(stat.get("FTA") > 0){
            float tmp = (float) (stat.get("freeThrowMade")) / stat.get("FTA");
            percentage.put("FT%", "" + tmp * 100 + "%");
        }
        else{
            percentage.put("FT%", "0.0%");
        }
        stat.put("OFF", stat.get("offenseRebound"));
        stat.put("DEF", stat.get("defenseRebound"));
        stat.put("REB", stat.get("offenseRebound") + stat.get("defenseRebound"));
        stat.put("AST", stat.get("assist"));
        stat.put("STL", stat.get("steal"));
        stat.put("BLK", stat.get("block"));
        stat.put("TO", stat.get("turnover"));
        stat.put("PF", stat.get("offenseFoul") + stat.get("defenseFoul"));
    }

}

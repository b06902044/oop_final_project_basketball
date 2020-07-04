package com.example.basketballrecord;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AllStatistics {
    private ArrayList<Player> players;
    private ArrayList<Integer> myScoreSec, GuestScoreSec;
    private int myScore, guestScore;
    private String home;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Integer> getMyScoreSec() {
        return myScoreSec;
    }

    public void setMyScoreSec(ArrayList<Integer> myScoreSec) {
        this.myScoreSec = myScoreSec;
    }

    public ArrayList<Integer> getGuestScoreSec() {
        return GuestScoreSec;
    }

    public void setGuestScoreSec(ArrayList<Integer> guestScoreSec) {
        GuestScoreSec = guestScoreSec;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    private String guest;
    private String type;
    private String date;
    public AllStatistics(ArrayList<Player> players, ArrayList<Integer> myScoreSec, ArrayList<Integer> guestScoreSec, int myScore, int guestScore
            , String home, String guest, String type, String date){
        this.players = players;
        this.myScoreSec = myScoreSec;
        this.GuestScoreSec = guestScoreSec;
        this.myScore = myScore;
        this.guestScore = guestScore;
        this.home = home;
        this.guest = guest;
        this.type = type;
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getGuest() + this.getHome() + this.getDate() + this.getType();
    }
}

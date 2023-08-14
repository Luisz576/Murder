package com.luisz.murder.game.data;

public class ScoreboardData {
    private int time = 0, alivePlayers = 0;
    public void time(int time){
        this.time = time;
    }
    public int time(){
        return time;
    }
    public void alivePlayers(int alivePlayers){
        this.alivePlayers = alivePlayers;
    }
    public int alivePlayers(){
        return alivePlayers;
    }
}
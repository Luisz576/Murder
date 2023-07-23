package com.luisz.murder.game.enums;

public enum GamePlayerType {
    SPECTATOR,
    VICTIM,
    ASSASSIN,
    DETECTIVE,
    NONE;

    public boolean isGamePlayer(){
        return this == VICTIM || this == ASSASSIN || this == DETECTIVE;
    }
}
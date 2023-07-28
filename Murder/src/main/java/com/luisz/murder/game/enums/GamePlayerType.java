package com.luisz.murder.game.enums;

import org.bukkit.ChatColor;

public enum GamePlayerType {
    SPECTATOR(ChatColor.DARK_GRAY),
    VICTIM(ChatColor.GREEN),
    ASSASSIN(ChatColor.DARK_RED),
    DETECTIVE(ChatColor.BLUE),
    NONE(ChatColor.YELLOW);

    private final ChatColor color;
    GamePlayerType(ChatColor color){
        this.color = color;
    }

    public boolean isGamePlayer(){
        return this == VICTIM || this == ASSASSIN || this == DETECTIVE;
    }

    public ChatColor getColor(){
        return this.color;
    }
}
package com.luisz.murder.building.arena;

import com.luisz.murder.arena.ArenaLocation;
import com.luisz.murder.game.data.SkinData;

import java.util.ArrayList;
import java.util.List;

public class ArenaEditing {
    public final String NAME;
    public String WORLD;
    public int MIN_PLAYERS = 0, MAX_PLAYERS = 0;
    public final List<SkinData> SKINS = new ArrayList<>();
    public final List<ArenaLocation> SPAWNS = new ArrayList<>();
    public final List<ArenaLocation> COINS_SPAWNS = new ArrayList<>();

    public ArenaEditing(String NAME){
        this.NAME = NAME;
    }

    public boolean isValidToBuild(){
        return NAME != null
            && WORLD != null
            && MIN_PLAYERS > 0
            && MAX_PLAYERS > 0
            && SPAWNS.size() > 0
            && COINS_SPAWNS.size() > 0
            && SKINS.size() > 0
            && SKINS.size() >= MAX_PLAYERS;
    }

    public String toDataString(){
        return "NAME: " + NAME
                + "\nWORLD: " + WORLD
                + "\nMIN_PLAYERS: " + MIN_PLAYERS
                + "\nMAX_PLAYERS: " + MAX_PLAYERS
                + "\nSPAWNS: " + SPAWNS.size()
                + "\nCOINS_SPAWNS: " + COINS_SPAWNS.size()
                + "\nSPAWNS: " + SPAWNS.size();
    }
}
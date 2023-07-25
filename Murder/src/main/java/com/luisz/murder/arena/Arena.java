package com.luisz.murder.arena;

import com.luisz.murder.exceptions.ArenaInvalidDataException;
import com.luisz.murder.game.data.SkinData;

import java.util.*;

public class Arena {
    public final String NAME, WORLD;
    public final int MIN_PLAYERS, MAX_PLAYERS;
    private final List<SkinData> skins = new ArrayList<>();
    public List<SkinData> getSkins(){
        return Collections.unmodifiableList(skins);
    }
    private final List<ArenaLocation> spawns = new ArrayList<>();
    public List<ArenaLocation> getSpawns(){
        return Collections.unmodifiableList(spawns);
    }
    private final List<ArenaLocation> coinsSpawns = new ArrayList<>();
    public List<ArenaLocation> getCoinsSpawns(){
        return Collections.unmodifiableList(coinsSpawns);
    }

    public Arena(String NAME, String WORLD,
             int MIN_PLAYERS, int MAX_PLAYERS,
             Collection<SkinData> skins,
             Collection<ArenaLocation> spawns,
             Collection<ArenaLocation> coinsSpawns) throws ArenaInvalidDataException{
        this.NAME = NAME.toLowerCase(Locale.ROOT);
        this.WORLD = WORLD;
        this.MIN_PLAYERS = MIN_PLAYERS;
        this.MAX_PLAYERS = MAX_PLAYERS;
        if(MAX_PLAYERS > skins.size()){
            throw new ArenaInvalidDataException();
        }
        this.skins.addAll(skins);
        this.spawns.addAll(spawns);
        this.coinsSpawns.addAll(coinsSpawns);
    }

    public Arena createNew(){
        try{
            return new Arena(
                    this.NAME,
                    this.WORLD,
                    this.MIN_PLAYERS,
                    this.MAX_PLAYERS,
                    this.skins,
                    this.spawns,
                    this.coinsSpawns
            );
        }catch (Exception ignored){
            return null;
        }
    }
}
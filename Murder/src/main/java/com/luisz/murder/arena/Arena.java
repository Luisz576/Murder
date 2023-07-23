package com.luisz.murder.arena;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.murder.game.data.SkinData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Arena {
    public final String NAME, WORLD;
    public final int MIN_PLAYERS, MAX_PLAYERS;
    private final List<SkinData> skins = new ArrayList<>();
    public List<SkinData> getSkins(){
        return Collections.unmodifiableList(skins);
    }
    private final List<UnmodifiableVector3D> spawns = new ArrayList<>();
    public List<UnmodifiableVector3D> getSpawn(){
        return Collections.unmodifiableList(spawns);
    }
    private final List<UnmodifiableVector3D> coinsSpawns = new ArrayList<>();
    public List<UnmodifiableVector3D> getCoinsSpawns(){
        return Collections.unmodifiableList(coinsSpawns);
    }

    public Arena(String NAME, String WORLD,
             int MIN_PLAYERS, int MAX_PLAYERS,
             Collection<SkinData> skins,
             Collection<UnmodifiableVector3D> spawns,
             Collection<UnmodifiableVector3D> coinsSpawns){
        this.NAME = NAME;
        this.WORLD = WORLD;
        this.MIN_PLAYERS = MIN_PLAYERS;
        this.MAX_PLAYERS = MAX_PLAYERS;
        this.skins.addAll(skins);
        this.spawns.addAll(spawns);
        this.coinsSpawns.addAll(coinsSpawns);
    }
}
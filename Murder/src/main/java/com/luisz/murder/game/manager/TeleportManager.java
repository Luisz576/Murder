package com.luisz.murder.game.manager;

import com.luisz.lapi.common.random.LRandom;
import com.luisz.murder.arena.ArenaLocation;
import com.luisz.murder.game.profile.Profile;

import java.util.List;

public class TeleportManager {
    private final List<ArenaLocation> spawns;
    private final PlayersManager playersManager;

    public TeleportManager(PlayersManager playersManager, List<ArenaLocation> spawns){
        this.playersManager = playersManager;
        this.spawns = spawns;
    }

    public void spawnEveryone(){
        int rs = this.spawns.size();
        for(Profile profile : this.playersManager.getAllProfiles()){
            profile.player.teleport(this.spawns.get(LRandom.randomInt(rs)).getLocation());
        }
    }
}
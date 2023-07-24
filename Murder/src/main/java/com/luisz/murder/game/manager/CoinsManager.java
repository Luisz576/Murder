package com.luisz.murder.game.manager;

import com.luisz.lapi.common.math.vector.Vector3D;
import com.luisz.murder.game.profile.Profile;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class CoinsManager {
    private final Map<Vector3D, Entity> spawnedCoins = new HashMap<>();

    public void _gameTick(){
        // TODO
    }

    public void getCoin(Profile profile, Entity coin){
        //TODO
    }

    public void unspawnAll(){
        for(Entity coin : spawnedCoins.values()){
            coin.remove();
        }
        spawnedCoins.clear();
    }
}
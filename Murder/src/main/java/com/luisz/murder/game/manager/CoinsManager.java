package com.luisz.murder.game.manager;

import com.luisz.murder.arena.ArenaLocation;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.events.PlayerPickupCoinEvent;
import com.luisz.murder.game.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinsManager {
    public final Game game;
    private final PlayersManager playersManager;
    private final Map<ArenaLocation, Entity> spawnedCoins = new HashMap<>();
    private final Map<ArenaLocation, Integer> coinsProbabilities = new HashMap<>();

    public CoinsManager(Game game, PlayersManager playersManager, List<ArenaLocation> coinsSpawns){
        this.game = game;
        this.playersManager = playersManager;
        for(ArenaLocation coinSpawn : coinsSpawns){
            coinsProbabilities.put(coinSpawn, 100);
        }
    }

    public void _gameTick(){
        // TODO
    }
    private void spawnRandomCoin(){

    }

    public boolean isCoin(Entity coin){
        Collection<Entity> coins = spawnedCoins.values();
        for(Entity c : coins){
            if(c.getEntityId() == coin.getEntityId()){
                return true;
            }
        }
        return false;
    }
    public boolean pickupCoin(Player player, Entity coin){
        ArenaLocation target = null;
        for(Map.Entry<ArenaLocation, Entity> c : spawnedCoins.entrySet()){
            if(c.getValue().getEntityId() == coin.getEntityId()){
               target = c.getKey();
            }
        }
        if(target == null){
            return false;
        }
        Profile profile = this.playersManager.getProfile(player);
        if(profile == null){
            return false;
        }
        spawnedCoins.get(target).remove();
        spawnedCoins.remove(target);
        Bukkit.getPluginManager().callEvent(new PlayerPickupCoinEvent(this.game, profile));
        return true;
    }

    public void unspawnAll(){
        for(Entity coin : spawnedCoins.values()){
            coin.remove();
        }
        spawnedCoins.clear();
    }
}
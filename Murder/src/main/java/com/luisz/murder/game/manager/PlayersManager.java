package com.luisz.murder.game.manager;

import com.luisz.murder.game.profile.Profile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class PlayersManager {
    private final HashMap<Player, Profile> profiles = new HashMap<>();

    public void addPlayer(Player player){
        this.profiles.put(player, new Profile(player));
    }
    public boolean contains(Player player){
        return this.profiles.containsKey(player);
    }
    public Profile getProfile(Player player){
        return this.profiles.get(player);
    }
    public Profile removeProfile(Player player){
        return this.profiles.remove(player);
    }

    public void sendMessage(String message){
        Set<Player> players = profiles.keySet();
        for(Player p : players){
            p.sendMessage(message);
        }
    }
}
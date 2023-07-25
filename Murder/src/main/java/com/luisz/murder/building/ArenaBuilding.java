package com.luisz.murder.building;

import com.luisz.murder.arena.Arena;
import com.luisz.murder.building.arena.ArenaEditing;
import com.luisz.murder.exceptions.ArenaInvalidDataException;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ArenaBuilding {
    private final Map<Player, ArenaEditing> arenas = new HashMap<>();

    public boolean start(Player player, String arena){
        arena = arena.toLowerCase(Locale.ROOT);
        if(isEditingThisArena(arena)){
            return false;
        }
        this.arenas.put(player, new ArenaEditing(arena));
        return true;
    }

    public boolean isEditingThisArena(String arena){
        for(ArenaEditing arenaEditing : this.arenas.values()){
            if(arenaEditing.NAME.equalsIgnoreCase(arena)){
                return true;
            }
        }
        return false;
    }
    public boolean isEditing(Player player){
        return arenas.containsKey(player);
    }

    public String getArenaInfo(Player player){
        ArenaEditing arenaEditing = arenas.get(player);
        if(arenaEditing != null){
            return arenaEditing.toDataString();
        }
        return null;
    }

    public boolean build(Player player){
        ArenaEditing arenaEditing = arenas.get(player);
        if(arenaEditing != null && arenaEditing.isValidToBuild()){
            try{
                Arena arena = new Arena(
                    arenaEditing.NAME,
                    arenaEditing.WORLD,
                    arenaEditing.MIN_PLAYERS,
                    arenaEditing.MAX_PLAYERS,
                    arenaEditing.SKINS,
                    arenaEditing.SPAWNS,
                    arenaEditing.COINS_SPAWNS
                );
                arenas.remove(player);
                return MurderPluginManager.getArenasConfig().registerArena(arena);
            }catch (ArenaInvalidDataException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
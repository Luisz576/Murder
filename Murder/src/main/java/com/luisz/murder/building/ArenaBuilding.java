package com.luisz.murder.building;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.ArenaLocation;
import com.luisz.murder.building.arena.ArenaEditing;
import com.luisz.murder.exceptions.ArenaInvalidDataException;
import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ArenaBuilding {
    private final Map<Player, ArenaEditing> arenas = new HashMap<>();

    public boolean start(Player player, String arena, String world){
        arena = arena.toLowerCase(Locale.ROOT);
        if(isEditingThisArena(arena)){
            return false;
        }
        this.arenas.put(player, new ArenaEditing(arena, world));
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

    public boolean addSkin(Player player, String skin, String nickname){
        ArenaEditing arenaEditing = this.arenas.get(player);
        if(arenaEditing == null || skin == null || nickname == null){
            return false;
        }
        nickname = nickname.replaceAll("&", "§");
        for(SkinData skinData : arenaEditing.SKINS){
            if(skinData.nickname.equalsIgnoreCase(nickname)){
               return false;
            }
        }
        arenaEditing.SKINS.add(new SkinData(skin, nickname));
        return true;
    }

    public boolean addSpawn(Player player, Location location, String spawn_name){
        if(location == null){
            return false;
        }
        spawn_name = spawn_name.toLowerCase(Locale.ROOT);
        ArenaEditing arenaEditing = arenas.get(player);
        if(arenaEditing == null){
            return false;
        }
        for(ArenaLocation al : arenaEditing.SPAWNS){
            if(al.id.equalsIgnoreCase(spawn_name)){
                return false;
            }
        }
        arenaEditing.SPAWNS.add(new ArenaLocation(spawn_name, arenaEditing.WORLD, new UnmodifiableVector3D(location.getBlockX(), location.getBlockY(), location.getBlockZ())));
        return true;
    }

    public boolean addCoinSpawn(Player player, Location location, String coin_spawn_name){
        if(location == null){
            return false;
        }
        coin_spawn_name = coin_spawn_name.toLowerCase(Locale.ROOT);
        ArenaEditing arenaEditing = arenas.get(player);
        if(arenaEditing == null){
            return false;
        }
        for(ArenaLocation al : arenaEditing.COINS_SPAWNS){
            if(al.id.equalsIgnoreCase(coin_spawn_name)){
                return false;
            }
        }
        arenaEditing.COINS_SPAWNS.add(new ArenaLocation(coin_spawn_name, arenaEditing.WORLD, new UnmodifiableVector3D(location.getBlockX(), location.getBlockY(), location.getBlockZ())));
        return true;
    }

    public boolean setMinAndMaxPlayers(Player player, int min, int max){
        ArenaEditing arenaEditing = this.arenas.get(player);
        if(arenaEditing == null || min <= 1 || max <= 1 || min > max){
            return false;
        }
        arenaEditing.MIN_PLAYERS = min;
        arenaEditing.MAX_PLAYERS = max;
        return true;
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
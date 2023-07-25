package com.luisz.murder.arena.configs.loader;

import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.loader.ConfigLoader;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.ArenaLocation;
import com.luisz.murder.arena.configs.adapters.SkinsLoaderAdapter;
import com.luisz.murder.arena.configs.adapters.ArenaLocationLoaderAdapter;
import com.luisz.murder.exceptions.ArenaInvalidDataException;
import com.luisz.murder.game.data.SkinData;

import java.util.List;

public class ArenaLoader implements ConfigLoader<Arena> {
    private final ArenaLocationLoaderAdapter arenaLocationLoaderAdapter = new ArenaLocationLoaderAdapter();
    private final SkinsLoaderAdapter skinsLoaderAdapter = new SkinsLoaderAdapter();

    @Override
    public Arena load(LConfig lConfig, String key) {
        String name = lConfig.getString(key + ".name");
        String world = lConfig.getString(key + ".world");
        int min_players = lConfig.getInt(key + ".min_players");
        int max_players = lConfig.getInt(key + ".max_players");
        // spawns
        List<ArenaLocation> spawns = lConfig.getCustomListValue(key + ".spawns", arenaLocationLoaderAdapter);
        // skins
        List<SkinData> skins = lConfig.getCustomListValue(key + ".skins", skinsLoaderAdapter);
        // coins_spawns
        List<ArenaLocation> coinsSpawns = lConfig.getCustomListValue(key + ".coins_spawns", arenaLocationLoaderAdapter);
        try{
            return new Arena(name, world, min_players, max_players, skins, spawns, coinsSpawns);
        }catch (ArenaInvalidDataException e){
            e.printStackTrace();
            return null;
        }
    }
}
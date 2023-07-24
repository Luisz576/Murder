package com.luisz.murder.arena.configs.loader;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.loader.ConfigLoader;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.configs.adapters.SkinsLoaderAdapter;
import com.luisz.murder.arena.configs.adapters.UnmodifiableVector3DLoaderAdapter;
import com.luisz.murder.game.data.SkinData;

import java.util.List;

public class ArenaLoader implements ConfigLoader<Arena> {
    private final UnmodifiableVector3DLoaderAdapter unmodifiableVector3DLoaderAdapter = new UnmodifiableVector3DLoaderAdapter();
    private final SkinsLoaderAdapter skinsLoaderAdapter = new SkinsLoaderAdapter();

    @Override
    public Arena load(LConfig lConfig, String key) {
        String name = lConfig.getString(key + ".name");
        String world = lConfig.getString(key + ".world");
        int min_players = lConfig.getInt(key + ".min_players");
        int max_players = lConfig.getInt(key + ".max_players");
        // spawns
        List<UnmodifiableVector3D> spawns = lConfig.getCustomListValue(key + ".spawns", unmodifiableVector3DLoaderAdapter);
        // skins
        List<SkinData> skins = lConfig.getCustomListValue(key + ".skins", skinsLoaderAdapter);
        // coins_spawns
        List<UnmodifiableVector3D> coinsSpawns = lConfig.getCustomListValue(key + ".coins_spawns", unmodifiableVector3DLoaderAdapter);
        return new Arena(name, world, min_players, max_players, skins, spawns, coinsSpawns);
    }
}
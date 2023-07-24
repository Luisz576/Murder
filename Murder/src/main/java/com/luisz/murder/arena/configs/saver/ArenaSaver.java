package com.luisz.murder.arena.configs.saver;

import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.saver.ConfigSaver;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.configs.adapters.SkinsSaverAdapter;
import com.luisz.murder.arena.configs.adapters.UnmodifiableVector3DSaverAdapter;

public class ArenaSaver implements ConfigSaver<Arena> {
    private final UnmodifiableVector3DSaverAdapter unmodifiableVector3DSaverAdapter = new UnmodifiableVector3DSaverAdapter();
    private final SkinsSaverAdapter skinsSaverAdapter = new SkinsSaverAdapter();

    @Override
    public boolean save(LConfig lConfig, String key, Arena arena) {
        lConfig.setValue(key + ".name", arena.NAME);
        lConfig.setValue(key + ".world", arena.WORLD);
        lConfig.setValue(key + ".max_players", arena.MAX_PLAYERS);
        lConfig.setValue(key + ".min_players", arena.MIN_PLAYERS);
        // spawns
        lConfig.setCustomListValue(key + ".spawns", unmodifiableVector3DSaverAdapter, arena.getSpawns());
        // skins
        lConfig.setCustomListValue(key + ".skins", skinsSaverAdapter, arena.getSkins());
        // coins_spawns
        lConfig.setCustomListValue(key + ".coins_spawns", unmodifiableVector3DSaverAdapter, arena.getCoinsSpawns());
        return true;
    }
}
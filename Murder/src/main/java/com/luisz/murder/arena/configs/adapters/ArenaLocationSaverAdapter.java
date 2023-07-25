package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.SaverAdapter;
import com.luisz.murder.arena.ArenaLocation;

public class ArenaLocationSaverAdapter implements SaverAdapter<ArenaLocation> {
    @Override
    public void save(LConfig lConfig, String key, ArenaLocation value) {
        lConfig.setValue(key + ".loc", value.loc);
        lConfig.setValue(key + ".id", value.id);
    }
}
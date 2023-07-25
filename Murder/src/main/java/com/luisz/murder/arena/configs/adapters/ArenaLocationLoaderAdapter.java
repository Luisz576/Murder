package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.LoaderAdapter;
import com.luisz.murder.arena.ArenaLocation;

public class ArenaLocationLoaderAdapter implements LoaderAdapter<ArenaLocation> {
    @Override
    public ArenaLocation load(LConfig lConfig, String key) {
        return new ArenaLocation(
            lConfig.getString(key + ".id"),
            UnmodifiableVector3D.fromVector3D(lConfig.getVector3D(key + ".loc"))
        );
    }
}
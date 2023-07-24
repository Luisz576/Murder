package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.LoaderAdapter;

public class UnmodifiableVector3DLoaderAdapter implements LoaderAdapter<UnmodifiableVector3D> {
    @Override
    public UnmodifiableVector3D load(LConfig lConfig, String key) {
        return UnmodifiableVector3D.fromVector3D(lConfig.getVector3D(key));
    }
}
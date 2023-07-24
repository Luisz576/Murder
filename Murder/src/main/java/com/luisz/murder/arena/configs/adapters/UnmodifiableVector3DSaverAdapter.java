package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.SaverAdapter;

public class UnmodifiableVector3DSaverAdapter implements SaverAdapter<UnmodifiableVector3D> {
    @Override
    public void save(LConfig lConfig, String key, UnmodifiableVector3D value) {
        lConfig.setValue(key, value);
    }
}
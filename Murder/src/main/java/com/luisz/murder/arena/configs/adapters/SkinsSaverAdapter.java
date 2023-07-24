package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.SaverAdapter;
import com.luisz.murder.game.data.SkinData;

public class SkinsSaverAdapter implements SaverAdapter<SkinData> {
    @Override
    public void save(LConfig lConfig, String key, SkinData skinData) {
        lConfig.setValue(key + ".skin", skinData.skin);
        lConfig.setValue(key + ".nickname", skinData.nickname);
    }
}
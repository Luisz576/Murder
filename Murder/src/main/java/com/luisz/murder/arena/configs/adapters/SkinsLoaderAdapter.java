package com.luisz.murder.arena.configs.adapters;

import com.luisz.lapi.config.LConfig;
import com.luisz.lapi.config.adapter.LoaderAdapter;
import com.luisz.murder.game.data.SkinData;

public class SkinsLoaderAdapter implements LoaderAdapter<SkinData> {
    @Override
    public SkinData load(LConfig lConfig, String key) {
        String skin = lConfig.getString(key + ".skin");
        String nickname = lConfig.getString(key + ".nickname");
        return new SkinData(skin, nickname);
    }
}
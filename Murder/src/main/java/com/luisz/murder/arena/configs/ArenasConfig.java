package com.luisz.murder.arena.configs;

import com.luisz.lapi.config.LConfig;
import com.luisz.murder.exceptions.ArenasNotLoadedException;
import org.bukkit.plugin.Plugin;

public class ArenasConfig {
    private final LConfig lConfig;
    public ArenasConfig(Plugin plugin) throws ArenasNotLoadedException {
        lConfig = new LConfig("arenas", plugin);
        //TODO
    }
}
package com.luisz.murder.arena.configs;

import com.luisz.lapi.config.LConfig;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.configs.loader.ArenaLoader;
import com.luisz.murder.arena.configs.saver.ArenaSaver;
import com.luisz.murder.exceptions.ArenasNotLoadedException;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class ArenasConfig {
    private final String KEY_ARENAS_LIST = "arenas_list";
    private final String KEY_ARENAS = "arenas";

    private final LConfig lConfig;
    private final List<String> arenas_list = new ArrayList<>();
    private final Map<String, Arena> arenas = new HashMap<>();
    private final ArenaSaver arenaSaver = new ArenaSaver();
    private final ArenaLoader arenaLoader = new ArenaLoader();

    public ArenasConfig(Plugin plugin) throws ArenasNotLoadedException {
        this.lConfig = new LConfig("arenas", plugin);
        _loadArenas();
        this.lConfig.save();
    }

    private void _loadArenas(){
        this.arenas_list.clear();
        this.arenas.clear();
        List<String> a_list = (List<String>) lConfig.getList(KEY_ARENAS_LIST);
        if(a_list == null){
            return;
        }
        this.arenas_list.addAll(a_list);
        for(String a : this.arenas_list){
            Arena arena = arenaLoader.load(this.lConfig, KEY_ARENAS + "." + a);
            if(arena == null){
                MurderPluginManager.sendPluginConsoleMessage("Arena '" + a + "' not loaded", ChatColor.RED);
                continue;
            }
            this.arenas.put(a, arena);
        }
    }

    private void save(){
        this.lConfig.setValue(KEY_ARENAS_LIST, arenas_list);
        this.lConfig.save();
    }

    public Arena getArena(String arena){
        Collection<Arena> arenas = this.arenas.values();
        for(Arena a : arenas){
            if(a.NAME.equalsIgnoreCase(arena)){
                return a.createNew();
            }
        }
        return null;
    }

    public boolean registerArena(Arena arena){
        if(arena == null || arena.NAME.isEmpty()) return false;
        if(arenaSaver.save(this.lConfig, KEY_ARENAS + "." + arena.NAME, arena)) {
            this.arenas_list.add(arena.NAME.toLowerCase(Locale.ROOT));
            this.arenas.put(arena.NAME.toLowerCase(Locale.ROOT), arena.createNew());
            save();
            return true;
        }
        return false;
    }
    public boolean containsArena(String arena){
        for(String a : this.arenas_list){
            if(a.equalsIgnoreCase(arena)){
                return true;
            }
        }
        return false;
    }
    public boolean unregisterArena(String arena){
        arena = arena.toLowerCase(Locale.ROOT);
        if(arena.isEmpty() || !containsArena(arena)) return false;
        lConfig.remove(KEY_ARENAS + "." + arena);
        this.arenas_list.remove(arena);
        this.arenas.remove(arena);
        save();
        return true;
    }
}
package com.luisz.murder.manager;

import com.luisz.murder.arena.configs.ArenasConfig;
import com.luisz.murder.building.ArenaBuilding;
import com.luisz.murder.exceptions.ArenasNotLoadedException;
import com.luisz.murder.exceptions.SetupException;
import com.luisz.murder.exceptions.UnsetupDeniedException;
import com.luisz.murder.game.manager.GamesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class MurderPluginManager {
    private static Plugin plugin;
    private static CommandSender cmd;
    private static String pluginName;
    private static org.bukkit.plugin.PluginManager pm;
    private static GamesManager gamesManager;
    private static ArenasConfig arenasConfig;
    private static ArenaBuilding arenaBuilding;

    public static Plugin getPlugin(){
        return plugin;
    }
    public static GamesManager getGamesManager(){
        return gamesManager;
    }
    public static ArenasConfig getArenasConfig(){
        return arenasConfig;
    }
    public static ArenaBuilding getArenaBuilding(){
        return arenaBuilding;
    }

    public static void _setupPlugin(Plugin plugin, String pluginName) throws SetupException {
        MurderPluginManager.plugin = plugin;
        MurderPluginManager.pluginName = pluginName;
        MurderPluginManager.cmd = Bukkit.getConsoleSender();
        MurderPluginManager.pm = Bukkit.getPluginManager();
        MurderPluginManager.gamesManager = new GamesManager();
        MurderPluginManager.arenaBuilding = new ArenaBuilding();
        try{
            MurderPluginManager.arenasConfig = new ArenasConfig(plugin);
        }catch (ArenasNotLoadedException e){
            throw new SetupException(e.message);
        }
    }

    public static void _unsetupPlugin(Plugin plugin) throws UnsetupDeniedException {
        if(MurderPluginManager.plugin == plugin){
            return;
        }
        throw new UnsetupDeniedException();
    }

    public static void sendConsoleMessage(String message){
        cmd.sendMessage(message);
    }
    public static void sendPluginConsoleMessage(String message){
        sendPluginConsoleMessage(message, ChatColor.RED);
    }
    public static void sendPluginConsoleMessage(String message, ChatColor nameColor){
        cmd.sendMessage(nameColor + "[" + pluginName + "] " + message);
    }

    public static void registerListener(Listener listener) {
        pm.registerEvents(listener, plugin);
    }
    public static void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public static void callEvent(Event event){
        pm.callEvent(event);
    }
}
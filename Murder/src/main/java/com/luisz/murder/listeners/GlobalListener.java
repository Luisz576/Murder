package com.luisz.murder.listeners;

import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GlobalListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        MurderPluginManager.getGamesManager().quitPlayer(e.getPlayer());
    }
}
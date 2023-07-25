package com.luisz.murder;

import com.luisz.lapi.LPlugin;
import com.luisz.murder.commands.Commands;
import com.luisz.murder.exceptions.SetupException;
import com.luisz.murder.exceptions.UnsetupDeniedException;
import com.luisz.murder.listeners.GlobalListener;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Main extends LPlugin {
    @Override
    public void onEnable() {
        try{
            MurderPluginManager._setupPlugin(this, "Murder");
            MurderPluginManager.registerListener(new GlobalListener());
            _registerCommands();
            MurderPluginManager.sendPluginConsoleMessage("Plugin inicializado!", ChatColor.GREEN);
        }catch (SetupException e){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Murder] Erro ao iniciar!\nErro:\n" + e.reason);
        }
    }

    @Override
    public void onDisable() {
        try {
            MurderPluginManager._unsetupPlugin(this);
        } catch (UnsetupDeniedException e) {
            MurderPluginManager.sendPluginConsoleMessage("Erro ao desligar o plugin!");
        }
    }

    public void _registerCommands(){
        Commands commands = new Commands();
        getCommand("murderstartarena").setExecutor(commands);
        getCommand("murderstoparena").setExecutor(commands);
        getCommand("murderjoin").setExecutor(commands);
        getCommand("murderarenaedit").setExecutor(commands);
    }
}
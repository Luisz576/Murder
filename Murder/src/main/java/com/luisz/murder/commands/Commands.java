package com.luisz.murder.commands;

import com.luisz.murder.exceptions.ArenaAlreadyOpennedException;
import com.luisz.murder.exceptions.ArenaDoesntExistException;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "You can't use this command!");
            return false;
        }
        Player p = (Player) sender;
        if(!p.isOp()){
            sender.sendMessage(ChatColor.RED + "You can't use this command!");
            return false;
        }
        switch (cmd.getName().toLowerCase()){
            case "murderstartarena":
                if(args.length == 1){
                    String arena = args[0].toLowerCase(Locale.ROOT);
                    try{
                        if(MurderPluginManager.getGamesManager().startArena(arena)){
                            p.sendMessage(ChatColor.GREEN + "Arena '" + arena + "' is open now!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Can't open arena!");
                        }
                    }catch (ArenaAlreadyOpennedException | ArenaDoesntExistException e){
                        if(e instanceof ArenaAlreadyOpennedException){
                            p.sendMessage(ChatColor.RED + "Arena already open!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Arena doesn't exist!");
                        }
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Usage: /murderstartarena <arena>");
                }
                return true;
            case "murderstoparena":
                if(args.length == 1){
                    String arena = args[0].toLowerCase(Locale.ROOT);
                    if(MurderPluginManager.getGamesManager().stopArena(arena)){
                        p.sendMessage(ChatColor.YELLOW + "Arena '" + arena + "' closed!");
                    }else{
                        p.sendMessage(ChatColor.RED + "Arena isn't open!");
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Usage: /murderstoparena <arena>");
                }
                return true;
            case "murderjoin":
                if(args.length == 1){
                    String arena = args[0].toLowerCase(Locale.ROOT);
                    if(MurderPluginManager.getGamesManager().isArenaOpen(arena)){
                        if(MurderPluginManager.getGamesManager().joinPlayerIn(p, arena)){
                            p.sendMessage(ChatColor.GREEN + "Joining to arena...");
                        }else{
                            p.sendMessage(ChatColor.RED + "Unable to join arena!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "Arena isn't open!");
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Usage: /murderjoin <arena>");
                }
                return true;
        }
        return false;
    }
}
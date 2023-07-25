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
            case "murderarenaedit":
                if(args.length > 0){
                    String option = args[0].toLowerCase(Locale.ROOT);
                    switch (option){
                        case "start":
                            if(args.length == 2){
                                String arena = args[0].toLowerCase(Locale.ROOT);
                                if(!MurderPluginManager.getArenasConfig().containsArena(arena)){
                                    if(MurderPluginManager.getArenaBuilding().start(p, arena)) {
                                        p.sendMessage(ChatColor.GREEN + "You are editing a new arena!");
                                    }else{
                                        p.sendMessage(ChatColor.RED + "There is someone editing a arena with this name!");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "An arena with this name already exists!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Use: /murderarenaedit start <arena>");
                            }
                            break;
                        case "show":
                            if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                p.sendMessage(ChatColor.GREEN + "Arena:\n" + MurderPluginManager.getArenaBuilding().getArenaInfo(p));
                            }else{
                                p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                            }
                            break;
                        case "build":
                            if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                if(MurderPluginManager.getArenaBuilding().build(p)){
                                    p.sendMessage(ChatColor.GREEN + "Arena created!");
                                }else{
                                    p.sendMessage(ChatColor.GREEN + "Can't create arena!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                            }
                            break;
                        default:
                            p.sendMessage(ChatColor.RED + "This option doesn't exist!");
                            break;
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Usage: /murderarenaedit <option>");
                }
                return true;
        }
        return false;
    }
}
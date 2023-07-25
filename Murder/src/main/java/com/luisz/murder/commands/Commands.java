package com.luisz.murder.commands;

import com.luisz.lapi.player.skin.Skin;
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
                                String arena = args[1].toLowerCase(Locale.ROOT);
                                if(!MurderPluginManager.getArenasConfig().containsArena(arena)){
                                    if(MurderPluginManager.getArenaBuilding().start(p, arena, p.getWorld().getName())) {
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
                        case "minmax":
                            if(args.length == 3){
                                if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                    int min = 0, max = 0;
                                    try{
                                        min = Integer.parseInt(args[1]);
                                        max = Integer.parseInt(args[2]);
                                    }catch (Exception ignored){}
                                    if(min > 1 && max > 1 && MurderPluginManager.getArenaBuilding().setMinAndMaxPlayers(p, min, max)){
                                        p.sendMessage(ChatColor.GREEN + "Min and Max setted!");
                                    }else{
                                        p.sendMessage(ChatColor.RED + "Invalid value(s) passed!");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Use: /murderarenaedit minmax <min> <max>");
                            }
                            break;
                        case "addskin":
                            if(args.length == 3){
                                if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                    String skin = args[1].toLowerCase(Locale.ROOT), nickname = args[2];
                                    if(Skin.fromName(skin) != null){
                                        if(MurderPluginManager.getArenaBuilding().addSkin(p, skin, nickname)){
                                            p.sendMessage(ChatColor.GREEN + "Skin added!");
                                        }else{
                                            p.sendMessage(ChatColor.RED + "This nickname already exist!");
                                        }
                                    }else{
                                        p.sendMessage(ChatColor.RED + "Invalid skin!");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Use: /murderarenaedit addskin <skin> <nickname>");
                            }
                            break;
                        case "help":
                            p.sendMessage(
                                ChatColor.GREEN + "Usage: /murderarenaedit <option>" +
                                "\n<options>:" +
                                "\naddskin => add a skin" +
                                "\naddspawn => add a spawn" +
                                "\naddcoinspawn => add a coin spawn" +
                                "\nbuild => Build and register arena" +
                                "\nhelp => Show all commands" +
                                "\nminmax => Set min and max of an arena" +
                                "\nstart => Start editing a new arena" +
                                "\nshow => Show current arena info"
                            );
                            break;
                        case "addspawn":
                            if(args.length == 2){
                                if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                    String spawn_name = args[1].toLowerCase(Locale.ROOT);
                                    if(MurderPluginManager.getArenaBuilding().addSpawn(p, p.getLocation(), spawn_name)){
                                        p.sendMessage(ChatColor.GREEN + "Spawn added!");
                                    }else{
                                        p.sendMessage(ChatColor.RED + "Spawn already added!");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Use: /murderarenaedit addspawn <spawn_name>");
                            }
                            break;
                        case "addcoinspawn":
                            if(args.length == 2){
                                if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                    String coin_spawn_name = args[1].toLowerCase(Locale.ROOT);
                                    if(MurderPluginManager.getArenaBuilding().addCoinSpawn(p, p.getLocation(), coin_spawn_name)){
                                        p.sendMessage(ChatColor.GREEN + "Coin spawn added!");
                                    }else{
                                        p.sendMessage(ChatColor.RED + "Coin spawn already added!");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "You aren't editing an arena!");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Use: /murderarenaedit addcoinspawn <coin_spawn_name>");
                            }
                            break;
                        case "build":
                            if(MurderPluginManager.getArenaBuilding().isEditing(p)){
                                if(MurderPluginManager.getArenaBuilding().build(p)){
                                    p.sendMessage(ChatColor.GREEN + "Arena created!");
                                }else{
                                    p.sendMessage(ChatColor.RED + "Can't create arena!");
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
            case "murderremovearena":
                if(args.length == 1){
                    String arena = args[0].toLowerCase(Locale.ROOT);
                    if(MurderPluginManager.getArenasConfig().containsArena(arena)){
                        if(MurderPluginManager.getArenasConfig().unregisterArena(arena)){
                            p.sendMessage(ChatColor.GREEN + "Arena unregistered!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Arena not removed!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "Arena not founded!");
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Usage: /murderremovearena <arena>");
                }
                return true;
        }
        return false;
    }
}
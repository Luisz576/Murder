package com.luisz.murder.game.manager;

import com.luisz.lapi.game.exception.GameNotRegisteredException;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.arena.configs.ArenasConfig;
import com.luisz.murder.exceptions.ArenaAlreadyOpennedException;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.enums.GameStopReason;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GamesManager {
    private final HashMap<Arena, Game> games = new HashMap<>();

    public boolean quitPlayer(Player player){
        for(Game game : games.values()){
            if(game.quit(player)){
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerInSomeArenaLikePlayer(Player player){
        for(Game game : games.values()){
            if(game.isFromGameLikePlayer(player)){
                return true;
            }
        }
        return false;
    }

    public boolean startArena(String arenaS) throws ArenaAlreadyOpennedException{
        if(isArenaOpen(arenaS)){
            throw new ArenaAlreadyOpennedException();
        }
        ArenasConfig arenasConfig = MurderPluginManager.getArenasConfig();
        Arena arena = arenasConfig.getArena(arenaS);
        if(arena != null){
            try{
                games.put(arena, new Game(arena));
                return true;
            }catch (GameNotRegisteredException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean stopArena(String arena){
        Arena target = null;
        for (Arena a : games.keySet()){
            if(a.NAME.equalsIgnoreCase(arena)){
                target = a;
            }
        }
        if(target == null){
            return false;
        }
        Game game = games.remove(target);
        game.stopGame(GameStopReason.SYSTEM);
        return true;
    }

    public boolean isArenaOpen(String arena){
        for (Arena a : games.keySet()){
            if(a.NAME.equalsIgnoreCase(arena)){
                return true;
            }
        }
        return false;
    }
}
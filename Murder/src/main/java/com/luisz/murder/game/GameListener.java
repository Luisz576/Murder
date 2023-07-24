package com.luisz.murder.game;

import com.luisz.murder.game.enums.GamePlayerType;
import com.luisz.murder.game.events.PlayerJoinTheGameEvent;
import com.luisz.murder.game.events.PlayerQuitTheGameEvent;
import com.luisz.murder.game.events.PlayerPickupCoinEvent;
import com.luisz.murder.language.Texts;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameListener implements Listener {
    public final Game game;
    public GameListener(Game game){
        this.game = game;
    }

    @EventHandler
    public void onPlayerJoinTheGame(PlayerJoinTheGameEvent e){
        if(e.game == game){
            if(e.profile.type == GamePlayerType.SPECTATOR){
                e.profile.sendMessage(Texts.GAME_MESSAGE_PLAYER_JOIN_LIKE_SPECTATOR.getString(e.profile.getLanguage()));
                return;
            }
            e.profile.sendMessage(Texts.GAME_MESSAGE_PLAYER_JOIN_LIKE_PLAYER.getString(e.profile.getLanguage()));
        }
    }

    @EventHandler
    public void onPlayerQuitTheGame(PlayerQuitTheGameEvent e){
        if(e.game == game){
            if(e.profile.type == GamePlayerType.SPECTATOR){
                e.profile.sendMessage(Texts.GAME_MESSAGE_PLAYER_QUIT_LIKE_SPECTATOR.getString(e.profile.getLanguage()));
                return;
            }
            e.profile.sendMessage(Texts.GAME_MESSAGE_PLAYER_QUIT_LIKE_PLAYER.getString(e.profile.getLanguage()));
        }
    }

    @EventHandler
    public void onPlayerPickupCoin(PlayerPickupCoinEvent e){
        if(e.game == game){
            e.who.addCoin();
            game.profileInventoryManager.pickupCoin(e.who);
            e.who.sendMessage(Texts.GAME_RUNNING_PICKUP_COIN.getString(e.who.getLanguage()));
        }
    }
}
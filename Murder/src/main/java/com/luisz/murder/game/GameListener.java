package com.luisz.murder.game;

import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.murder.game.enums.GamePlayerType;
import com.luisz.murder.game.events.PlayerDieEvent;
import com.luisz.murder.game.events.PlayerJoinTheGameEvent;
import com.luisz.murder.game.events.PlayerQuitTheGameEvent;
import com.luisz.murder.game.events.PlayerPickupCoinEvent;
import com.luisz.murder.language.Texts;
import com.luisz.murder.language.TextsVar;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class GameListener implements Listener {
    public final Game game;
    public GameListener(Game game){
        this.game = game;
    }

    @EventHandler
    public void onPlayerDie(PlayerDieEvent e){
        if(e.game == game){
            List<Tuple<TextsVar, String>> vars = new ArrayList<>();
            vars.add(new Tuple<>(TextsVar.FIRST_PLAYER_NAME, e.killed.player.getName()));
            vars.add(new Tuple<>(TextsVar.SECOND_PLAYER_NAME, e.killer.player.getName()));
            game.sendGameMessageForEveryone(Texts.GAME_RUNNING_PLAYER_DIE, vars, ChatColor.YELLOW.toString());
        }
    }

    @EventHandler
    public void onPlayerJoinTheGame(PlayerJoinTheGameEvent e){
        if(e.game == game){
            boolean isSpectator = e.profile.type == GamePlayerType.SPECTATOR;
            Texts texts = isSpectator ? Texts.GAME_MESSAGE_PLAYER_JOIN_LIKE_SPECTATOR : Texts.GAME_MESSAGE_PLAYER_JOIN_LIKE_PLAYER;
            ChatColor messageColor = isSpectator ? ChatColor.DARK_GRAY : ChatColor.GREEN;
            List<Tuple<TextsVar, String>> vars = new ArrayList<>();
            vars.add(new Tuple<>(TextsVar.FIRST_PLAYER_NAME, ChatColor.YELLOW + e.profile.player.getName() + messageColor));
            game.sendGameMessageForEveryone(texts, vars, messageColor.toString());
        }
    }

    @EventHandler
    public void onPlayerQuitTheGame(PlayerQuitTheGameEvent e){
        if(e.game == game){
            boolean isSpectator = e.profile.type == GamePlayerType.SPECTATOR;
            Texts texts = isSpectator ? Texts.GAME_MESSAGE_PLAYER_QUIT_LIKE_SPECTATOR : Texts.GAME_MESSAGE_PLAYER_QUIT_LIKE_PLAYER;
            ChatColor messageColor = isSpectator ? ChatColor.DARK_GRAY : ChatColor.RED;
            List<Tuple<TextsVar, String>> vars = new ArrayList<>();
            vars.add(new Tuple<>(TextsVar.FIRST_PLAYER_NAME, e.profile.player.getName()));
            game.sendGameMessageForEveryone(texts, vars, messageColor.toString());
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
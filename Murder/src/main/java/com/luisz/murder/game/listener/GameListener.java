package com.luisz.murder.game.listener;

import com.luisz.murder.game.Game;
import org.bukkit.event.Listener;

public class GameListener implements Listener {
    public final Game game;
    public GameListener(Game game){
        this.game = game;
    }
}
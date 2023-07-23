package com.luisz.murder.game.manager;

import com.luisz.murder.game.Game;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.profile.Profile;
import org.bukkit.entity.Player;

public class ProfileInventoryManager {
    public final Game game;
    public ProfileInventoryManager(Game game){
        this.game = game;
    }
    public void giveItems(Profile profile){
        Player p = profile.player;
        p.getInventory().clear();
        if(game.getGameState() != GameState.RUNNING){
            return;
        }
        switch (profile.type){
            case SPECTATOR:
            case NONE:
                break;
        }
    }
}
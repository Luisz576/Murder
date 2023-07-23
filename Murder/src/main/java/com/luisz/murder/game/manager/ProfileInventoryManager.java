package com.luisz.murder.game.manager;

import com.luisz.murder.game.Game;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.items.GameItem;
import com.luisz.murder.game.items.GameItems;
import com.luisz.murder.game.profile.Profile;
import org.bukkit.entity.Player;

public class ProfileInventoryManager {
    public final Game game;
    private final GameItems gameItems = new GameItems();

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
            case ASSASSIN:
                p.getInventory().setItem(0, gameItems.build(
                    GameItem.SWORD,
                    1,
                    profile.playerProfile.language.getLikeLanguage()
                ));
                break;
            case DETECTIVE:
                p.getInventory().setItem(0, gameItems.build(
                        GameItem.PISTOL,
                        1,
                        profile.playerProfile.language.getLikeLanguage()
                ));
                p.getInventory().setItem(0, gameItems.build(
                        GameItem.AMMO,
                        3,
                        profile.playerProfile.language.getLikeLanguage()
                ));
                break;
            case VICTIM:
            case SPECTATOR:
            case NONE:
                break;
        }
    }

    public void buyAmmo(Profile profile){
        //TODO
    }
}
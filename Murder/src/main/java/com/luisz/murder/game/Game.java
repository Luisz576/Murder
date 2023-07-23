package com.luisz.murder.game;

import com.luisz.lapi.game.IGame;
import com.luisz.lapi.game.exception.GameNotRegisteredException;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.enums.GameStopReason;
import com.luisz.murder.game.manager.PlayersManager;
import com.luisz.murder.game.manager.ProfileInventoryManager;
import com.luisz.murder.game.profile.Profile;
import org.bukkit.entity.Player;

public class Game extends IGame {
    private boolean _open = true;
    public final Arena arena;
    private final GameRunner runner;
    public GameState getGameState(){
        return this.runner.getGameState();
    }
    private final ProfileInventoryManager profileInventoryManager;
    private final PlayersManager playersManager = new PlayersManager();

    public Game(Arena arena) throws GameNotRegisteredException {
        super();
        this.arena = arena;
        this.runner = new GameRunner(this);
        this.profileInventoryManager = new ProfileInventoryManager(this);
    }

    @Override
    public boolean isOpen() {
        return _open;
    }

    @Override
    public boolean isPlayer(Player player) {
        return playersManager.contains(player);
    }
    public boolean isFromGameLikePlayer(Player player){
        Profile profile = playersManager.getProfile(player);
        if(profile == null){
            return false;
        }
        return profile.type.isGamePlayer();
    }

    @Override
    public boolean quit(Player player) {
        return false;
    }

    public void stopGame(GameStopReason reason){

    }

    @Override
    protected void onClose() {

    }
}
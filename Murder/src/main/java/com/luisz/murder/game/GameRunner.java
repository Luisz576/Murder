package com.luisz.murder.game;

import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.Bukkit;

public class GameRunner implements Runnable{
    private final Game game;
    private GameState gameState = GameState.RECRUITING;
    public GameState getGameState(){
        return this.gameState;
    }
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    private int runId = -1;
    public GameRunner(Game game){
        this.game = game;
    }

    public void start(){
        if(runId == -1){
            runId = Bukkit.getScheduler().scheduleSyncRepeatingTask(
                MurderPluginManager.getPlugin(), this, 0, 20);
        }
    }

    public void stop(){
        if(runId != -1){
            Bukkit.getScheduler().cancelTask(runId);
            runId = -1;
        }
    }

    @Override
    public void run() {
        switch (game.getGameState()){
            case RECRUITING:
                break;
            case RUNNING:
                game.coinsManager._gameTick();
                break;
            case STOPPING:
                break;
        }
    }
}
package com.luisz.murder.game;

import com.luisz.murder.game.enums.GameState;

public class GameRunner implements Runnable{
    private final Game game;
    private GameState gameState = GameState.RECRUITING;
    public GameState getGameState(){
        return this.gameState;
    }
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    public int runId = -1;
    public GameRunner(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        //TODO
    }
}
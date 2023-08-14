package com.luisz.murder.game;

import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.enums.GameStopReason;
import com.luisz.murder.language.Texts;
import com.luisz.murder.language.TextsVar;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class GameRunner implements Runnable{
    private final Game game;
    protected int timer = 0;
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
        switch (this.gameState){
            case RECRUITING:
                if(timer <= 0){
                    game.runGame();
                    break;
                }
                if(game.getAmountOfPlayers(true) < game.arena.MIN_PLAYERS){
                    timer = game.TIME_TO_START_GAME;
                    break;
                }
                if(timer < 10){
                    List<Tuple<TextsVar, String>> vars = new ArrayList<>();
                    vars.add(new Tuple<>(TextsVar.FIRST_NUMBER, timer + ""));
                    game.sendGameMessageForEveryone(Texts.GAME_MESSAGE_GAME_START_IN, vars, ChatColor.GREEN.toString());
                }
                break;
            case RUNNING:
                game.coinsManager._gameTick();
                if(timer <= 0){
                    game.stopGame(GameStopReason.TIMEOUT);
                }
                break;
            case STOPPING:
                break;
        }
        updateScoreboard();
        timer--;
    }

    private void updateScoreboard(){
        game.scoreboardManager.data.time(timer);

        game.scoreboardManager.renderForEveryListener();
    }
}
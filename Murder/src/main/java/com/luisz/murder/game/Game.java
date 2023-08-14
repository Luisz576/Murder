package com.luisz.murder.game;

import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.lapi.game.IGame;
import com.luisz.lapi.game.exception.GameNotRegisteredException;
import com.luisz.lapi.game.manager.GamesManager;
import com.luisz.murder.arena.Arena;
import com.luisz.murder.exceptions.ErrorLoadingPlayerProfileException;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.enums.GameStopReason;
import com.luisz.murder.game.events.PlayerJoinTheGameEvent;
import com.luisz.murder.game.events.PlayerQuitTheGameEvent;
import com.luisz.murder.game.manager.*;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.game.scorerender.MurderScoreRender;
import com.luisz.murder.language.Texts;
import com.luisz.murder.language.TextsVar;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Game extends IGame {
    public final int TIME_TO_START_GAME = 30, TIME_TO_END_GAME = 60 * 10;

    private boolean _open = true;
    public final Arena arena;
    private final GameRunner runner;
    private final GameListener gameListener;
    public GameState getGameState(){
        return this.runner.getGameState();
    }
    public final ScoreboardManager scoreboardManager;
    protected final ProfileInventoryManager profileInventoryManager;
    private final PlayersManager playersManager;
    public int getAmountOfPlayers(boolean needBePlayer){
        return playersManager.getAmountOfPlayers(needBePlayer);
    }
    protected final CoinsManager coinsManager;
    private final PlayerSkinsManager playerSkinsManager;
    private final JobsManager jobsManager;
    private final TeleportManager teleportManager;

    public Game(Arena arena) throws GameNotRegisteredException {
        super();
        this.arena = arena;
        this.runner = new GameRunner(this);
        this.playersManager = new PlayersManager(this);
        this.scoreboardManager = new ScoreboardManager(new MurderScoreRender(), this.playersManager);
        this.playerSkinsManager = new PlayerSkinsManager(arena.getSkins(), this.playersManager);
        this.profileInventoryManager = new ProfileInventoryManager(this, this.playersManager);
        this.jobsManager = new JobsManager(this.playersManager);
        this.teleportManager = new TeleportManager(this.playersManager, this.arena.getSpawns());
        this.coinsManager = new CoinsManager(this, this.playersManager, arena.getCoinsSpawns());
        this.gameListener = new GameListener(this);
        MurderPluginManager.registerListener(gameListener);
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

    public boolean join(Player player){
        if(!GamesManager.getInstance().isPlayerInSomeGame(player)){
            try {
                Profile profile = playersManager.joinPlayer(player);
                Bukkit.getPluginManager().callEvent(new PlayerJoinTheGameEvent(this, profile));
                return true;
            }catch (ErrorLoadingPlayerProfileException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    // states
    protected void runGame(){
        this.runner.setGameState(GameState.RUNNING);
        this.runner.timer = TIME_TO_END_GAME;
        this.teleportManager.spawnEveryone();
        this.jobsManager.giveJobs();
        this.playerSkinsManager.giveRandomSkinsForEveryone();
        this.profileInventoryManager.giveItemsForEveryone();
        // TODO
    }

    @Override
    public boolean quit(Player player) {
        Profile profile = playersManager.quitPlayer(player);
        if(profile == null){
            return false;
        }
        Bukkit.getPluginManager().callEvent(new PlayerQuitTheGameEvent(this, profile));
        return true;
    }

    public void stopGame(GameStopReason reason){
        coinsManager.unspawnAll();
        MurderPluginManager.unregisterListener(gameListener);
    }

    @Override
    protected void onClose() {
        stopGame(GameStopReason.SYSTEM);
    }

    public void sendGameMessageForEveryone(Texts texts, List<Tuple<TextsVar, String>> vars, String pre){
        this.playersManager.sendMessageForEveryone(texts, vars, ChatColor.YELLOW + "[Murder] " + pre);
    }
}
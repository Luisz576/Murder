package com.luisz.murder.game.events;

import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.interfaces.IGameEvent;

public class PlayerDieEvent extends IGameEvent {//TODO
    public final Profile killer, killed;

    public PlayerDieEvent(Game game, Profile killer, Profile killed){
        super(game);
        this.killed = killed;
        this.killer = killer;
    }
}
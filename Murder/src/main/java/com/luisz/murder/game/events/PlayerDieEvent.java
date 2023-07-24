package com.luisz.murder.game.events;

import com.luisz.lapi.event.IEvent;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;

public class PlayerDieEvent extends IEvent {//TODO
    public final Game game;
    public final Profile killer, killed;

    public PlayerDieEvent(Game game, Profile killer, Profile killed){
        this.game = game;
        this.killed = killed;
        this.killer = killer;
    }
}
package com.luisz.murder.interfaces;

import com.luisz.lapi.event.IEvent;
import com.luisz.murder.game.Game;

public abstract class IGameEvent extends IEvent {
    public final Game game;

    public IGameEvent(Game game){
        this.game = game;
    }
}
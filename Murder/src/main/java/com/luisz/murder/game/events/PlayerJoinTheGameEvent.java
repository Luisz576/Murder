package com.luisz.murder.game.events;

import com.luisz.lapi.event.IEvent;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;

public class PlayerJoinTheGameEvent extends IEvent {
    public final Game game;
    public final Profile profile;

    public PlayerJoinTheGameEvent(Game game, Profile profile){
        this.profile = profile;
        this.game = game;
    }
}
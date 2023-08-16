package com.luisz.murder.game.events;

import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.interfaces.IGameEvent;

public class PlayerQuitTheGameEvent extends IGameEvent {
    public final Profile profile;

    public PlayerQuitTheGameEvent(Game game, Profile profile){
        super(game);
        this.profile = profile;
    }
}
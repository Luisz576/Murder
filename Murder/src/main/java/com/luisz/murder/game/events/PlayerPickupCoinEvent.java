package com.luisz.murder.game.events;

import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.interfaces.IGameEvent;

public class PlayerPickupCoinEvent extends IGameEvent {
    public final Profile who;

    public PlayerPickupCoinEvent(Game game, Profile profile){
        super(game);
        this.who = profile;
    }
}
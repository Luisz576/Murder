package com.luisz.murder.game.events;

import com.luisz.lapi.event.IEvent;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.profile.Profile;

public class PlayerPickupCoinEvent extends IEvent {
    public final Profile who;
    public final Game game;

    public PlayerPickupCoinEvent(Game game, Profile profile){
        this.game = game;
        this.who = profile;
    }
}
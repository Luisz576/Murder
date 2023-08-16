package com.luisz.murder.game.profile;

import com.luisz.murder.game.enums.GamePlayerType;

public class MurderStatistics {
    public final GamePlayerType playerType;
    public final int collected_coins, kills;

    public MurderStatistics(GamePlayerType playerType, int collected_coins, int kills){
        this.playerType = playerType;
        this.collected_coins = collected_coins;
        this.kills = kills;
    }
}
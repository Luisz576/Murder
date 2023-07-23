package com.luisz.murder.game.profile;

import com.luisz.lapi.player.skin.Skin;
import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.enums.GamePlayerType;
import org.bukkit.entity.Player;

public class Profile {
    public final Player player;
    public GamePlayerType type = GamePlayerType.NONE;
    public void _setType(GamePlayerType type){
        this.type = type;
    }
    private int kills = 0;
    public void addKill(){
        this.kills++;
    }
    private int coins = 0;
    public void addCoin(){
        this.coins++;
    }

    private SkinData skin;
    public SkinData getSkin(){
        return this.skin;
    }
    public void updateSkin(SkinData skin){
        this.skin = skin;
        _updateSkin();
    }
    private void _updateSkin(){
        Skin s = Skin.fromName(this.skin.skin);
        if(s == null){
            return;
        }
        s.apply(this.player);
    }

    public Profile(Player player){
        this.player = player;
    }
}
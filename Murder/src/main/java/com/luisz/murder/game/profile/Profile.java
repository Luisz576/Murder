package com.luisz.murder.game.profile;

import com.luisz.lapi.common.language.Language;
import com.luisz.lapi.player.skin.Skin;
import com.luisz.luisz576api.domain.playerprofile.PlayerProfile;
import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.enums.GamePlayerType;
import org.bukkit.entity.Player;

public class Profile {
    public final Player player;
    private final PlayerProfile playerProfile;
    public GamePlayerType type;
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

    public Profile(Player player, PlayerProfile playerProfile, boolean isSpectator){
        this.player = player;
        this.playerProfile = playerProfile;
        this.type = isSpectator ? GamePlayerType.SPECTATOR : GamePlayerType.NONE;
    }

    public Language getLanguage(){
        return this.playerProfile.language.getLikeLanguage();
    }
    public void sendMessage(String message){
        this.player.sendMessage(message);
    }
}
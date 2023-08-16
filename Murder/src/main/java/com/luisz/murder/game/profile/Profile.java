package com.luisz.murder.game.profile;

import com.luisz.lapi.common.language.Language;
import com.luisz.lapi.nms.NMS;
import com.luisz.lapi.player.skin.Skin;
import com.luisz.luisz576api.domain.playerprofile.PlayerProfile;
import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.enums.GamePlayerType;
import com.luisz.murder.manager.MurderPluginManager;
import org.bukkit.ChatColor;
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
    private boolean isAlive = true;
    public boolean isAlive(){
        return this.isAlive;
    }
    public void kill(){
        isAlive = false;
    }

    private SkinData skin = null;
    public SkinData getSkinData(){
        return this.skin;
    }
    public String getFakeName(){
        if(skin == null){
            return null;
        }
        return this.skin.nickname;
    }
    public void clearSkin(){
        this.skin = null;
        Skin s = Skin.fromName(this.playerProfile.skin);
        if(s == null){
            MurderPluginManager.sendPluginConsoleMessage(ChatColor.RED + "Player '" + this.player.getName() + "' hasn't a skin!");
            return;
        }
        s.apply(this.player);
        _updateFakenickname();
    }
    public void updateSkin(SkinData skin){
        this.skin = skin;
        _updateSkin();
        _updateFakenickname();
    }
    private void _updateSkin(){
        Skin s = Skin.fromName(this.skin.skin);
        if(s == null){
            return;
        }
        s.apply(this.player);
        this.player.setDisplayName(this.skin.nickname);
    }
    private void _updateFakenickname(){
        // TODO
    }

    public MurderStatistics createMurderStatistics(){
        return new MurderStatistics(this.coins, this.kills);
    }

    public Profile(Player player, PlayerProfile playerProfile, boolean isSpectator){
        this.player = player;
        this.playerProfile = playerProfile;
        this.type = isSpectator ? GamePlayerType.SPECTATOR : GamePlayerType.NONE;
    }

    public Language getLanguage(){
        return this.playerProfile.language;
    }
    public void sendTitle(String title){
        NMS.getInstance().sendTitle(this.player, title);
    }
    public void sendMessage(String message){
        this.player.sendMessage(message);
    }
}
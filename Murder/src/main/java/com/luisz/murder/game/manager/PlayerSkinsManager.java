package com.luisz.murder.game.manager;

import com.luisz.lapi.common.random.LRandom;
import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.profile.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerSkinsManager {
    private final List<SkinData> skins = new ArrayList<>();
    private final PlayersManager playersManager;

    public PlayerSkinsManager(List<SkinData> skins, PlayersManager playersManager){
        this.playersManager = playersManager;
        this.skins.addAll(skins);
    }

    public void giveRandomSkinsForEveryone(){
        Collection<Profile> profiles = playersManager.getAllProfiles();
        List<SkinData> skinsToGive = new ArrayList<>(this.skins);
        for(Profile profile : profiles){
            if(profile.type.isGamePlayer()) {
                SkinData s = skinsToGive.get(LRandom.randomInt(skinsToGive.size()));
                profile.updateSkin(s);
                skinsToGive.remove(s);
            }
        }
    }

    public void changeSkinOfPlayerTo(Profile who, SkinData skinData){
        who.updateSkin(skinData);
    }
}
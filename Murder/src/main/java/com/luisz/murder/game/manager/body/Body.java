package com.luisz.murder.game.manager.body;

import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.profile.Profile;

public class Body {//TODO
    private SkinData skinData;
//    private final Location location;

    public Body(Profile profile){
        this.skinData = profile.getSkinData();
        spawn();
        updateSkin(this.skinData);
    }

    private void spawn(){
        //TODO
    }

    public void updateSkin(SkinData skinData){
        this.skinData = skinData;
        //TODO
    }

    public SkinData getSkinData(){
        return this.skinData;
    }
}
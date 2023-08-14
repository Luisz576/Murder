package com.luisz.murder.game.body;

import com.luisz.murder.game.data.SkinData;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.interfaces.IEntity;

public class Body implements IEntity {//TODO
    private SkinData skinData;
    private boolean spawned = false;
//    private final Location location;

    public boolean isSpawned(){
        return this.spawned;
    }

    public Body(Profile profile){
        this.skinData = profile.getSkinData();
    }

    @Override
    public void spawn(){
        if(spawned){
            return;
        }
        spawned = true;
        //TODO
    }

    @Override
    public void remove(){
        if(!spawned){
            return;
        }
        spawned = false;
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
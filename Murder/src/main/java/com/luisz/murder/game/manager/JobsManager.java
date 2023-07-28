package com.luisz.murder.game.manager;

import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.language.Texts;

import java.util.Collection;

public class JobsManager {
    private final PlayersManager playersManager;

    public JobsManager(PlayersManager playersManager){
        this.playersManager = playersManager;
    }

    public void giveJobs(){
        Collection<Profile> profiles = playersManager.getAllProfiles();
        for(Profile profile : profiles){
            // TODO give job
            sendTitle(profile);
        }
    }

    private void sendTitle(Profile profile){
        switch (profile.type){
            case ASSASSIN:
                profile.sendTitle(profile.type.getColor() + Texts.ASSASSIN.getString(profile.getLanguage()));
                break;
            case DETECTIVE:
                profile.sendTitle(profile.type.getColor() + Texts.DETECTIVE.getString(profile.getLanguage()));
                break;
            case VICTIM:
                profile.sendTitle(profile.type.getColor() + Texts.VICTIM.getString(profile.getLanguage()));
                break;
        }
    }
}
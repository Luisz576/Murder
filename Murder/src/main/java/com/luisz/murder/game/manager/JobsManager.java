package com.luisz.murder.game.manager;

import com.luisz.lapi.common.random.LRandom;
import com.luisz.murder.game.enums.GamePlayerType;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.language.Texts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobsManager {
    private final PlayersManager playersManager;

    public JobsManager(PlayersManager playersManager){
        this.playersManager = playersManager;
    }

    public void giveJobs(){
        Collection<Profile> profiles = playersManager.getAllProfiles();
        List<GamePlayerType> jobs = new ArrayList<>();
        jobs.add(GamePlayerType.DETECTIVE);
        jobs.add(GamePlayerType.ASSASSIN);
        for(int i = 0; i < profiles.size() - 2; i++){
            jobs.add(GamePlayerType.VICTIM);
        }
        for(Profile profile : profiles){
            GamePlayerType j = jobs.get(LRandom.randomInt(jobs.size()));
            jobs.remove(j);
            profile._setType(j);
            sendJobTitle(profile);
        }
    }

    private void sendJobTitle(Profile profile){
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
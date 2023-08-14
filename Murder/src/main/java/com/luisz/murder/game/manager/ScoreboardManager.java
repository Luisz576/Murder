package com.luisz.murder.game.manager;

import com.luisz.murder.game.data.ScoreboardData;
import com.luisz.murder.game.scorerender.ScoreRender;
import com.luisz.murder.game.profile.Profile;

public class ScoreboardManager {
    private final PlayersManager playersManager;
    private final ScoreRender render;
    public final ScoreboardData data = new ScoreboardData();

    public ScoreboardManager(ScoreRender render, PlayersManager playersManager){
        this.render = render;
        this.playersManager = playersManager;
    }

    public void renderForEveryListener(){
        for(Profile profile : playersManager.getAllProfiles()){
            render.render(data, profile);
        }
    }
}
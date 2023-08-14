package com.luisz.murder.game.scorerender;

import com.luisz.murder.game.data.ScoreboardData;
import com.luisz.murder.game.profile.Profile;

public interface ScoreRender {
    void render(ScoreboardData data, Profile profile);
}
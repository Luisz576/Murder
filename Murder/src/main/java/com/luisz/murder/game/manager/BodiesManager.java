package com.luisz.murder.game.manager;

import com.luisz.murder.game.body.Body;
import com.luisz.murder.game.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class BodiesManager {
    // TODO
    private List<Body> bodies = new ArrayList<>();

    public Body spawn(Profile profile){
        Body body = new Body(profile);
        this.bodies.add(body);
        return body;
    }

    public Body getBodyAt() {//TODO
        return null;
    }
}
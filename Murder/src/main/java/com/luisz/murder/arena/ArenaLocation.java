package com.luisz.murder.arena;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;

public class ArenaLocation {
    public final UnmodifiableVector3D loc;
    public final String id;

    public ArenaLocation(String id, UnmodifiableVector3D loc){
        this.loc = loc;
        this.id = id;
    }
}
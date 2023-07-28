package com.luisz.murder.arena;

import com.luisz.lapi.common.math.vector.UnmodifiableVector3D;
import com.luisz.lapi.nms.NMS;
import org.bukkit.Location;
import org.bukkit.World;

public class ArenaLocation {
    public final UnmodifiableVector3D loc;
    public final String id, world;

    public ArenaLocation(String id, String world, UnmodifiableVector3D loc){
        this.loc = loc;
        this.world = world;
        this.id = id;
    }

    public Location getLocation(){
        World world = NMS.getInstance().getWorldByName(this.world);
        return new Location(world, loc.x, loc.y, loc.z);
    }
}
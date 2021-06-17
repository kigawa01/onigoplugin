package net.kigawa.utilplugin.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;

public class GetEntity {
    public Entity getNearest(Location location){
        List<Entity> entityList=location.getWorld().getEntities();
        double distance=-1;
        double temp=0;
        Entity nearest = null;
        for(Entity entity:entityList){
            temp=location.distance(entity.getLocation());
            if ((distance<0) || (temp<distance)){
                distance=temp;
                nearest=entity;
            }
        }
        return nearest;
    }
}

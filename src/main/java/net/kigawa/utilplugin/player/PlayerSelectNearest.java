package net.kigawa.utilplugin.player;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;

public class PlayerSelectNearest {

    Iterator<Player> it;
    Location location;
    public PlayerSelectNearest(Location location){
        List<Player> players=location.getWorld().getPlayers();
        Iterator<Player> it=players.iterator();
        this.location=location;
    }
    public Player getNearestPlayer(){
        Player nearestPlayer=null;
        Player player;
        while (it.hasNext()){
            player= it.next();
            if(nearestPlayer==null){
                nearestPlayer=player;
            }else {
                if(location.distance(player.getLocation())>location.distance(nearestPlayer.getLocation())) {
                    nearestPlayer=player;
                }
            }
        }
        return nearestPlayer;
    }
}

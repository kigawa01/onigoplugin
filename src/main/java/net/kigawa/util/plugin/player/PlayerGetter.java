package net.kigawa.util.plugin.player;

import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGetter {
    KigawaPlugin plugin;
    public PlayerGetter(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
    }
    public List<Player> room(String world,int x,int y,int z,int x1,int y1,int z1){
        int sx;
        int bx;
        int sy;
        int by;
        int sz;
        int bz;
        if (x>x1){
            sx=x1;
            bx=x+1;
        }else{
            sx=x;
            bx=x1+1;
        }
        if (x>x1){
            sy=y1;
            by=y+1;
        }else{
            sy=y;
            by=y1+1;
        }
        if (x>x1){
            sz=z1;
            bz=z+1;
        }else{
            sz=z;
            bz=z1+1;
        }
        World world1=plugin.getServer().getWorld(world);
        Location location;
        List<Player> allPlayers=world1.getPlayers();
        List<Player> inRoom=new ArrayList<>();
        Player player;
        boolean isIn;
        for (int i = 0;i<allPlayers.size();i++){
            isIn=false;
            player=allPlayers.get(i);
            location=player.getLocation();
            if (location.getX()>sx&&location.getY()>sy&&location.getZ()>sz){
                isIn=location.getX()<bx&&location.getY()<by&&location.getZ()<bz;
            }
            if (isIn){
                inRoom.add(player);
            }
        }
        return inRoom;
    }
}

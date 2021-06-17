package net.kigawa.utilplugin.data;


import net.kigawa.utilplugin.player.PlayerSelectNearest;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RegionData {
    double startX;
    double startY;
    double startZ;
    double endX;
    double endY;
    double endZ;
    Location center;
    PlayerSelectNearest nearest;
    public RegionData(Location location, Location location1){
        startX=location.getX();
        endX=location1.getX();
        if(startX>endX){
            double temp;
            temp=startX;
            startX=endX;
            endX=temp;
        }else {endX++;}
        startY=location.getY();
        endY=location1.getY();
        if(startY>endY){
            double temp;
            temp=startY;
            startY=endY;
            endY=temp;
        }else {endZ++;}
        startZ=location.getZ();
        endZ=location1.getZ();
        if(startZ>endZ){
            double temp;
            temp=startZ;
            startZ=endZ;
            endZ=temp;
        }else {endZ++;}
        center=new Location(location.getWorld(),startX+endX/2,startY+endY/2,startZ+endZ/2);
        nearest=new PlayerSelectNearest(center);
    }
    public Player isPlayer(){
        Player player=nearest.getNearestPlayer();
        Location location=player.getLocation();
        double x=location.getX();
        double y=location.getY();
        double z=location.getZ();
        Player returnPlayer=null;
        if(startX<=x&&x<=endX){
            if(startY<=y&&y<=endY){
                if(startZ<=z&&z<=endZ){
                    returnPlayer=player;
                }
            }
        }
        return returnPlayer;
    }
}

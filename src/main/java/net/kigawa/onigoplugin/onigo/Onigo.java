package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Onigo {
    OnigoData d;
    KigawaPlugin plugin;
    public Onigo(KigawaPlugin kigawaPlugin,OnigoData onigoData){
        plugin=kigawaPlugin;
        d=onigoData;
    }
    public void start(CommandSender sender){
        if (d.waitRoomWorld!=null) {
            List<Player> playerGetter = plugin.getPlayerGetter().room(d.getWaitRoomWorld(), d.getWaitRoom()[0], d.getWaitRoom()[1], d.getWaitRoom()[2],
                    d.getWaitRoom()[3], d.getWaitRoom()[4], d.getWaitRoom()[5]);
        }else {
            sender.sendMessage("need waiting room");
        }
    }
    public void setWaitingRoom1(String world,int x,int y,int z){
        int [] loc=d.getWaitRoom();
        loc[0]=x;
        loc[1]=y;
        loc[2]=z;
        d.setWaitRoom(loc);
        d.setWaitRoomWorld(world);
        plugin.getRecorder().save(d);
    }
    public void setWaitingRoom2(int x,int y,int z){
        int [] loc=d.getWaitRoom();
        loc[3]=x;
        loc[4]=y;
        loc[5]=z;
        d.setWaitRoom(loc);
        plugin.getRecorder().save(d);
    }
    public void start(){
        start(plugin.getServer().getConsoleSender());
    }
    public void end(){

    }
    public void exit(){

    }
    public String getName(){
        return d.getName();
    }

}

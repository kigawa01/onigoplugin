package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.stage.StageData;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Onigo {
    OnigoData d;
    KigawaPlugin plugin;
    public Onigo(KigawaPlugin kigawaPlugin,OnigoData onigoData){
        plugin=kigawaPlugin;
        d=onigoData;
    }
    public void end(){

    }
    public void exit(){

    }
    public void start(CommandSender sender){
        if (d.waitRoomWorld!=null) {
            //sort oni
            List<Player> joinPlayer = plugin.getPlayerGetter().room(d.getWaitRoomWorld(), d.getWaitRoom()[0], d.getWaitRoom()[1], d.getWaitRoom()[2],
                    d.getWaitRoom()[3], d.getWaitRoom()[4], d.getWaitRoom()[5]);
            Random random=new Random();
            List<Player> oniPlayer=new ArrayList<>();
            List<Player> runPlayer=new ArrayList<>();
            runPlayer.addAll(joinPlayer);
            int randomNumber;
            if (oniPlayer.size()>d.getOniCount()) {
                for (int i = 0; i < d.getOniCount(); i++) {
                    randomNumber=random.nextInt(runPlayer.size());
                    oniPlayer.add(joinPlayer.get(randomNumber));
                    runPlayer.remove(randomNumber);
                }
                //teleport runner
                StageData stageData=plugin.getStageManager().getRandomStage();
                if (stageData!=null) {
                    for (int i = 0; i < runPlayer.size(); i++) {
                        //runPlayer.get(i).teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld())));
                    }
                }else {
                    sender.sendMessage("stage is not exit");
                }
            }else {
                sender.sendMessage("players is not enough");
            }
        }else {
            sender.sendMessage("need waiting room");
        }
    }
    public void setOniCount(int oniCount){
        d.setOniCount(oniCount);
        plugin.getRecorder().save(d);
    }

    public OnigoData getD() {
        return d;
    }

    public void setWaitingRoom1(String world, int x, int y, int z){
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

    public String getName(){
        return d.getName();
    }

}

package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.stage.StageData;
import net.kigawa.util.timer.Counter;
import net.kigawa.util.yaml.YamlData;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Onigo implements YamlData {
    OnigoData d;
    KigawaPlugin plugin;
    public Onigo(KigawaPlugin kigawaPlugin,OnigoData onigoData){
        plugin=kigawaPlugin;
        d=onigoData;
        counter=new Counter(plugin);
    }
    public void end(){

    }
    public void exit(){

    }
    Counter counter;
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
            plugin.logger("join player"+joinPlayer.size());
            if (joinPlayer.size()>d.getOniCount()) {
                for (int i = 0; i < d.getOniCount(); i++) {
                    randomNumber=random.nextInt(runPlayer.size()-1);
                    oniPlayer.add(runPlayer.get(randomNumber));
                    runPlayer.remove(randomNumber);
                }
                //teleport runner
                StageData stageData=plugin.getStageManager().getRandomStage();
                if (stageData!=null) {
                    for (Player player : runPlayer) {
                        player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0]),
                                Integer.valueOf(stageData.getStartLoc()[1]), Integer.valueOf(stageData.getStartLoc()[2])));
                    }
                    //teleport oni
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for (Player player:oniPlayer){
                                player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0]),
                                        Integer.valueOf(stageData.getStartLoc()[1]), Integer.valueOf(stageData.getStartLoc()[2])));
                            }
                        }
                    }.runTaskLater(plugin,d.getWaitTime()*20);
                    counter.startSec("鬼ごっこ","onigo",0L,d.getWaitTime(),3,joinPlayer, Color.GREEN +"START",Color.GREEN,Color.GREEN);
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                        end();
                        }
                    }.runTaskLater(plugin,d.getGameTime()*20*60);
                }else {
                    sender.sendMessage("stage is not exit");
                }
            }else {
                sender.sendMessage("player is not enough");
            }
        }else {
            sender.sendMessage("need waiting room");
        }
    }
    public void setGameTime(int gameTime){
        d.setGameTime(gameTime);
        plugin.getRecorder().save(d);
    }
    public void setWaitTime(int waitTime){
        d.setWaitTime(waitTime);
        plugin.getRecorder().save(d);
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
        d.setWaitRoomWorld(world);
        plugin.getRecorder().save(d);
    }
    public void setWaitingRoom2(int x,int y,int z){
        int [] loc=d.getWaitRoom();
        loc[3]=x;
        loc[4]=y;
        loc[5]=z;
        plugin.getRecorder().save(d);
    }
    public void start(){
        start(plugin.getServer().getConsoleSender());
    }

    public String getName(){
        return d.getName();
    }

}

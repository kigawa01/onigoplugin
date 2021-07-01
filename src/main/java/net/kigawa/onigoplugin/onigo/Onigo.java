package net.kigawa.onigoplugin.onigo;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.stage.Limiter;
import net.kigawa.util.plugin.stage.StageData;
import net.kigawa.util.plugin.timer.Counter;
import net.kigawa.util.yaml.YamlData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Onigo implements YamlData {
    OnigoData d;
    KigawaPlugin plugin;
    Onigo onigo=this;
    public Onigo(KigawaPlugin kigawaPlugin,OnigoData onigoData){
        plugin=kigawaPlugin;
        d=onigoData;
    }
    public boolean changeOni(Player oni,Player runner){
        if (oniPlayer.contains(oni)){
            if (runPlayer.contains(runner)){
                oniPlayer.add(runner);
                runPlayer.remove(runner);
                runPlayer.add(oni);
                oniPlayer.remove(oni);
                oni.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,102,2));
                oni.sendTitle(ChatColor.GREEN+"鬼を交代しました","",5,15,5);
                runner.sendTitle(ChatColor.RED+"鬼になりました","",5,15,5);
                plugin.getMessenger().sendMessage(joinPlayer,ChatColor.GREEN+"鬼が変わりました");
                plugin.getMessenger().sendMessage(joinPlayer,ChatColor.BLUE+oni.getName()+ChatColor.WHITE+"→"+ChatColor.BLUE+runner.getName());
            }
        }
        return false;
    }

    public void exit(){
    }
    List<Player> joinPlayer;
    List<Player> oniPlayer;
    List<Player> runPlayer;
    StageData stageData;
    Limiter limiter;
    Limiter limiter1;
    Counter counter;
    Counter counter1;
    BukkitTask runnable;
    BukkitTask runnable1;
    public void end(){
        //send oni name
        plugin.getMessenger().sendMessage(joinPlayer,ChatColor.GREEN+"最後に鬼だったプレーヤー");
        for (Player player:oniPlayer){
            plugin.getMessenger().sendMessage(joinPlayer,":"+ChatColor.BLUE+player.getName());
        }
        //teleport players
        plugin.getTeleporter().teleportPlayers(joinPlayer,new Location(plugin.getServer().getWorld(d.getEndWorld()),d.getEndLoc()[0]+0.5,d.getEndLoc()[1]+0.5,d.getEndLoc()[2]+0.5));
        //return stage
        plugin.getStageManager().returnStage(stageData);
        //cancel counter
        counter.cancel();
        counter1.cancel();
        //cancel limiter
        limiter.cancel();
        limiter1.cancel();
        //cancel runnable
        runnable.cancel();
        runnable1.cancel();
        //remove in list
        while (0<joinPlayer.size()){
            joinPlayer.remove(0);
        }
        while (0<oniPlayer.size()){
            oniPlayer.remove(0);
        }
        while (0<runPlayer.size()){
            runPlayer.remove(0);
        }
    }
    public void start(CommandSender sender){
        if (d.waitRoomWorld!=null) {
            //sort oni
            joinPlayer = plugin.getPlayerGetter().room(d.getWaitRoomWorld(), d.getWaitRoom()[0], d.getWaitRoom()[1], d.getWaitRoom()[2],
                    d.getWaitRoom()[3], d.getWaitRoom()[4], d.getWaitRoom()[5]);
            Random random=new Random();
            oniPlayer=new ArrayList<>();
            runPlayer=new ArrayList<>();
            runPlayer.addAll(joinPlayer);
            int randomNumber;
            plugin.logger("join player"+joinPlayer.size());
            if (joinPlayer.size()>d.getOniCount()) {
                for (int i = 0; i < d.getOniCount(); i++) {
                    randomNumber=random.nextInt(runPlayer.size());
                    oniPlayer.add(runPlayer.get(randomNumber));
                    runPlayer.remove(randomNumber);
                }
                //teleport runner
                stageData=plugin.getStageManager().getRandomStage();
                if (stageData!=null) {
                    for (Player player : runPlayer) {
                        player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0])+0.5,
                                Integer.valueOf(stageData.getStartLoc()[1])+0.5, Integer.valueOf(stageData.getStartLoc()[2])+0.5));
                    }
                    //limiter
                    limiter=new Limiter(plugin,runPlayer,stageData);
                    //counter
                    counter=new Counter("鬼ごっこ","onigo",plugin);
                    counter.startSec(0L,d.getWaitTime(),3,joinPlayer, ChatColor.GREEN +"START",ChatColor.GREEN);
                    //count wait time
                    runnable=new BukkitRunnable() {
                        @Override
                        public void run() {
                            //send message
                            plugin.getMessenger().sendMessage(joinPlayer,ChatColor.GREEN+"最初に鬼のプレーヤー");
                            //teleport oni
                            for (Player player:oniPlayer){
                                player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0])+0.5,
                                        Integer.valueOf(stageData.getStartLoc()[1])+0.5, Integer.valueOf(stageData.getStartLoc()[2])+0.5));
                                plugin.getMessenger().sendMessage(joinPlayer,ChatColor.BLUE+player.getName());
                            }
                            //cancel limiter
                            limiter.cancel();
                            //limiter
                            limiter1=new OnigoLimiter(plugin,stageData,onigo);
                            //counter
                            counter.cancel();
                            counter1=new Counter("鬼ごっこ","onigo",plugin);
                            counter1.startMin(0L,d.getGameTime(),3,joinPlayer, ChatColor.RED +"END",ChatColor.GREEN);
                            //end
                            runnable1=new BukkitRunnable(){
                                @Override
                                public void run() {
                                    end();
                                }
                            }.runTaskLater(plugin,d.getGameTime()*20*60);
                        }
                    }.runTaskLater(plugin,d.getWaitTime()*20);
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

    public List<Player> getOniPlayer() {
        return oniPlayer;
    }

    public List<Player> getJoinPlayer(){
        return joinPlayer;
    }
    public void setEndLoc(String world, int x, int y, int z){
        int [] loc=d.getEndLoc();
        loc[0]=x;
        loc[1]=y;
        loc[2]=z;
        d.setEndWorld(world);
        plugin.getRecorder().save(d);
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
    public String getName(){
        return d.getName();
    }
}
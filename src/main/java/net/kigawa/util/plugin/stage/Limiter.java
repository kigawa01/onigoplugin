package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Limiter extends BukkitRunnable {
    KigawaPlugin plugin;
    List<Player> players;
    int[] stageLoc=new int[6];
    public Limiter(KigawaPlugin kigawaPlugin, List<Player> players,StageData stageData){
        plugin=kigawaPlugin;
        this.players=players;
        int[] s=stageData.getStageLoc();
        if (s[0]>s[3]){
            stageLoc[0]=s[3];
            stageLoc[3]=s[0];
        }else{
            stageLoc[0]=s[0];
            stageLoc[3]=s[3];
        }
        if (s[1]>s[4]){
            stageLoc[1]=s[4];
            stageLoc[4]=s[1];
        }else{
            stageLoc[1]=s[1];
            stageLoc[4]=s[4];
        }
        if (s[2]>s[5]){
            stageLoc[2]=s[5];
            stageLoc[5]=s[2];
        }else{
            stageLoc[2]=s[2];
            stageLoc[5]=s[5];
        }
        if (stageLoc[0]<0)stageLoc[0]--;
        if (stageLoc[1]<0)stageLoc[1]--;
        if (stageLoc[2]<0)stageLoc[2]--;
        if (stageLoc[3]>0)stageLoc[3]++;
        if (stageLoc[4]>0)stageLoc[4]++;
        if (stageLoc[5]>0)stageLoc[5]++;
        runTaskTimer(plugin,0,2);
    }
    public void onRun(){
    }
    @Override
    public void run() {
        onRun();
        for (Player player:players){
            Location loc=player.getLocation();
            double[] l=new double[3];
            l[0]=loc.getX();
            l[1]=loc.getY();
            l[2]=loc.getZ();
            if (l[0]<stageLoc[0]){
                player.teleport(new Location(loc.getWorld(),stageLoc[0],l[1],l[2]));
                player.sendMessage("ステージから出ることはできません");
            }
            if (l[0]>stageLoc[3]){
                player.teleport(new Location(loc.getWorld(),stageLoc[3],l[1],l[2]));
                player.sendMessage("ステージから出ることはできません");
            }
            if (l[1]<stageLoc[1]){
                player.teleport(new Location(loc.getWorld(),l[0],stageLoc[1],l[2]));
                player.sendMessage("ステージから出ることはできません");
            }
            if (l[1]>stageLoc[4]){
                player.teleport(new Location(loc.getWorld(),l[0],stageLoc[4],l[2]));
                player.sendMessage("ステージから出ることはできません");
            }
            if (l[2]<stageLoc[2]){
                player.teleport(new Location(loc.getWorld(),l[0],l[1],stageLoc[2]));
                player.sendMessage("ステージから出ることはできません");
            }
            if (l[2]>stageLoc[5]){
                player.teleport(new Location(loc.getWorld(),l[0],l[1],stageLoc[5]));
                player.sendMessage("ステージから出ることはできません");
            }
        }
    }
}

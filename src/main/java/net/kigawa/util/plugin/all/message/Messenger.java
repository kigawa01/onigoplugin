package net.kigawa.util.plugin.all.message;

import net.kigawa.util.plugin.all.KigawaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Messenger {
    KigawaPlugin plugin;
    public Messenger(KigawaPlugin plugin){
        this.plugin=plugin;
    }
    public void sendTitle(List<Player> players,String title,String subTitle){
        for (Player player:players){
            player.sendTitle(title,subTitle,10,20,10);
        }
    }
    public void sendTitle(List<Player> players,String title){
        for (Player player:players){
            player.sendTitle(title,"",10,20,10);
        }
    }
    public void sendMessage(List<Player> players,String message){
        for (Player player: players){
            player.sendMessage(message);
        }
    }
    public  void sendTitleLater(List<Player> players,String title,Long delay){
        new BukkitRunnable() {
            @Override
            public void run() {
                sendTitle(players,title);
            }
        }.runTaskLater(plugin,delay);
    }
}

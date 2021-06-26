package net.kigawa.util.plugin.player;

import org.bukkit.entity.Player;

import java.util.List;

public class Messenger {
    public void sendTitle(List<Player> players,String title,String subTitle){
        for (Player player:players){
            player.sendTitle(title,subTitle,10,20,10);
        }
    }
}

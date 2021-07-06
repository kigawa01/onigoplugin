package net.kigawa.onigoplugin.game.onigo;

import net.kigawa.util.plugin.timer.Counter;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class OnigoCounter extends Counter {
    OnigoGame onigo;
    public OnigoCounter(String bordName, String bordID, OnigoGame onigo) {
        super(bordName, bordID, onigo.getPlugin());
        //replace variable
        this.onigo=onigo;

        //start count
        startMin(0L,onigo.getD().getGameTime(),3,onigo.getJoinPlayer(), ChatColor.RED +"END",ChatColor.GREEN);
    }
    @Override
    public void sendLastMessage(){
        //send end
        onigo.getPlugin().getMessenger().sendTitle(onigo.getJoinPlayer(),ChatColor.RED+"END","");
        //send winner and loser
        new BukkitRunnable() {
            @Override
            public void run() {
                //send lose
                onigo.getPlugin().getMessenger().sendTitle(onigo.getOniPlayer(),ChatColor.RED+"LOSE","");
                //send win
                onigo.getPlugin().getMessenger().sendTitle(onigo.getRunPlayer(),ChatColor.RED+"WIN","");
            }
        }.runTaskLater(onigo.getPlugin(), 40);
    }
}

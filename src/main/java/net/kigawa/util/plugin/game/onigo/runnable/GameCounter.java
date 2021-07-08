package net.kigawa.util.plugin.game.onigo.runnable;

import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.all.timer.Counter;
import org.bukkit.ChatColor;

public class GameCounter extends Counter {
    OnigoGame onigo;
    public GameCounter(String bordName, String bordID, OnigoGame onigo) {
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
    }
}

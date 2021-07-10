package net.kigawa.util.plugin.game.onigo.runnable;

import net.kigawa.util.plugin.all.timer.Counter;
import net.kigawa.util.plugin.game.onigo.Game;
import org.bukkit.ChatColor;

public class GameCounter extends Counter {
    Game onigo;

    public GameCounter(String bordName, String bordID, Game onigo) {
        super(bordName, bordID, onigo.getPlugin());
        //replace variable
        this.onigo = onigo;

        //start count
        startMin(0L, onigo.getD().getGameTime(), 3, onigo.getJoinPlayer(), ChatColor.RED + "END", ChatColor.GREEN);
    }

    @Override
    public void sendLastMessage() {
        //send end
        onigo.getPlugin().getMessenger().sendTitle(onigo.getJoinPlayer(), ChatColor.RED + "END", "");
    }
}

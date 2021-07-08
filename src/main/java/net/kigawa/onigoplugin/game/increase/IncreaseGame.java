package net.kigawa.onigoplugin.game.increase;

import net.kigawa.onigoplugin.game.change.OnigoData;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class IncreaseGame extends Game {
    public IncreaseGame(KigawaPlugin kigawaPlugin, IncreaseData increaseData, GameManager manager) {
        super(kigawaPlugin, increaseData,manager);
    }

    @Override
    public String getBordName() {
        return "増え鬼";
    }

    @Override
    public boolean changeOni(Player oni, Player runner) {
        //check player
        if (getOniPlayer().contains(oni)) {
            if (getRunPlayer().contains(runner)) {
                //change list
                getOniPlayer().add(runner);
                getRunPlayer().remove(runner);
                //send title
                oni.sendTitle("逃走者を捕まえました","",5,15,5);
                runner.sendTitle("鬼になりました","",5,15,5);
                //send message
                getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が増えました");
                getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.BLUE + oni.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + runner.getName());
            }
        }
        return true;
    }

    @Override
    public void sendEndMessage() {
        //send oni name
        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "最後に鬼だったプレーヤー");
        for (Player player : getOniPlayer()) {
            getPlugin().getMessenger().sendMessage(getJoinPlayer(), ":" + ChatColor.BLUE + player.getName());
        }
        //send who win
        if (getRunPlayer().size()==0){
            getPlugin().getMessenger().sendMessage(getJoinPlayer(),"鬼の勝利");
        }else {
            getPlugin().getMessenger().sendMessage(getJoinPlayer(),"逃走者の勝利");
        }
    }
}

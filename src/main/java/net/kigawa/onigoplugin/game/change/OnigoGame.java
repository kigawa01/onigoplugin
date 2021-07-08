package net.kigawa.onigoplugin.game.change;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class OnigoGame extends Game {

    public OnigoGame(KigawaPlugin kigawaPlugin, OnigoData onigoData, GameManager manager) {
        super(kigawaPlugin, onigoData,manager);
    }

    @Override
    public String getBordName() {
        return "鬼ごっこ";
    }

    @Override
    public boolean changeOni(Player oni, Player runner) {
        if (getOniPlayer().contains(oni)) {
            if (getRunPlayer().contains(runner)) {
                //change list
                getOniPlayer().add(runner);
                getRunPlayer().remove(runner);
                getRunPlayer().add(oni);
                getOniPlayer().remove(oni);
                //oni to runner
                oni.sendTitle(ChatColor.GREEN + "鬼を交代しました", "", 5, 15, 5);
                //runner to oni
                runner.sendTitle(ChatColor.RED + "鬼になりました", "", 5, 15, 5);
                runner.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
                runner.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                //send all player
                getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が変わりました");
                getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.BLUE + oni.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + runner.getName());
                //return
                return true;
            }
        }
        return false;
    }
    @Override
    public void sendEndMessage(){
        //send oni name
        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "最後に鬼だったプレーヤー");
        for (Player player : getOniPlayer()) {
            getPlugin().getMessenger().sendMessage(getJoinPlayer(), ":" + ChatColor.BLUE + player.getName());
        }
        //send title
        new BukkitRunnable() {
            @Override
            public void run() {
                //send lose
                getPlugin().getMessenger().sendTitle(getOniPlayer(),ChatColor.RED+"LOSE","");
                //send win
                getPlugin().getMessenger().sendTitle(getRunPlayer(),ChatColor.RED+"WIN","");
            }
        }.runTaskLater(getPlugin(), 40);
    }
}
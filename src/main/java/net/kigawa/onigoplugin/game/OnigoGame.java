package net.kigawa.onigoplugin.game;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameData;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class OnigoGame extends Game {


    ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);

    public OnigoGame(KigawaPlugin kigawaPlugin, GameData gameData, GameManager manager) {
        super(kigawaPlugin, gameData, manager);
    }

    @Override
    public String getBordName() {
        return "鬼ごっこ";
    }

    @Override
    public boolean changeOni(Player oni, Player runner) {
        if (getOniPlayer().contains(oni)) {
            if (getRunPlayer().contains(runner)) {
                //check game type
                switch (getGameType()) {
                    case "change":
                        //change runner list
                        getOniPlayer().add(runner);
                        getRunPlayer().remove(runner);
                        //change oni list
                        getRunPlayer().add(oni);
                        getOniPlayer().remove(oni);
                        //oni to runner
                        oni.sendTitle(ChatColor.GREEN + "鬼を交代しました", "", 5, 15, 5);
                        oni.getInventory().setHelmet(null);
                        //runner to oni
                        runner.sendTitle(ChatColor.RED + "鬼になりました", "", 5, 15, 5);
                        runner.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
                        runner.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                        //send all player
                        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が変わりました");
                        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.BLUE + oni.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + runner.getName());
                        break;
                    case "increase":
                        //changed runner list
                        getOniPlayer().add(runner);
                        getRunPlayer().remove(runner);
                        //send title
                        runner.sendTitle(ChatColor.RED + "鬼になりました", "", 5, 15, 5);
                        oni.sendTitle(ChatColor.GREEN + "逃走車を捕まえました", "", 5, 15, 5);
                        //send message
                        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が増えました");
                        getPlugin().getMessenger().sendMessage(getJoinPlayer(), ChatColor.BLUE + oni.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + runner.getName());
                        break;
                }
                //return
                return true;
            }
        }
        return false;
    }

    @Override
    public void sendEndMessage() {
        //new list
        List<Player> players = new ArrayList<>(getJoinPlayer());
        //check game type
        switch (getGameType()) {
            case "change":
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
                        getPlugin().getMessenger().sendTitle(players, ChatColor.RED + "LOSE", "");
                        //send win
                        getPlugin().getMessenger().sendTitle(players, ChatColor.RED + "WIN", "");
                    }
                }.runTaskLater(getPlugin(), 40);
                break;
            case "increase":

                //runner win
                if (getRunPlayer().size() > 0) {
                    getPlugin().getMessenger().sendTitleLater(players, ChatColor.GREEN + "逃走者の勝利", 40L);
                }
                //oni win
                if (getRunPlayer().size() == 0) {
                    getPlugin().getMessenger().sendTitleLater(players, ChatColor.RED + "鬼の勝利", 40L);
                }
                break;
        }
    }

    @Override
    public void runnable() {
        for (Player player : getOniPlayer()) {
            player.getInventory().setHelmet(helmet);
        }
        switch (getGameType()) {
            case "increase":
                //check runner
                if (getRunPlayer().size()==0){
                    end();
                }
                break;
        }
    }

    @Override
    public void onStart() {
    }
}
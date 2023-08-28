package net.kigawa.onigoplugin.game;

import net.kigawa.onigoplugin.OnigoPlugin;
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
import java.util.LinkedList;
import java.util.List;

public class OnigoGame extends Game {


    ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
    List<Player> caughtPlayer = new ArrayList<>();

    public OnigoGame(OnigoPlugin OnigoPlugin, GameData gameData, GameManager manager) {
        super(OnigoPlugin, gameData, manager);
    }

    @Override
    public String getBordName() {
        return "鬼ごっこ";
    }

    @Override
    public boolean changeOni(Player attacker, Player receiver) {
        if (getOniPlayer().contains(attacker) && getRunPlayer().contains(receiver)) {

            //check game type
            switch (getGameType()) {
                //when change
                case "change":
                    //change runner list
                    getOniPlayer().add(receiver);
                    getRunPlayer().remove(receiver);
                    //change oni list
                    getRunPlayer().add(attacker);
                    getOniPlayer().remove(attacker);
                    //oni to runner
                    attacker.sendTitle(ChatColor.GREEN + "鬼を交代しました", "", 5, 15, 5);
                    attacker.getInventory().setHelmet(null);
                    //runner to oni
                    receiver.sendTitle(ChatColor.RED + "鬼になりました", "", 5, 15, 5);
                    receiver.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
                    receiver.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                    //send all player
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が変わりました");
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.BLUE + attacker.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + receiver.getName());
                    break;
                //when increase
                case "increase":
                    //changed runner list
                    getOniPlayer().add(receiver);
                    getRunPlayer().remove(receiver);
                    //send title
                    receiver.sendTitle(ChatColor.RED + "鬼になりました", "", 5, 15, 5);
                    attacker.sendTitle(ChatColor.GREEN + "逃走車を捕まえました", "", 5, 15, 5);
                    //send message
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.GREEN + "鬼が増えました");
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.BLUE + attacker.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + receiver.getName());
                    break;
                //when ice
                case "ice":
                    //change runner list
                    caughtPlayer.add(receiver);
                    getRunPlayer().remove(receiver);
                    //send title
                    receiver.sendTitle(ChatColor.RED + "鬼につかまりました", "", 5, 15, 5);
                    attacker.sendTitle(ChatColor.GREEN + "逃走車を捕まえました", "", 5, 15, 5);
                    //send message
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.GREEN + "逃走者がつかまりました");
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.BLUE + attacker.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + receiver.getName());
                    //change walk speed
                    receiver.setWalkSpeed(0F);
                    receiver.setFlySpeed(0F);
                    break;
            }
            //return
            return true;

        }
        //release caught player
        if (getRunPlayer().contains(attacker) && caughtPlayer.contains(receiver)) {
            switch (getGameType()) {
                case "ice":
                    //change caught player
                    getRunPlayer().add(receiver);
                    caughtPlayer.remove(receiver);
                    //send title
                    receiver.sendTitle(ChatColor.GREEN + "救出されました", "", 5, 15, 5);
                    attacker.sendTitle(ChatColor.GREEN + "救出しました", "", 5, 15, 5);
                    //send message
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.GREEN + "逃走者が救出されました");
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.BLUE + attacker.getName() + ChatColor.WHITE + "→" + ChatColor.BLUE + receiver.getName());
                    //change walk speed
                    receiver.setWalkSpeed(0.2F);
                    receiver.setFlySpeed(0.2F);
                    break;
            }
        }
        return false;
    }

    @Override
    public void sendEndMessage() {
        //new list
        List<Player> players = new LinkedList<Player>(getJoinPlayer());
        //check game type
        switch (getGameType()) {
            case "change":
                //send oni name
                getPlugin().messenger.sendMessage(getJoinPlayer(), ChatColor.GREEN + "最後に鬼だったプレーヤー");
                for (Player player : getOniPlayer()) {
                    getPlugin().messenger.sendMessage(getJoinPlayer(), ":" + ChatColor.BLUE + player.getName());
                }
                //send title
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        //send lose
                        getPlugin().messenger.sendTitle(players, ChatColor.RED + "敗北", "");
                        //send win
                        getPlugin().messenger.sendTitle(players, ChatColor.RED + "勝利", "");
                    }
                }.runTaskLater(getPlugin(), 40);
                break;
            case "ice":
                for (Player player:caughtPlayer){
                    player.setWalkSpeed(0.2F);
                    player.setFlySpeed(0.2F);
                }
            case "increase":


                //runner win
                if (getRunPlayer().size() > 0) {
                    getPlugin().messenger.sendTitleLater(players, ChatColor.GREEN + "逃走者の勝利", 40L);
                }
                //oni win
                if (getRunPlayer().size() == 0) {
                    getPlugin().messenger.sendTitleLater(players, ChatColor.RED + "鬼の勝利", 40L);
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
            case "ice":
                //check runner
                if (getRunPlayer().size() == 0) {
                    end();
                }
                break;
        }
    }

    @Override
    public void onStart() {
    }
}
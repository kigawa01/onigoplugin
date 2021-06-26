package net.kigawa.util.timer;

import net.kigawa.onigoplugin.command.Test;
import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;
import java.util.Set;

public class Counter extends BukkitRunnable {
    int count;
    KigawaPlugin plugin;
    Scoreboard bord;
    Team team;
    Objective objective;
    Score score;
    int titleCount;
    List<Player> players;
    String lastMessage;
    public Counter(String teamID, KigawaPlugin kigawaPlugin){
        bord= Bukkit.getScoreboardManager().getNewScoreboard();
        team=bord.registerNewTeam(teamID);
        plugin=kigawaPlugin;
    }
    public void startSec(String bordName,String bordID,Long delay, int count, int titleCount, List<Player> players,String lastMessage){
        this.count=count;
        objective=bord.registerNewObjective(bordID,"dummy",bordName);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        score= objective.getScore("時間(秒)");
        score.setScore(count);
        this.titleCount=titleCount;
        this.players=players;
        this.lastMessage=lastMessage;
        for (Player player:players){
            player.setScoreboard(bord);
        }
        runTaskTimer(plugin,delay,20);
    }
    @Override
    public void run() {
        score.setScore(count);
        //count down
        if (count<=titleCount){
            plugin.getMessenger().sendTitle(players,Integer.toString(count),"");
        }
        //send message
        if (count==0){
            plugin.getMessenger().sendTitle(players,lastMessage,"");
            cancel();
        }
        count--;
    }
}

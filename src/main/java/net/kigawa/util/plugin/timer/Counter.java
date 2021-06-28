package net.kigawa.util.plugin.timer;

import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;

public class Counter extends BukkitRunnable {
    int count;
    KigawaPlugin plugin;
    Scoreboard bord;
    Objective objective;
    Score score;
    int titleCount;
    List<Player> players;
    String lastMessage;
    ChatColor countdownColor;
    String unit;
    public Counter(String bordName, String bordID,KigawaPlugin kigawaPlugin){
        bord= Bukkit.getScoreboardManager().getNewScoreboard();
        plugin=kigawaPlugin;
        objective=bord.registerNewObjective(bordID,"dummy",bordName);
    }
    public void startSec( Long delay, int count, int titleCount, List<Player> players, String lastMessage, ChatColor countdownColor){
        this.count=count;
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        score= objective.getScore("時間(秒)");
        this.titleCount=titleCount;
        this.players=players;
        this.lastMessage=lastMessage;
        this.countdownColor=countdownColor;
        for (Player player:players){
            player.setScoreboard(bord);
        }
        unit="sec";
        runTaskTimer(plugin,delay,20);
    }
    public void startMin( Long delay, int count, int titleCount, List<Player> players, String lastMessage, ChatColor countdownColor){
        this.count=count;
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        score= objective.getScore("時間(分)");
        this.titleCount=titleCount;
        this.players=players;
        this.lastMessage=lastMessage;
        this.countdownColor=countdownColor;
        for (Player player:players){
            player.setScoreboard(bord);
        }
        unit="min";
        runTaskTimer(plugin,delay,20*60);
    }
    @Override
    public void run() {
        plugin.logger("run");
        score.setScore(count);
        if (unit.equals("sec")) {
            //count down
            if (count <= titleCount) {
                plugin.getMessenger().sendTitle(players, countdownColor + Integer.toString(count), "");
            }
            //send message
            if (count == 0) {
                plugin.getMessenger().sendTitle(players,lastMessage, "");
                bord.resetScores("時間(秒)");
                cancel();
            }
        }else {
            if (unit.equals("min")){
                if (count==1){
                    plugin.getMessenger().sendTitle(players,countdownColor+Integer.toString(count)+"分","");
                    bord.resetScores("時間(分)");
                    score= objective.getScore("時間(秒)");
                    count=60;
                    unit="sec";
                    new Counter(objective.getDisplayName(),objective.getName(),plugin).startSec(0L,60,titleCount,players,lastMessage,countdownColor);
                    cancel();
                }
            }
        }
        count--;
    }
}

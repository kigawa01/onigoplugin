package net.kigawa.util.plugin.game.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.all.Named;
import net.kigawa.util.plugin.all.timer.Counter;
import net.kigawa.util.plugin.game.onigo.runnable.GameCounter;
import net.kigawa.util.plugin.game.onigo.runnable.GameLimiter;
import net.kigawa.util.plugin.game.stage.StageData;
import net.kigawa.util.plugin.game.stage.runnable.Limiter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Game implements Named, Onigo {
    GameData d;
    OnigoPlugin plugin;
    Game game = this;
    GameManager manager;
    List<Player> joinPlayer;
    List<Player> oniPlayer;
    List<Player> runPlayer;
    StageData stageData;
    Limiter limiter;
    Limiter limiter1;
    Counter counter;
    Counter gameCounter;
    BukkitTask runnable;
    BukkitTask runnable1;

    public Game(OnigoPlugin OnigoPlugin, GameData gameData, GameManager gameManager) {
        plugin = OnigoPlugin;
        d = gameData;
        manager = gameManager;
    }

    public abstract String getBordName();

    public abstract boolean changeOni(Player oni, Player runner);

    public abstract void sendEndMessage();

    public abstract void runnable();

    public abstract void onStart();

    @Override
    public boolean contain(HumanEntity player) {
        if (joinPlayer != null) return joinPlayer.contains(player);
        return false;
    }

    public void start(CommandSender sender, String stage) {
        if (d.getWaitRoomWorld() != null) {
            //sort player
            joinPlayer = plugin.playerGetter.room(d.getWaitRoomWorld(), d.getWaitRoom()[0], d.getWaitRoom()[1], d.getWaitRoom()[2],
                    d.getWaitRoom()[3], d.getWaitRoom()[4], d.getWaitRoom()[5]);
            Random random = new Random();
            oniPlayer = new ArrayList<>();
            runPlayer = new ArrayList<>();
            runPlayer.addAll(joinPlayer);
            oniPlayer = plugin.playerGetter.room(d.getOniWaitWorld(), d.getOniWait()[0], d.getOniWait()[1], d.getOniWait()[2], d.getOniWait()[3], d.getOniWait()[4], d.getOniWait()[5]);
            joinPlayer.addAll(oniPlayer);
            int randomNumber;
            plugin.logger("join player" + joinPlayer.size());
            if (joinPlayer.size() > d.getOniCount()) {
                while (d.getOniCount() != oniPlayer.size()) {
                    if (d.getOniCount() > oniPlayer.size()) {
                        randomNumber = random.nextInt(runPlayer.size());
                        oniPlayer.add(runPlayer.get(randomNumber));
                        runPlayer.remove(randomNumber);
                    }
                    if (d.getOniCount() < oniPlayer.size()) {
                        randomNumber = random.nextInt(oniPlayer.size());
                        runPlayer.add(oniPlayer.get(randomNumber));
                        oniPlayer.remove(randomNumber);
                    }
                }
                //clear inventory
                for (Player player : joinPlayer) {
                    player.getInventory().clear();
                    //give food
                    player.getInventory().addItem(new ItemStack(Material.BREAD, 64));
                }
                //get stage
                stageData = plugin.stageManager.getStage(stage);
                if (stageData != null) {
                    for (Player player : runPlayer) {
                        //teleport runner
                        player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0]) + 0.5,
                                Integer.valueOf(stageData.getStartLoc()[1]) + 0.5, Integer.valueOf(stageData.getStartLoc()[2]) + 0.5));
                    }
                    //limiter
                    limiter = new Limiter(plugin, runPlayer, stageData);
                    //counter
                    counter = new Counter(getBordName(), "onigo", plugin);
                    counter.startSec(0L, d.getWaitTime(), 3, joinPlayer, ChatColor.GREEN + "START", ChatColor.GREEN);
                    //count wait time
                    runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            //send message
                            plugin.messenger.sendMessage(joinPlayer, ChatColor.GREEN + "最初に鬼のプレーヤー");
                            //teleport oni
                            for (Player player : oniPlayer) {
                                player.teleport(new Location(plugin.getServer().getWorld(stageData.getStageWorld()), Integer.valueOf(stageData.getStartLoc()[0]) + 0.5,
                                        Integer.valueOf(stageData.getStartLoc()[1]) + 0.5, Integer.valueOf(stageData.getStartLoc()[2]) + 0.5));
                                plugin.messenger.sendMessage(joinPlayer, ChatColor.BLUE + player.getName());
                                //wear gold helmet
                                player.getInventory().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                            }
                            //cancel limiter
                            limiter.cancel();
                            //limiter
                            limiter1 = new GameLimiter(plugin, stageData, game);
                            //counter
                            counter.cancel();
                            gameCounter = new GameCounter(getBordName(), getName(), game);
                            //end
                            runnable1 = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    end();
                                }
                            }.runTaskLater(plugin, d.getGameTime() * 20 * 60);
                        }
                    }.runTaskLater(plugin, d.getWaitTime() * 20);
                    onStart();
                } else {
                    sender.sendMessage("stage is not exit");
                }
            } else {
                sender.sendMessage("player is not enough");
            }
        } else {
            sender.sendMessage("need waiting room");
        }
    }

    public void save(GameData data) {
        plugin.recorder.save(data, manager.getName());
    }

    public void end() {
        //clear inventory
        for (Player player : joinPlayer) {
            player.getInventory().clear();
        }
        sendEndMessage();
        //teleport players
        plugin.teleport.teleportPlayers(joinPlayer, new Location(plugin.getServer().getWorld(d.getEndWorld()), d.getEndLoc()[0] + 0.5, d.getEndLoc()[1] + 0.5, d.getEndLoc()[2] + 0.5));
        //return stage
        plugin.stageManager.returnStage(stageData);
        //cancel counter
        counter.end();
        gameCounter.end();
        //cancel limiter
        limiter.cancel();
        limiter1.cancel();
        //cancel runnable
        runnable.cancel();
        runnable1.cancel();
        //remove in list
        while (0 < joinPlayer.size()) {
            joinPlayer.remove(0);
        }
        while (0 < oniPlayer.size()) {
            oniPlayer.remove(0);
        }
        while (0 < runPlayer.size()) {
            runPlayer.remove(0);
        }
        joinPlayer = null;
        oniPlayer = null;
        runPlayer = null;
        stageData = null;
        limiter = null;
        limiter1 = null;
        counter = null;
        gameCounter = null;
        runnable = null;
        runnable1 = null;

    }


    public void setEndLoc(String world, int x, int y, int z) {
        int[] loc = d.getEndLoc();
        loc[0] = x;
        loc[1] = y;
        loc[2] = z;
        d.setEndWorld(world);
        plugin.recorder.save(d, manager.getName());
    }

    public void setGameTime(int gameTime) {
        d.setGameTime(gameTime);
        plugin.recorder.save(d, manager.getName());
    }

    public void setWaitTime(int waitTime) {
        d.setWaitTime(waitTime);
        plugin.recorder.save(d, manager.getName());
    }

    public void setOniCount(int oniCount) {
        d.setOniCount(oniCount);
        plugin.recorder.save(d, manager.getName());
    }

    public void setOniWait1(String world, int x, int y, int z) {
        int[] loc = d.getOniWait();
        loc[0] = x;
        loc[1] = y;
        loc[2] = z;
        d.setOniWaitWorld(world);
        plugin.recorder.save(d, manager.getName());
    }

    public void setOniWait2(int x, int y, int z) {
        int[] loc = d.getOniWait();
        loc[3] = x;
        loc[4] = y;
        loc[5] = z;
        plugin.recorder.save(d, manager.getName());
    }

    public void setWaitingRoom1(String world, int x, int y, int z) {
        int[] loc = d.getWaitRoom();
        loc[0] = x;
        loc[1] = y;
        loc[2] = z;
        d.setWaitRoomWorld(world);
        plugin.recorder.save(d, manager.getName());
    }

    public void setWaitingRoom2(int x, int y, int z) {
        int[] loc = d.getWaitRoom();
        loc[3] = x;
        loc[4] = y;
        loc[5] = z;
        plugin.recorder.save(d, manager.getName());
    }


    public String getName() {
        return d.getName();
    }

    public OnigoPlugin getPlugin() {
        return plugin;
    }

    public List<Player> getRunPlayer() {
        return runPlayer;
    }

    public GameData getD() {
        return d;
    }

    public String getGameType() {
        return d.getGameType();
    }

    public void setGameType(String gameType) {
        d.setGameType(gameType);
        plugin.recorder.save(d, manager.getName());
    }

    public List<Player> getOniPlayer() {
        return oniPlayer;
    }

    public List<Player> getJoinPlayer() {
        return joinPlayer;
    }
}

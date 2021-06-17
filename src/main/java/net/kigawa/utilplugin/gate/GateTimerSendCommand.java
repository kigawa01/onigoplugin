package net.kigawa.utilplugin.gate;

import net.kigawa.utilplugin.data.RegionData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class GateTimerSendCommand extends BukkitRunnable {
    public String name;
    JavaPlugin plugin;
    RegionData regionData;
    Player player;
    String command;
    public GateTimerSendCommand(JavaPlugin plugin, String name, Location location, Location location1, String command){
        this.plugin=plugin;
        this.name=name;
        regionData=new RegionData(location,location1);
        this.command=command;
    }
    @Override
    public void run() {
        player=regionData.isPlayer();
        if(player!=null){
            plugin.getServer().dispatchCommand(player,command);
        }
    }
}

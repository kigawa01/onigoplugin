package net.kigawa.utilplugin.api.plugin;

import net.kigawa.utilplugin.api.command.MainCommand;
import net.kigawa.utilplugin.api.config.KigawaConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class KigawaPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("debug",false);
        this.saveConfig();

        onStart();
    }
    public abstract void onStart();
    public void logger(String message){
        this.getLogger().info(message);
    }
    public KigawaConfig getConfig;
    public void eventSetter(Listener listener){
        getServer().getPluginManager().registerEvents(listener,this);
    }

}

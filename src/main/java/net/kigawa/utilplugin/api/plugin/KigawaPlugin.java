package net.kigawa.utilplugin.api.plugin;

import net.kigawa.utilplugin.api.command.MainCommand;
import net.kigawa.utilplugin.api.config.KigawaConfig;
import net.kigawa.utilplugin.api.player.PlayerGetter;
import net.kigawa.utilplugin.api.recorder.Recorder;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class KigawaPlugin extends JavaPlugin {
    boolean debug;
    Recorder recorder;
    PlayerGetter playerGetter;
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("debug",false);
        config.addDefault("useDB",false);
        addConfigDefault(config);
        config.options().copyDefaults(true);
        this.saveConfig();
        debug=config.getBoolean("debug");

        recorder=new Recorder(this);
        playerGetter=new PlayerGetter();

        onStart();
    }
    public abstract void onStart();
    public void logger(String message){
        if(debug) {
            this.getLogger().info(message);
        }
    }
    public void logger(int message){
        if(debug) {
            this.getLogger().info(Integer.toString(message));
        }
    }
    public void logger(boolean message){
        if(debug) {
            this.getLogger().info(Boolean.toString(message));
        }
    }
    public void eventSetter(Listener listener){
        getServer().getPluginManager().registerEvents(listener,this);
    }
    public Recorder getRecorder(){
            return recorder;
    }
    public abstract void addConfigDefault(FileConfiguration config);

}

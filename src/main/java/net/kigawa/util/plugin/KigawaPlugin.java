package net.kigawa.util.plugin;

import net.kigawa.util.plugin.player.PlayerGetter;
import net.kigawa.util.plugin.recorder.Recorder;
import net.kigawa.util.plugin.stage.StageManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class KigawaPlugin extends JavaPlugin {
    boolean debug;
    Recorder recorder;
    PlayerGetter playerGetter;
    StageManager stageManager;
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
        playerGetter=new PlayerGetter(this);
        stageManager=new StageManager(this);

        onStart();
    }

    public StageManager getStageManager() {
        return stageManager;
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
    public PlayerGetter getPlayerGetter(){
        return playerGetter;
    }
}
package net.kigawa.util.plugin.all;

import net.kigawa.util.message.Logger;
import net.kigawa.util.plugin.all.message.Messenger;
import net.kigawa.util.plugin.all.player.PlayerGetter;
import net.kigawa.util.plugin.all.player.Teleporter;
import net.kigawa.util.plugin.all.recorder.Recorder;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.stage.StageManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class KigawaPlugin extends JavaPlugin implements Logger {
    boolean debug;
    Recorder recorder;
    PlayerGetter playerGetter;
    StageManager stageManager;
    Messenger messenger;
    Teleporter teleporter;
    List<GameManager> gameManagers = new ArrayList<>();

    public List<GameManager> getGameManagers() {
        return gameManagers;
    }

    public void addGameManager(GameManager gameManager) {
        gameManagers.add(gameManager);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("debug", false);
        config.addDefault("useDB", false);
        addConfigDefault(config);
        config.options().copyDefaults(true);
        this.saveConfig();
        debug = config.getBoolean("debug");

        recorder = new Recorder(this);
        playerGetter = new PlayerGetter(this);
        stageManager = new StageManager(this);
        messenger = new Messenger();
        teleporter = new Teleporter();

        onStart();
    }

    public Teleporter getTeleporter() {
        return teleporter;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public StageManager getStageManager() {
        return stageManager;
    }

    public abstract void onStart();

    public void logger(String message) {
        if (debug) {
            this.getLogger().info(message);
        }
    }

    public void logger(int message) {
        if (debug) {
            this.getLogger().info(Integer.toString(message));
        }
    }

    public void logger(boolean message) {
        logger(String.valueOf(message));
    }

    public void eventSetter(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public Recorder getRecorder() {
        return recorder;
    }

    public abstract void addConfigDefault(FileConfiguration config);

    public PlayerGetter getPlayerGetter() {
        return playerGetter;
    }
}

package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Stage;
import net.kigawa.onigoplugin.command.change.create.OnigoCreate;
import net.kigawa.onigoplugin.command.change.game.Onigo;
import net.kigawa.onigoplugin.event.change.OnigoListener;
import net.kigawa.onigoplugin.game.change.ChangeManager;
import net.kigawa.onigoplugin.game.increase.IncreaseManager;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;
    GameManager changeGame;
    GameManager increaseGame;

    @Override
    public void onStart() {
        //initialize
        changeGame = new ChangeManager(this, "change");
        onigo = new Onigo(this);
        increaseGame = new IncreaseManager(this, "increase");
        //new Test(this);
        new OnigoCreate(this);
        new Stage(this);
        new OnigoListener(this);

    }

    public GameManager getIncreaseGame() {
        return increaseGame;
    }

    public GameManager getChangeGame() {
        return changeGame;
    }

    @Override
    public void addConfigDefault(FileConfiguration config) {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Onigo getOnigo() {
        return onigo;
    }
}

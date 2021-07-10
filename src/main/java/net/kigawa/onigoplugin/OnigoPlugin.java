package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Stage;
import net.kigawa.onigoplugin.command.change.OnigoCreate;
import net.kigawa.onigoplugin.command.change.Onigo;
import net.kigawa.onigoplugin.command.increase.Increase;
import net.kigawa.util.plugin.game.onigo.evnt.OnigoListener;
import net.kigawa.onigoplugin.game.change.ChangeManager;
import net.kigawa.onigoplugin.game.increase.IncreaseManager;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;
    Increase increase;
    GameManager changeGame;
    GameManager increaseGame;

    @Override
    public void onStart() {
        //initialize
        changeGame = new ChangeManager(this, "change");
        onigo = new Onigo(this,changeGame);
        increaseGame = new IncreaseManager(this, "increase");
        increase = new Increase(this,increaseGame);
        addGameManager(changeGame);
        addGameManager(increaseGame);
        //new Test(this);
        new OnigoCreate(this,changeGame);
        new Stage(this);
        new OnigoListener(this);

    }

    public GameManager getIncreaseGame() {
        return increaseGame;
    }

    public GameManager getChangeGame()
    {
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

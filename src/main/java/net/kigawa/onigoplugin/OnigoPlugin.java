package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Stage;
import net.kigawa.onigoplugin.command.Onigo;
import net.kigawa.onigoplugin.command.OnigoCreate;
import net.kigawa.onigoplugin.game.ChangeManager;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.evnt.OnigoListener;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;
    GameManager changeGame;

    @Override
    public void onStart() {
        //initialize
        changeGame = new ChangeManager(this, "change");
        onigo = new Onigo(this, changeGame);
        addGameManager(changeGame);
        //new Test(this);
        new OnigoCreate(this, changeGame);
        new Stage(this);
        new OnigoListener(this);
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

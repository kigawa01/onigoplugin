package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Stage;
import net.kigawa.onigoplugin.command.change.game.Onigo;
import net.kigawa.onigoplugin.command.change.create.OnigoCreate;
import net.kigawa.onigoplugin.event.change.OnigoListener;
import net.kigawa.onigoplugin.game.change.OnigoManager;
import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;
    OnigoManager onigoManager;
    @Override
    public void onStart() {
        onigoManager=new OnigoManager(this);
        onigo= new Onigo(this);
        //new Test(this);
        new OnigoCreate(this);
        new Stage(this);
        new OnigoListener(this);

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

    public OnigoManager getOnigoManager() {
        return onigoManager;
    }
}

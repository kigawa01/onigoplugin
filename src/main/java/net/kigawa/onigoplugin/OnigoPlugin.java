package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Stage;
import net.kigawa.onigoplugin.command.onigo.Onigo;
import net.kigawa.onigoplugin.command.Test;
import net.kigawa.onigoplugin.command.onigo.OnigoCreate;
import net.kigawa.onigoplugin.event.onigo.OnigoListener;
import net.kigawa.onigoplugin.onigo.OnigoManager;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.stage.StageCommand;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;
    OnigoManager onigoManager;
    @Override
    public void onStart() {
        onigoManager=new OnigoManager(this);
        onigo= new Onigo(this);
        new Test(this);
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

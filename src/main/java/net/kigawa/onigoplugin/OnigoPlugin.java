package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Onigo;
import net.kigawa.onigoplugin.command.Test;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public final class OnigoPlugin extends KigawaPlugin {
    Onigo onigo;

    @Override
    public void onStart() {
        onigo= new Onigo(this);
        new Test(this);

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

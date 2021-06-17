package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.Onigo;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;

public final class OnigoPlugin extends KigawaPlugin {


    @Override
    public void onStart() {
        new Onigo(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

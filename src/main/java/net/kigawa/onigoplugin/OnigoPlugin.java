package net.kigawa.onigoplugin;

import net.kigawa.onigoplugin.command.onigo;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;

public final class OnigoPlugin extends KigawaPlugin {


    @Override
    public void onStart() {
        new onigo(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

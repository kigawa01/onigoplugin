package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;

public class Onigo extends net.kigawa.util.plugin.game.onigo.command.game.Onigo {


    public Onigo(OnigoPlugin plugin, GameManager manager) {
        super(plugin,manager);
    }

    @Override
    public String getCommandStr() {
        return "change";
    }
}

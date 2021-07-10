package net.kigawa.onigoplugin.command.increase.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.game.Onigo;

public class Increase extends Onigo {
    public Increase(OnigoPlugin plugin, GameManager manager) {
        super(plugin, manager);
    }

    @Override
    public String getCommandStr() {
        return "increase";
    }
}

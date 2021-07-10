package net.kigawa.onigoplugin.command.increase;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.game.Onigo;

public class Increase extends Onigo {
    public Increase(OnigoPlugin plugin, GameManager manager) {
        super(plugin, manager);
    }

    @Override
    public String getName() {
        return "increase";
    }
}

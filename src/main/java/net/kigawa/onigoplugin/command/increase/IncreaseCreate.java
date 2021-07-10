package net.kigawa.onigoplugin.command.increase;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.crate.CreateOnigo;

public class IncreaseCreate extends CreateOnigo {
    public IncreaseCreate(OnigoPlugin plugin,GameManager manager) {
        super(plugin,manager);
    }

    @Override
    public String getName() {
        return "incrasecreate";
    }
}

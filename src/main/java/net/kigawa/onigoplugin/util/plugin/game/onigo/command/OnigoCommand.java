package net.kigawa.onigoplugin.util.plugin.game.onigo.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.command.SubCommand;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;

public abstract class OnigoCommand extends SubCommand {
    GameManager manager;
    public OnigoCommand(OnigoPlugin OnigoPlugin, GameManager manager) {
        super(OnigoPlugin);
        this.manager=manager;
    }

    public GameManager getManager() {
        return manager;
    }
}

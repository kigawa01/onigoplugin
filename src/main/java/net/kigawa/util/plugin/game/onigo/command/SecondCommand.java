package net.kigawa.util.plugin.game.onigo.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameManager;

public abstract class SecondCommand extends SubCommand {
    GameManager manager;
    public SecondCommand(KigawaPlugin kigawaPlugin, GameManager manager) {
        super(kigawaPlugin);
        this.manager=manager;
    }

    public GameManager getManager() {
        return manager;
    }
}

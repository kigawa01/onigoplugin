package net.kigawa.util.plugin.game.onigo.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameManager;

public abstract class OnigoCommand extends SubCommand {
    GameManager manager;
    public OnigoCommand(KigawaPlugin kigawaPlugin, GameManager manager) {
        super(kigawaPlugin);
        this.manager=manager;
    }

    public GameManager getManager() {
        return manager;
    }
}

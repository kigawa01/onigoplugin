package net.kigawa.onigoplugin.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.stage.command.StageCommand;

public class Stage extends StageCommand {
    public Stage(KigawaPlugin plugin) {
        super(plugin);
    }


    @Override
    public String getCommandStr() {
        return "stage";
    }
}

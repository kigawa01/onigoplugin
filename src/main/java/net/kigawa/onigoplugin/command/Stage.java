package net.kigawa.onigoplugin.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.stage.command.StageCommand;

public class Stage extends StageCommand {
    public Stage(OnigoPlugin plugin) {
        super(plugin);
    }


    @Override
    public String getName() {
        return "stage";
    }
}

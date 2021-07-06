package net.kigawa.onigoplugin.command;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.stage.StageCommand;

import java.util.List;

public class Stage extends StageCommand {
    public Stage(KigawaPlugin plugin) {
        super(plugin);
    }


    @Override
    public String getCommandStr() {
        return "stage";
    }
}

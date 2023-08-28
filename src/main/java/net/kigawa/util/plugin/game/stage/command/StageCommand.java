package net.kigawa.util.plugin.game.stage.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class StageCommand extends FirstCommand {
    OnigoPlugin plugin;


    public StageCommand(OnigoPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
        addSubcommands(new CreateCommand(plugin));
        addSubcommands(new SetStage1(plugin));
        addSubcommands(new SetStage2(plugin));
        addSubcommands(new SetStartLoc(plugin));
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}

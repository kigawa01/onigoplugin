package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.FirstCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class StageCommand extends FirstCommand {
    KigawaPlugin plugin;


    public StageCommand(KigawaPlugin plugin) {
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

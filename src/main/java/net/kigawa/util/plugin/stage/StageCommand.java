package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.MainCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class StageCommand extends MainCommand {
    public StageCommand(KigawaPlugin plugin) {
        super(plugin);
        subCommands.add(new CreateCommand(plugin));
    }

    @Override
    public String getCommandStr() {
        return "stage";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
    List<SubCommand> subCommands;
    @Override
    public List<SubCommand> getCommandList() {
        return subCommands;
    }
}

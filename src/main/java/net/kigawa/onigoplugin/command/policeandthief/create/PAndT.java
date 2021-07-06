package net.kigawa.onigoplugin.command.policeandthief.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.FirstCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class PAndT extends FirstCommand {
    OnigoPlugin plugin;
    public PAndT(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }

    @Override
    public String getCommandStr() {
        return "pandt";
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public void addSubcommands(List<SubCommand> subCommands) {

    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return null;
    }
}

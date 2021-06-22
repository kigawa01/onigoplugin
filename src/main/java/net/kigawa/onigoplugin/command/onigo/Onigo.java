package net.kigawa.onigoplugin.command.onigo;

import net.kigawa.util.plugin.command.MainCommand;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Onigo extends MainCommand {
    List<SubCommand> subCommands=new ArrayList<SubCommand>();
    KigawaPlugin plugin;
    public Onigo(KigawaPlugin plugin) {
        super(plugin);
        subCommands.add(new Start(plugin));
        subCommands.add(new End(plugin));
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return subCommands;
    }

    @Override
    public String getCommandStr() {
        return "onigo";
    }
}

package net.kigawa.onigoplugin.command;

import net.kigawa.utilplugin.api.command.MainCommand;
import net.kigawa.utilplugin.api.command.LastCommand;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Onigo extends MainCommand {
    List<LastCommand> subCommands=new ArrayList<>();
    public Onigo(KigawaPlugin plugin) {
        super(plugin);
        subCommands.add(new OnigoStart(plugin));
        subCommands.add(new OnigoEnd(plugin));
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
    public List<LastCommand> getCommandList() {
        return subCommands;
    }

    @Override
    public String getCommandStr() {
        return "onigo";
    }
}

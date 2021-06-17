package net.kigawa.onigoplugin.command;

import net.kigawa.utilplugin.api.command.MainCommand;
import net.kigawa.utilplugin.api.command.SubCommand;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class onigo extends MainCommand {
    List<SubCommand> subCommands=new ArrayList<>();
    public onigo(KigawaPlugin plugin) {
        super(plugin);
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

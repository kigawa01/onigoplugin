package net.kigawa.onigoplugin.command.onigo.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.MainCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class OnigoCreate extends MainCommand {
    List<SubCommand> subCommands=new ArrayList<>();
    OnigoPlugin plugin;
    public OnigoCreate(OnigoPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
        subCommands.add(new CreateGame(plugin));
        subCommands.add(new SetWaitRoom1(plugin));
        subCommands.add(new SetWaitRoom2(plugin));
        subCommands.add(new SetOniCount(plugin));
        subCommands.add(new SetWaitTime(plugin));
        subCommands.add(new SetGameTime(plugin));
        subCommands.add(new SetEndLoc(plugin));
    }

    @Override
    public String getCommandStr() {
        return "onigocreate";
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
}
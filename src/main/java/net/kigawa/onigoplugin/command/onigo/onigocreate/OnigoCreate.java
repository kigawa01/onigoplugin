package net.kigawa.onigoplugin.command.onigo.onigocreate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.FirstCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class OnigoCreate extends FirstCommand {
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
    public int getWordNumber() {
        return 0;
    }

    @Override
    public void addSubcommands(List<SubCommand> subCommands) {
        subCommands.add(new CreateGame(plugin));
        subCommands.add(new SetWaitRoom1(plugin));
        subCommands.add(new SetWaitRoom2(plugin));
        subCommands.add(new SetOniCount(plugin));
        subCommands.add(new SetWaitTime(plugin));
        subCommands.add(new SetGameTime(plugin));
        subCommands.add(new SetEndLoc(plugin));
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
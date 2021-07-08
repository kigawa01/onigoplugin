package net.kigawa.onigoplugin.command.change.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.all.command.SubCommand;
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
        addSubcommands(new CreateGame(plugin));
        addSubcommands(new SetWaitRoom1(plugin));
        addSubcommands(new SetWaitRoom2(plugin));
        addSubcommands(new SetOniCount(plugin));
        addSubcommands(new SetWaitTime(plugin));
        addSubcommands(new SetGameTime(plugin));
        addSubcommands(new SetEndLoc(plugin));
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
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}